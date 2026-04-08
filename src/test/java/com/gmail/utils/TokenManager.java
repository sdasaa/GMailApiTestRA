package com.gmail.utils;

import com.gmail.api.oauth.OAuthTokenApi;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Objects;

import static com.gmail.utils.Constants.*;
import static com.gmail.utils.PropertyLoader.getMyProperty;
import static com.gmail.utils.SpecBuilder.postRequestSpecificationOAuth;

public class TokenManager {
    private static final ThreadLocal<OAuthToken> threadHolder = new ThreadLocal<>();

    @Step
    public static String getAccessToken(){
        OAuthToken token = threadHolder.get();
        if(Objects.isNull(token) || token.isExpired()){
            System.out.println("Refreshing token");
            Response response = refreshToken();
            if(response.statusCode() == 200) {
                OAuthToken oAuthToken = new OAuthToken(response.jsonPath().getString(ACCESS_TOKEN), Long.parseLong(response.jsonPath().getString(EXPIRED_IN)));
                System.out.println(oAuthToken.toString());
                threadHolder.set(oAuthToken);
            }else
                throw new RuntimeException("Unable to Refresh Token, Aborting Test Execution !!");
        }else
            System.out.println(" Token is still valid ");
        return threadHolder.get().getAccessToken();
    }

    @Step
    private static Response refreshToken(){
            return
                new OAuthTokenApi()
                    .refreshOAuthToken(postRequestSpecificationOAuth(), buildFormparamOAuth());
    }

    @Step
    private static HashMap<String, String> buildFormparamOAuth(){
        HashMap<String,String> formparams = new HashMap<String, String>();
        formparams.put(StringUtils.lowerCase(CLIENT_ID), getMyProperty(CLIENT_ID));
        formparams.put(StringUtils.lowerCase(CLIENT_SECRET), getMyProperty(CLIENT_SECRET));
        formparams.put(StringUtils.lowerCase(REFRESH_TOKEN), getMyProperty(REFRESH_TOKEN));
        formparams.put(StringUtils.lowerCase(GRANT_TYPE), getMyProperty(OAUTH2_GRANT_TYPE));
        return formparams;
    }

}