package com.haitaos.service.impl;

import com.haitaos.component.SmsComponent;
import com.haitaos.service.NotifyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotifyServiceImpl implements NotifyService {
  private SmsComponent smsComponent;

  public NotifyServiceImpl(SmsComponent smsComponent) {
    super();
    this.smsComponent = smsComponent;
  }

  @Override
  @Async
  public void end(String phone, String templateId, String code) {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    // smsComponent.send(phone, templateId, code);
  }
}
