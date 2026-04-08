package com.gmail.api.oauth;

import com.gmail.api.BaseApi;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;

public class OAuthTokenApi extends BaseApi {

    @Step
    public Response refreshOAuthToken(RequestSpecification requestSpecification, HashMap<String, String> formparams){
        return postApiOAuth(requestSpecification, formparams);
    }

}