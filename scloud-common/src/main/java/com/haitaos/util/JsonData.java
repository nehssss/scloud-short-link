package com.haitaos.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.haitaos.enums.BizCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonData {

  /** status code 0 indicates success */
  private Integer code;
  /** data */
  private Object data;
  /** description */
  private String msg;

  /**
   * get remote call data caution: support for multi-word underscore-specific humps (serialization
   * and deserialization)
   *
   * @param typeReference
   * @param <T>
   * @return
   */
  public <T> T getData(TypeReference<T> typeReference) {
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      return objectMapper.readValue(objectMapper.writeValueAsString(data), typeReference);
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Error in parsing JSON data", e);
    }
  }

  /**
   * success, no incoming data
   *
   * @return
   */
  public static JsonData buildSuccess() {
    return new JsonData(0, null, null);
  }

  /**
   * success, incoming data
   *
   * @param data
   * @return
   */
  public static JsonData buildSuccess(Object data) {
    return new JsonData(0, data, null);
  }

  /**
   * failed, incoming description information
   *
   * @param msg
   * @return
   */
  public static JsonData buildError(String msg) {
    return new JsonData(-1, null, msg);
  }

  /**
   * customized status codes and error messages
   *
   * @param code
   * @param msg
   * @return
   */
  public static JsonData buildCodeAndMsg(int code, String msg) {
    return new JsonData(code, null, msg);
  }

  /**
   * pass in the enumeration and return the information
   *
   * @param codeEnum
   * @return
   */
  public static JsonData buildResult(BizCodeEnum codeEnum) {
    return JsonData.buildCodeAndMsg(codeEnum.getCode(), codeEnum.getMessage());
  }
}
