package com.gmail.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class BaseApi {

    protected Response listAllApi(RequestSpecification requestSpecification, String pathParam){
        return given()
                    .spec(requestSpecification)
                .when()
                    .get(pathParam)
                .then()
                    .log().all()
                    .extract()
                    .response();
    }

    protected Response getApi(RequestSpecification requestSpecification, String pathParam, String id){
        return given()
                    .spec(requestSpecification)
                .when()
                    .get(pathParam + "/" + id)
                .then()
                    .log().all()
                    .extract()
                    .response();
    }

    protected Response deleteApi(RequestSpecification requestSpecification, String pathParam, String id){
           return given()
                    .spec(requestSpecification)
                .when()
                    .delete(pathParam + "/" + id)
                .then()
                    .log().all()
                   .extract()
                   .response();
    }

    protected <T> Response postApi(RequestSpecification requestSpecification, String pathParam, T requestPayload){
        return  given()
                    .spec(requestSpecification)
                    .contentType(ContentType.JSON)
                    .body(requestPayload)
                .when()
                    .post(pathParam)
                .then()
                    .log().all()
                    .extract()
                    .response();
    }

    protected <T> Response putApi(RequestSpecification requestSpecification, String pathParam, T requestPayload, String id){
        return  given()
                    .spec(requestSpecification)
                    .contentType(ContentType.JSON)
                    .body(requestPayload)
                .when()
                    .put(pathParam + "/" + id)
                .then()
                    .log().all()
                    .extract()
                    .response();
    }

    protected Response postApiOAuth(RequestSpecification requestSpecification, HashMap<String, String> formParamsMap){
       return given()
                .spec(requestSpecification)
                .formParams(formParamsMap)
            .when()
                .post()
            .then()
                .log().all()
                .extract()
                .response();
    }

}