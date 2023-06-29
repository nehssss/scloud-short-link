package com.haitaos.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginUser {
  /** account number */
  private long accountNo;
  /** user name */
  private String username;
  /** user avatar */
  private String headImg;
  /** email */
  private String mail;
  /** phone */
  private String phone;
  /** authority level */
  private String auth;
}
