package com.haitaos.controller.request;

import lombok.Data;

@Data
public class SendCodeRequest {
  private String captcha;
  private String to;
}
