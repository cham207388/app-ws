package com.udemy.appws.util;

import java.security.SecureRandom;
import java.util.Random;

public class RandomString {
    private static final String ALPHA_NUMERIC = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final Random random = new SecureRandom();

    public static String randomString(){
        return generateRandomString(30);
    }

    private static String generateRandomString(int length) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < length; i++) {
            builder.append(ALPHA_NUMERIC.charAt(random.nextInt(ALPHA_NUMERIC.length())));
        }
        return new String(builder);
    }

    public static void main(String[] args) {
        System.out.println(randomString());
    }
}
