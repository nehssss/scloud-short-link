package com.haitaos.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "sms")
@Configuration
@Data
public class SmsConfiguration {

  private String templateId;

  private String appCode;
}
