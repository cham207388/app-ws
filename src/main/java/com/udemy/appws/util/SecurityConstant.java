package com.udemy.appws.util;

import com.udemy.appws.SpringApplicationContext;
import com.udemy.appws.security.AppProperties;

public class SecurityConstant {
    //public static final String TOKEN_SECRET = "";
    public static final String TOKEN_PREFIX = "Bearer ";
    //public static final String HEADER_STRING = "Authorization";
    public static final Integer ONE_DAY = 86400000;

    public static String getTokenSecret() {
        AppProperties appProperties
                = (AppProperties) SpringApplicationContext.getBean("appProperties");
        return appProperties.getTokenSecret();
    }

    public static String getHeaderName() {
        AppProperties appProperties
                = (AppProperties) SpringApplicationContext.getBean("appProperties");
        return appProperties.getHeaderName();
    }
}
