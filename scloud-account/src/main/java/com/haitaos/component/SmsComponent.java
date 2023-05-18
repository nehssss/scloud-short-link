package com.haitaos.component;

import com.haitaos.config.SmsConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class SmsComponent {

  /** sending address */
  private static final String URL_TEMPLATE =
      "https://jmsms.market.alicloudapi.com/sms/send?mobile=%s&templateId=%s&value=%s";

  private RestTemplate restTemplate;

  private SmsConfig smsConfig;

  public SmsComponent(RestTemplate restTemplate, SmsConfig smsConfig) {
    this.restTemplate = restTemplate;
    this.smsConfig = smsConfig;
  }

  public void send(String to, String templateId, String value) {
    String url = String.format(URL_TEMPLATE, to, templateId, value);
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.set("Authoration", "APPCODE " + smsConfig.getAppCode());
    HttpEntity entity = new HttpEntity(httpHeaders);
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