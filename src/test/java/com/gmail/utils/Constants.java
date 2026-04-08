package com.gmail.utils;

import java.util.Random;
import static com.gmail.utils.PropertyLoader.getMyProperty;

public class Constants {
    public static final String BASE_URI = "BASE_URI";
    public static final String BASE_RPATH = "BASE_RPATH";
    public static final String USER_ID = "USER_ID";
    public static final String USERS_RPATH = "/users/" + getMyProperty(USER_ID);
    public static final String DRAFTS_RPATH = USERS_RPATH + "/drafts";
    public static final String REFRESH_TOKEN_URL = "REFRESH_TOKEN_URL";
    public static final String REFRESH_TOKEN_PATH = "REFRESH_TOKEN_PATH";
    public static final String CLIENT_ID = "CLIENT_ID";
    public static final String CLIENT_SECRET = "CLIENT_SECRET";
    public static final String REFRESH_TOKEN = "REFRESH_TOKEN";
    public static final String GRANT_TYPE = "grant_type";
    public static final String OAUTH2_GRANT_TYPE = "OAUTH2_GRANT_TYPE";
    public static final String PROPERTY_FILE = "default-configurations.properties";
    public static final String ACCESS_TOKEN = "access_token";
    public static final String EXPIRED_IN = "expires_in";

    public static final String CREATE_DRAFT_EMAIL_TEXT = "From: "+getMyProperty(USER_ID)+"\n" +
                                                         "To: "+getMyProperty(USER_ID)+"\n" +
                                                        "Subject: Creating a new Email draft-" + new Random().nextInt() +"\n"+
                                                        "\n" +
                                                        "This draft was created using Gmail Api.";

    public static final String UPDATE_DRAFT_EMAIL_TEXT = "From: "+getMyProperty(USER_ID)+"\n" +
                                                        "To: "+getMyProperty(USER_ID)+"\n" +
                                                        "Subject: Updating a new Email draft-" + new Random().nextInt() +"\n"+
                                                        "\n" +
                                                        "This draft was updated using Gmail Api.";
}