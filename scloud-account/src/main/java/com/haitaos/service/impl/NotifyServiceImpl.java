package com.haitaos.service.impl;

import com.haitaos.component.SmsComponent;
import com.haitaos.config.SmsConfig;
import com.haitaos.enums.SendCodeEnum;
import com.haitaos.service.NotifyService;
import com.haitaos.util.CheckUtil;
import com.haitaos.util.CommonUtil;
import com.haitaos.util.JsonData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class NotifyServiceImpl implements NotifyService {

  private SmsComponent smsComponent;

  private SmsConfig smsConfig;
  private RestTemplate restTemplate;

  public NotifyServiceImpl(
      SmsComponent smsComponent, SmsConfig smsConfig, RestTemplate restTemplate) {
    this.smsComponent = smsComponent;
    this.smsConfig = smsConfig;
    this.restTemplate = restTemplate;
  }

  @Override
  public JsonData sendCode(SendCodeEnum userRegister, String to) {
    String code = CommonUtil.getRandomCode(6);
    if (CheckUtil.isEmail(to)) {
      // send email TODO
    } else if (CheckUtil.isPhone(to)) {
      // send sms verification code
      smsComponent.send(to, smsConfig.getTemplateId(), code);
      return JsonData.buildSuccess();
    }
    return JsonData.buildError("Send code failed");
  }
}
