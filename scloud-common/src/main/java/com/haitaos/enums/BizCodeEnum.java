package com.haitaos.enums;

import lombok.Getter;

/**
 * @Description: Status code definition constraints, a total of 6 digits, the first three represent
 * the service, the last three represent the interface For example, product service 210, shopping
 * cart is 220, user service 230, 403 represents permission
 */
public enum BizCodeEnum {
  /** short link group */
  GROUP_REPEAT(23001, "Group name repeat"),
  GROUP_OPER_FAIL(23503, "Group name operation failed"),
  GROUP_NOT_EXIST(23404, "Group does not exist"),

  GROUP_ADD_FAIL(23405, "Group add failed"),

  /** verification code */
  CODE_TO_ERROR(240001, "The receiving number is not compliant"),
  CODE_LIMITED(240002, "Verification code sent too fast"),
  CODE_ERROR(240003, " Verification code error"),
  CODE_CAPTCHA_ERROR(240101, "Graphic verification code error"),

  /** account */
  ACCOUNT_REPEAT(250001, "Account already exists"),
  ACCOUNT_UNREGISTER(250002, "Account does not exist"),
  ACCOUNT_PWD_ERROR(250003, "Account or password error"),
  ACCOUNT_UNLOGIN(250004, "Account not logged in"),

  /** short link */
  SHORT_LINK_NOT_EXIST(260404, "Short link does not exist"),

  /** order */
  ORDER_CONFIRM_PRICE_FAIL(280002, "Create order-verify price failed"),
  ORDER_CONFIRM_REPEAT(280008, "Order malicious-repeated submission"),
  ORDER_CONFIRM_TOKEN_EQUAL_FAIL(280009, "Order token missing"),
  ORDER_CONFIRM_NOT_EXIST(280010, "Order does not exist"),

  /** payment */
  PAY_ORDER_FAIL(300001, "Failed to create payment order"),
  PAY_ORDER_CALLBACK_SIGN_FAIL(300002, "Payment order callback verification signature failed"),
  PAY_ORDER_CALLBACK_NOT_SUCCESS(300003, "GooglePay callback failed to update order"),
  PAY_ORDER_NOT_EXIST(300005, "Payment order does not exist"),
  PAY_ORDER_STATE_ERROR(300006, "Order status is abnormal"),
  PAY_ORDER_PAY_TIMEOUT(300007, "Order payment timeout"),

  /** flow control operation */
  CONTROL_FLOW(500101, "flow control"),
  CONTROL_DEGRADE(500201, "degrade control"),
  CONTROL_AUTH(500301, "auth control"),

  /** traffic package operation */
  TRAFFIC_FREE_NOT_EXIST(600101, "Free traffic package does not exist, contact customer service"),

  TRAFFIC_REDUCE_FAIL(600102, "Insufficient traffic, deduction failure"),

  TRAFFIC_EXCEPTION(600103, "Traffic pack data abnormal, user no traffic pack"),

  /** common operation code */
  OPS_REPEAT(110001, "Repeat operation"),
  OPS_NETWORK_ADDRESS_ERROR(110002, "Network address error"),

  /** file related */
  FILE_UPLOAD_USER_IMG_FAIL(700101, "User avatar file upload failed");

  @Getter private String message;

  @Getter private int code;

  private BizCodeEnum(int code, String message) {
    this.code = code;
    this.message = message;
  }
}
