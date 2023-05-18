package com.haitaos.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("account")
public class AccountDO implements Serializable {

  private static final long serialVersionUID = 1L;

  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  private Long accountNo;

  /** avatar */
  private String headImg;

  /** phone number */
  private String phone;

  /** password */
  private String pwd;

  /** salt for sensitive personal information handling */
  private String secret;

  /** email */
  private String mail;

  /** user name */
  private String username;

  /** authority level:，DEFAULT，REALNAME，ENTERPRISE，different request times */
  private String auth;

  private Date gmtCreate;

  private Date gmtModified;
}
