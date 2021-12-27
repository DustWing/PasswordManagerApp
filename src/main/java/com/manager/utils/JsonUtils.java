package com.manager.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class JsonUtils {

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static <T> T toObject(final String json, Class<T> clazz) throws JsonProcessingException {
        return OBJECT_MAPPER.readValue(json, clazz);
    }

    public static <T> List<T> toList(final String json) throws JsonProcessingException {
        return OBJECT_MAPPER.readValue(json, new TypeReference<>() {
        });
    }

    public static String toJson(final Object o) throws JsonProcessingException {
        return OBJECT_MAPPER.writeValueAsString(o);
    }

}
