package com.atlassian.developer;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpHeaders;

public class BaseService {

    private static String apiKey = System.getProperty("apiKey");
    private static String token = System.getProperty("token");
    private static final String AUTH_HEADER_FORMAT = "OAuth oauth_consumer_key=\"%s\", oauth_token=\"%s\"";


    protected RequestSpecification getBaseRequestSpecification() {
        return RestAssured.given()
                .baseUri("https://api.trello.com/")
                .contentType(ContentType.JSON)
                .header(HttpHeaders.AUTHORIZATION, String.format(AUTH_HEADER_FORMAT, apiKey, token))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .filter(new AllureRestAssured())
                .config(RestAssuredConfig.config().objectMapperConfig(
                        new ObjectMapperConfig(ObjectMapperType.JACKSON_2)
                                .jackson2ObjectMapperFactory((type, s) -> {
                                    return new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                                            .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS, true)
                                            .configure(SerializationFeature.WRAP_ROOT_VALUE, true);
                                })));
    }
}
