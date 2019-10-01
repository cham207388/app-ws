package com.udemy.appws.util;

@FunctionalInterface
public interface Converter<T, R> {
    R convert(T t);
}
