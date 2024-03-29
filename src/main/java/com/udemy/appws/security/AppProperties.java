package com.udemy.appws.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class AppProperties {

    @Autowired
    private Environment env;

    public String getTokenSecret() {
        return env.getProperty("tokenSecret");
    }


    public String getHeaderName(){
        return env.getProperty("headerName");
    }

    public String getTokenPrefix() {
        return env.getProperty("token.prefix");
    }

    public String getTokenExpiration() {
        return env.getProperty("token.expiration");
    }
}
