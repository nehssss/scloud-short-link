package com.haitaos.component;

import com.haitaos.config.SmsConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
@Slf4j
public class SmsComponent {

  /** sending address */
  private static final String URL_TEMPLATE =
      "https://jmsms.market.alicloudapi.com/sms/send?mobile=%s&templateId=%s&value=%s";

  private RestTemplate restTemplate;

  private SmsConfiguration smsConfiguration;

  public SmsComponent(RestTemplate restTemplate, SmsConfiguration smsConfiguration) {
    this.restTemplate = restTemplate;
    this.smsConfiguration = smsConfiguration;
  }

  @Async
  public void send(String to, String templateId, String value) {
    String url = String.format(URL_TEMPLATE, to, templateId, value);
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.set("Authorization", "APPCODE " + smsConfiguration.getAppCode());
    HttpEntity<Map<String, String>> entity = new HttpEntity<>(httpHeaders);
    ResponseEntity<String> response =
        restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
    log.info("url={}, body{}", url, response.getBody());
    if (response.getStatusCode().is2xxSuccessful()) {
      log.info("send sms success");
    } else {
      log.error("send sms failed:{}", response.getBody());
    }
  }
}
