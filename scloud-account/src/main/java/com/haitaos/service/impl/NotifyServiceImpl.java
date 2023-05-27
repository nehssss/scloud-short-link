package com.haitaos.service.impl;

import com.haitaos.component.SmsComponent;
import com.haitaos.config.SmsConfig;
import com.haitaos.constant.RedisKey;
import com.haitaos.enums.BizCodeEnum;
import com.haitaos.enums.SendCodeEnum;
import com.haitaos.service.NotifyService;
import com.haitaos.util.CheckUtil;
import com.haitaos.util.CommonUtil;
import com.haitaos.util.JsonData;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class NotifyServiceImpl implements NotifyService {

  // 10 minutes expiration time
  private static final int CODE_EXPIRED = 60 * 1000 * 10;

  private SmsComponent smsComponent;

  private SmsConfig smsConfig;
  private RestTemplate restTemplate;

  private StringRedisTemplate stringRedisTemplate;

  public NotifyServiceImpl(
      SmsComponent smsComponent,
      SmsConfig smsConfig,
      RestTemplate restTemplate,
      StringRedisTemplate stringRedisTemplate) {
    this.smsComponent = smsComponent;
    this.smsConfig = smsConfig;
    this.restTemplate = restTemplate;
    this.stringRedisTemplate = stringRedisTemplate;
  }

  @Override
  public JsonData sendCode(SendCodeEnum sendCodeEnum, String to) {

    String cacheKey = String.format(RedisKey.CHECK_CODE_KEY, sendCodeEnum.name(), to);

    String cacheValue = stringRedisTemplate.opsForValue().get(cacheKey);

    // If not null, and then determine whether the time interval is less than 60 seconds
    if (StringUtils.isNotBlank(cacheValue)) {
      long ttl = Long.parseLong(cacheValue.split(" ")[1]);
      // Current timestamp minus the timestamp in the cache, if less than 60 seconds, not allow
      // repeat send
      long leftTime = CommonUtil.getCurrentTimestamp() - ttl;
      if (leftTime < 1000 * 60) {
        log.info("Repeat send code, interval time:{}", leftTime);
        return JsonData.buildResult(BizCodeEnum.CODE_LIMITED);
      }
    }

    // Generate 6-digit verification code
    String code = CommonUtil.getRandomCode(6);
    // concat code and timestamp
    String value = code + "_" + CommonUtil.getCurrentTimestamp();
    stringRedisTemplate.opsForValue().set(cacheKey, value, CODE_EXPIRED, TimeUnit.MILLISECONDS);

    if (CheckUtil.isEmail(to)) {
      // send email TODO
    } else if (CheckUtil.isPhone(to)) {
      // send sms verification code
      smsComponent.send(to, smsConfig.getTemplateId(), code);
      return JsonData.buildSuccess();
    }
    return JsonData.buildError("Send code failed");
  }

  /**
   * check verification code logic
   *
   * @param sendCodeEnum
   * @param to
   * @param code
   * @return
   */
  @Override
  public boolean checkCode(SendCodeEnum sendCodeEnum, String to, String code) {
    String cacheKey = String.format(RedisKey.CHECK_CODE_KEY, sendCodeEnum.name(), to);

    String cacheValue = stringRedisTemplate.opsForValue().get(cacheKey);

    if (StringUtils.isNotBlank(cacheValue)) {
      String cacheCode = cacheValue.split("_")[0];
      if (cacheCode.equals(code)) {
        // delete cache
        stringRedisTemplate.delete(cacheKey);
        return true;
      }
    }
    return false;
  }
}
