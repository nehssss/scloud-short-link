package com.haitaos.controller;

import com.google.code.kaptcha.Producer;
import com.haitaos.service.NotifyService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
@RequestMapping("/api/account/v1")
@Slf4j
public class NotifyController {
    private Producer captchaProducer;

    private NotifyService notifyService;

    public NotifyController(Producer captchaProducer, NotifyService notifyService) {
        this.captchaProducer = captchaProducer;
        this.notifyService = notifyService;
    }

    /**
     * generate captcha
     * @param request
     * @param response
     */
    @GetMapping("captcha")
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response) {
        String captchaText = captchaProducer.createText();
        log.info("验证码内容:{}", captchaText);

        //store redis, set expiration time TODO

        BufferedImage bufferedImage = captchaProducer.createImage(captchaText);
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            ImageIO.write(bufferedImage, "jpg", outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            log.error("Get stream error:{}", e.getMessage());
        }
    }


}


