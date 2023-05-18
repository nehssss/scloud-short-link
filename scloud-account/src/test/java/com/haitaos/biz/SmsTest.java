package com.haitaos.biz;

import com.haitaos.component.SmsComponent;
import com.haitaos.config.SmsConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class SmsTest {

    private SmsComponent smsComponent;

    private SmsConfig smsConfig;

    public SmsTest(SmsComponent smsComponent, SmsConfig smsConfig){
        this.smsComponent = smsComponent;
        this.smsConfig = smsConfig;
    }

    @Test
    public void testSendSms() {

    }
}
