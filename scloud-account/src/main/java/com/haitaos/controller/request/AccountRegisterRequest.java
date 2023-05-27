package com.haitaos.controller.request;

import lombok.Data;

@Data
public class AccountRegisterRequest {
    /** avatar */
    private String headImg;

    /** phone number */
    private String phone;

    /** password */
    private String pwd;

    /** email */
    private String mail;

    /** user name */
    private String username;

    /** verification code */
    private String code;

}
