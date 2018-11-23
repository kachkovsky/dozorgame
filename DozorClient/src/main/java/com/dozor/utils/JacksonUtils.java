package com.dozor.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JacksonUtils {
    private static final ThreadLocal<ObjectMapper> OBJECT_MAPPER_THREAD_LOCAL = new ThreadLocal<ObjectMapper>() {
        @Override
        protected ObjectMapper initialValue() {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return objectMapper;
        }
    };

    public static <T> T parseJsonString(Class<T> clazz, String jsonString) throws IOException {
        ObjectMapper objectMapper = OBJECT_MAPPER_THREAD_LOCAL.get();
        return objectMapper.readValue(jsonString, clazz);
    }
}
