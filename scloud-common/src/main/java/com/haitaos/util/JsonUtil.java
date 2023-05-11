package com.haitaos.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.List;

@Slf4j
public class JsonUtil {

    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        // setting single quote
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);

        // serialize all attributes of the object
        mapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);

        // if there are extra attributes during deserialization, do not throw an exception
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // if it is an empty object, do not throw an exception
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        // cancel the time conversion format, the default is the timestamp, you can cancel it, and set the time format to be displayed at the same time
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    }

    /**
     * Object to Json string
     * @param obj
     * @return
     */

    public static String obj2Json(Object obj) {
        String jsonStr = null;
        try {
            jsonStr = mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("obj2Json error", e);
        }
        return jsonStr;
    }

    /**
     * Json string to Object
     * @param jsonStr
     * @param beanType
     * @param <T>
     * @return
     */

    public static <T> T json2Obj(String jsonStr, Class<T> beanType) {
        T obj = null;
        try {
            obj = mapper.readValue(jsonStr, beanType);
        } catch (Exception e) {
            log.error("json2Obj error", e);
        }
        return obj;
    }

    /**
     * Json data to List
     * @param jsonData
     * @param beanType
     * @return List<T>
     */

    public static <T> List<T> json2List(String jsonData, Class<T> beanType) {
        JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, beanType);
        List<T> list = null;
        try {
            list = mapper.readValue(jsonData, javaType);
        } catch (Exception e) {
            log.error("json2List error", e);
        }
        return list;
    }

    /**
     * Object to byte array
     * @param data
     * @return byte[]
     */

    public static byte[] obj2Bytes(Object data) {
        byte[] bytes = null;
        try {
            bytes = mapper.writeValueAsBytes(data);
        } catch (JsonProcessingException e) {
            log.error("obj2Bytes error", e);
        }
        return bytes;
    }

    /**
     * byte array to Object
     * @param bytes
     * @param beanType
     * @return T
     */

    public static <T> T bytes2Obj(byte[] bytes, Class<T> beanType) {
        T obj = null;
        try {
            obj = mapper.readValue(bytes, beanType);
        } catch (Exception e) {
            log.error("bytes2Obj error", e);
        }
        return obj;
    }
}
