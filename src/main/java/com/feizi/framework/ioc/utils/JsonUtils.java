package com.feizi.framework.ioc.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;

/**
 * 处理Json格式数据的工具类
 * Created by feizi on 2018/1/22.
 */
public final class JsonUtils {
    private final static ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        mapper.getDeserializationConfig().withoutFeatures(new DeserializationFeature[]{DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES});
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        mapper.configure(JsonParser.Feature.ALLOW_YAML_COMMENTS, true);
        mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    private JsonUtils(){

    }

    public static ObjectMapper getMapper(){
        return mapper;
    }

    public static <T> T readValue(String json, Class<T> tClass){
        try {
            return mapper.readValue(json, tClass);
        } catch (IOException e) {
            return null;
        }
    }

    public static <T> T readValue(InputStream inputStream, Class<T> tClass){
        try {
            return mapper.readValue(inputStream, tClass);
        } catch (IOException e) {
            return null;
        }
    }

    public static <T> T readValue(byte[] bytes, Class<T> tClass){
        try {
            return mapper.readValue(bytes, tClass);
        } catch (IOException e) {
            return null;
        }
    }

    public static <T> T readValue(String json, TypeReference typeReference){
        try {
            return mapper.readValue(json, typeReference);
        } catch (IOException e) {
            return null;
        }
    }

    public static <T> T readValue(byte[] bytes, TypeReference typeReference){
        try {
            return mapper.readValue(bytes, typeReference);
        } catch (IOException e) {
            return null;
        }
    }

    public static <T> T readValue(InputStream inputStream, TypeReference typeReference){
        try {
            return mapper.readValue(inputStream, typeReference);
        } catch (IOException e) {
            return null;
        }
    }

    public static String writeValue(Object entity){
        try {
            return mapper.writeValueAsString(entity);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    public static byte[] writeByValue(Object entity){
        try {
            return mapper.writeValueAsBytes(entity);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
