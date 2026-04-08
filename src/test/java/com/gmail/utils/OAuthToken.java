package com.gmail.utils;

import io.qameta.allure.Step;

public class OAuthToken {
    /*
        Why a separate class and final variables?
            Each thread will share this OAuthToken object
            No thread can modify the final variables
            if token expires mid-execution of say Thread n,
                it calls refreshToken() and a new OAuthToken object (Obj2) is created with new access_token
                Only Thread n and the Threads that follow (Thread n+1) will get refer Obj2 and the accessToken of that object
                Old Threads (Thread n-1) w
        expiryTimeInMills = 12345 (12:00:00) + 3599 - 10000 (10 secs buffer)
     */
    private final String accessToken;
    private final long expiryTimeInMills;

    public OAuthToken(String accessToken, long expiryTimeInMills) {
        this.accessToken = accessToken;
        this.expiryTimeInMills = System.currentTimeMillis() + expiryTimeInMills - 1000;
    }

    @Override
    public String toString() {
        return "OAuthToken{" +
            "accessToken='" + accessToken + '\'' +
            ", expiryTimeInMills=" + expiryTimeInMills +
            '}';
    }

    @Step("Return the AccessToken")
    public String getAccessToken() {
        return accessToken;
    }

    @Step("Get Access Token Expiry")
    public long getExpiryTimeInMills() {
        return expiryTimeInMills;
    }

    @Step("Check if the AccessToken is Expired")
    public boolean isExpired(){
        return System.currentTimeMillis() >= getExpiryTimeInMills();
    }
}