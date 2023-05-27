package com.haitaos.service;

import com.haitaos.enums.SendCodeEnum;
import com.haitaos.util.JsonData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

public interface NotifyService {
  /**
   * Send verification code
   *
   * @param userRegister
   * @param to
   * @return
   */
  JsonData sendCode(SendCodeEnum userRegister, String to);

  /**
   * check verification code
   *
   * @param sendCodeEnum
   * @param to
   * @param code
   * @return
   */
  boolean checkCode(SendCodeEnum sendCodeEnum, String to, String code);
}
