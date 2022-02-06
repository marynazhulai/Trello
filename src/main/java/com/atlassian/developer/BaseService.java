package com.atlassian.developer;

import com.fasterxml.jackson.databind.DeserializationFeature;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapper;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpHeaders;

public class BaseService {

    //TO FIX
    private static final String TRELLO_API_KEY = "";
    private static final String TRELLO_API_TOKEN = "";
    private static final String AUTH_HEADER_RORMAT = "";
    //TO FIX

     protected RequestSpecification getBaseRequestSpecification() {
        return RestAssured.given()
                          .baseUri("https://api.trello.com/")
                          .contentType(ContentType.JSON)
                .header(HttpHeaders.AUTHORIZATION, String.format(AUTH_HEADER_RORMAT, TRELLO_API_KEY, TRELLO_API_TOKEN))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .filter(new AllureRestAssured())
                .config(RestAssuredConfig.config().objectMapperConfig(
                        new ObjectMapperConfig(ObjectMapperType.JACKSON_2)
                        .jackson1ObjectMapperFactory((type, s) -> {
                            return new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
                        })));
    }

}
