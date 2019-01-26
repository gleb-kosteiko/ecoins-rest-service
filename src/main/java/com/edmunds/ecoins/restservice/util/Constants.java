package com.edmunds.ecoins.restservice.util;

public class Constants {
    public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 5*60*60;
    public static final String SIGNING_KEY = "defPub0923Check";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String AUTHORITIES_KEY = "scopes";

    public static final String DEFAULT_CRON_SCHEDULE = "0 0 1-3 * * *";
}
