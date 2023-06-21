package com.haitaos.util;

import com.google.common.hash.Hashing;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Slf4j
public class CommonUtil {

  /**
   * get ip address
   *
   * @param request
   * @return String
   */
  public static String getIpAddr(HttpServletRequest request) {
    String ipAddress = null;
    try {
      ipAddress = request.getHeader("x-forwarded-for");
      if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
        ipAddress = request.getHeader("Proxy-Client-IP");
      }
      if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
        ipAddress = request.getHeader("WL-Proxy-Client-IP");
      }
      if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
        ipAddress = request.getRemoteAddr();
        if (ipAddress.equals("127.0.0.1")) {
          // get local ip address
          InetAddress inet = null;
          try {
            inet = InetAddress.getLocalHost();
          } catch (UnknownHostException e) {
            e.printStackTrace();
          }
          ipAddress = inet.getHostAddress();
        }
      }
      // if there are multiple proxies, the first ip is the real ip, and the multiple ips are
      // separated by ','
      if (ipAddress != null && ipAddress.length() > 15) {
        // "***.***.***.***".length()
        // = 15
        if (ipAddress.indexOf(",") > 0) {
          ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
        }
      }
    } catch (Exception e) {
      ipAddress = "";
    }
    return ipAddress;
  }

  /**
   * get all request headers
   *
   * @param request
   * @return String
   */
  public static Map<String, String> getAllRequestHeaders(HttpServletRequest request) {
    Map<String, String> headers = new HashMap<>();
    Enumeration<String> headerNames = request.getHeaderNames();
    while (headerNames.hasMoreElements()) {
      String headerName = headerNames.nextElement();
      headers.put(headerName, request.getHeader(headerName));
    }
    return headers;
  }

  /**
   * MD5 encryption
   *
   * @param data
   * @return String
   */
  public static String MD5(String data) {
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      byte[] array = md.digest(data.getBytes("UTF-8"));
      StringBuilder sb = new StringBuilder();
      for (byte item : array) {
        sb.append(Integer.toHexString((item & 0xFF) | 0x100), 1, 3);
      }
      return sb.toString().toUpperCase();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * get random code
   *
   * @param length
   * @return String
   */
  public static String getRandomCode(int length) {

    String sources = "0123456789";
    Random random = new Random();
    StringBuilder sb = new StringBuilder();
    for (int j = 0; j < length; j++) {
      sb.append(sources.charAt(random.nextInt(9)));
    }
    return sb.toString();
  }

  /**
   * get current timestamp
   *
   * @retur long
   */
  public static long getCurrentTimestamp() {
    return System.currentTimeMillis();
  }

  /**
   * generate uuid
   *
   * @return String
   */
  public static String generateUUID() {
    return java.util.UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32);
  }

  /**
   * generate random string
   *
   * @param length
   * @return String
   */
  private static final String ALL_CHAR_NUM =
      "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

  public static String getStringNumRandom(int length) {
    // random number and alphabet
    Random random = new Random();
    StringBuilder saltString = new StringBuilder(length);
    for (int i = 1; i <= length; i++) {
      saltString.append(ALL_CHAR_NUM.charAt(random.nextInt(ALL_CHAR_NUM.length())));
    }
    return saltString.toString();
  }

  /**
   * response json data to client
   *
   * @param response
   * @param obj
   */
  public static void sendJsonMessage(HttpServletResponse response, Object obj) {
    response.setContentType("application/json; charset=utf-8");

    try (PrintWriter writer = response.getWriter()) {
      writer.print(JsonUtil.obj2Json(obj));
      response.flushBuffer();
    } catch (IOException e) {
      log.warn("sendJsonMessage error:{}", e.getMessage());
    }
  }

  /**
   * murmurhash algorithm
   * @param param
   * @return
   */
  public static long murmurHash32(String param) {
    return Hashing.murmur3_32().hashUnencodedChars(param).padToLong();
  }
}
