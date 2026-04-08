package com.gmail.utils;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.EncoderConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import java.util.Objects;

import static com.gmail.utils.Constants.*;
import static com.gmail.utils.TokenManager.getAccessToken;
import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.requestSpecification;
import static com.gmail.utils.PropertyLoader.getMyProperty;


public class SpecBuilder {

    @Step
    public static RequestSpecification getRequestSpecification(){
        return
        new RequestSpecBuilder()
            .log(LogDetail.ALL)
            .setUrlEncodingEnabled(false)
            .setBaseUri(getMyProperty(BASE_URI))
            .setBasePath(getMyProperty(BASE_RPATH))
            .setAuth(RestAssured.oauth2(getAccessToken()))
            .setConfig(RestAssuredConfig.config().encoderConfig(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false)))
            .addFilter(new AllureRestAssured())
            .build();
    }

    @Step
    public static RequestSpecification postRequestSpecification(){
        return
        new RequestSpecBuilder()
            .log(LogDetail.ALL)
            .setUrlEncodingEnabled(false)
            .setBaseUri(getMyProperty(BASE_URI))
            .setBasePath(getMyProperty(BASE_RPATH))
            .setAuth(RestAssured.oauth2(getAccessToken()))
            .setConfig(RestAssuredConfig.config().encoderConfig(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false)))
            .setContentType(ContentType.JSON)
            .addFilter(new AllureRestAssured())
            .build();
    }

    @Step
    public static ResponseSpecification getResponseSpecification() {
        return
        new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .build();
    }

    @Step
    public static RequestSpecification postRequestSpecificationOAuth(){
        return
            new RequestSpecBuilder()
                .log(LogDetail.ALL)
                .setUrlEncodingEnabled(false)
                .setBaseUri(getMyProperty(REFRESH_TOKEN_URL))
                .setBasePath(getMyProperty(REFRESH_TOKEN_PATH))
                .setContentType(ContentType.URLENC)
                .setConfig(config.encoderConfig(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false)))
                .addFilter(new AllureRestAssured())
                .build();
    }
}