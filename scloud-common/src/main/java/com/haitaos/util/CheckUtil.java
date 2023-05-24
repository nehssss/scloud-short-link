package com.haitaos.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckUtil {
  /** Email regular */
  private static final Pattern MAIL_PATTERN =
      Pattern.compile(
          "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");

  /** Phone number regular */
  private static final Pattern PHONE_PATTERN =
      Pattern.compile("^(\\+?1)?[2-9]\\d{2}[2-9](?!11)\\d{6}$");

  /**
   * check if email is valid
   *
   * @param email
   * @return
   */
  public static boolean isEmail(String email) {
    if (email == null || "".equals(email)) {
      return false;
    }
    Matcher m = MAIL_PATTERN.matcher(email);
    return m.matches();
  }

  /**
   * check if phone number is valid
   *
   * @param phone
   * @return
   */
  public static boolean isPhone(String phone) {
    if (null == phone || "".equals(phone)) {
      return false;
    }
    Matcher m = PHONE_PATTERN.matcher(phone);
    return m.matches();
  }
}
