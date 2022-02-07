package com.atlassian.developer;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    //TO FIX
    private static final String TRELLO_API_KEY = "220b2b86c6fd3d6377c24c134c778338";
    private static final String TRELLO_API_TOKEN = "0f7b7284bfff5589cfd59f555810753481364189080cb3a287357b2825b8b022\n";
    private static final String AUTH_HEADER_RORMAT = "OAuth oauth_consumer_key=\"%s\", oauth_token=\"%s\"";
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
                                .jackson2ObjectMapperFactory((type, s) -> {
                                    return new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
                        })));
    }
}
