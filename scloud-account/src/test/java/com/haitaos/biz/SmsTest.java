package com.haitaos.biz;

import com.haitaos.component.SmsComponent;
import com.haitaos.config.SmsConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class SmsTest {

  @Autowired private SmsComponent smsComponent;

  @Autowired private SmsConfiguration smsConfiguration;

  @Test
  public void testSendSms() {
    smsComponent.send("11111111111", smsConfiguration.getTemplateId(), "123456");
  }
}
