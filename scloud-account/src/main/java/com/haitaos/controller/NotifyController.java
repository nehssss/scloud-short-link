package com.haitaos.controller;

import com.google.code.kaptcha.Producer;
import com.haitaos.controller.request.SendCodeRequest;
import com.haitaos.enums.BizCodeEnum;
import com.haitaos.enums.SendCodeEnum;
import com.haitaos.service.NotifyService;
import com.haitaos.util.CommonUtil;
import com.haitaos.util.JsonData;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/account/v1")
@Slf4j
public class NotifyController {
  private Producer captchaProducer;

  private NotifyService notifyService;

  private StringRedisTemplate redisTemplate;

  /** Captcha expiration time */
  private static final long CAPTCHA_EXPIRATION = 1000 * 10 * 60;

  public NotifyController(
      Producer captchaProducer, NotifyService notifyService, StringRedisTemplate redisTemplate) {
    this.captchaProducer = captchaProducer;
    this.notifyService = notifyService;
    this.redisTemplate = redisTemplate;
  }

  /**
   * generate captcha
   *
   * @param request
   * @param response
   */
  @GetMapping("captcha")
  public void getCaptcha(HttpServletRequest request, HttpServletResponse response) {
    String captchaText = captchaProducer.createText();
    log.info("Captcha Content:{}", captchaText);

    // store redis, set expiration time
    this.redisTemplate
        .opsForValue()
        .set(getCaptchaKey(request), captchaText, CAPTCHA_EXPIRATION, TimeUnit.MILLISECONDS);

    BufferedImage bufferedImage = captchaProducer.createImage(captchaText);
    try (ServletOutputStream outputStream = response.getOutputStream()) {
      ImageIO.write(bufferedImage, "jpg", outputStream);
      outputStream.flush();
    } catch (IOException e) {
      log.error("Get stream error:{}", e.getMessage());
    }
  }

  private String getCaptchaKey(HttpServletRequest httpServletRequest) {
    String ip = CommonUtil.getIpAddr(httpServletRequest);
    String userAgent = httpServletRequest.getHeader("User-Agent");

    String key = "account-service:captcha:" + CommonUtil.MD5(ip + userAgent);
    log.info("Captcha key:{}", key);
    return key;
  }

  @PostMapping("send_code")
  public JsonData sendCode(
      @RequestBody SendCodeRequest sendCodeRequest, HttpServletRequest request) {
    String key = getCaptchaKey(request);
    String cacheCaptcha = redisTemplate.opsForValue().get(key);
    String captcha = sendCodeRequest.getCaptcha();
    if (captcha != null && cacheCaptcha != null && cacheCaptcha.equalsIgnoreCase(captcha)) {
      // success
      redisTemplate.delete(key);
      // send code
      JsonData jsonData =
          notifyService.sendCode(SendCodeEnum.USER_REGISTER, sendCodeRequest.getTo());
      return jsonData;
    } else {
      return JsonData.buildResult(BizCodeEnum.CODE_CAPTCHA_ERROR);
    }
  }
}
