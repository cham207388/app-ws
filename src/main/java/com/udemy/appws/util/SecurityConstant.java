package com.udemy.appws.util;

import com.udemy.appws.SpringApplicationContext;
import com.udemy.appws.security.AppProperties;

public class SecurityConstant {
    private static final AppProperties APP_PROPERTIES
            = (AppProperties) SpringApplicationContext.getBean("appProperties");

    public static final String TOKEN_PREFIX = "Bearer ";

    public static final Integer ONE_DAY = 86400000;

    public static String getTokenSecret() {
        return APP_PROPERTIES.getTokenSecret();
    }

    public static String getHeaderName() {
        return APP_PROPERTIES.getHeaderName();
    }

    public static String getTokenPrefix(){
        return APP_PROPERTIES.getTokenPrefix();
    }

    public static Long getTokenExpiration(){
        return Long.parseLong(APP_PROPERTIES.getTokenExpiration());
    }
}
