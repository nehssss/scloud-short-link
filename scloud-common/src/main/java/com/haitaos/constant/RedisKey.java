package com.haitaos.constant;

public class RedisKey {

  /**
   * Verification code, first parameter is the type of verification code, second parameter is the
   * phone number or email address
   */
  public static final String CHECK_CODE_KEY = "code:%s:%s";
}
