package com.teun.moviemanager.config;

public class AuthConfigurationConstants {

    public static final String SECRET = "MoveManager_Secret";
    public static final long EXPIRATION_TIME = 864000000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/auth/signup";
    public static final String SIGN_IN_URL = "/login";

}
