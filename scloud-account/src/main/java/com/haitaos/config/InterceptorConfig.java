package com.haitaos.config;

import com.haitaos.interceptor.LoginInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Slf4j
public class InterceptorConfig implements WebMvcConfigurer {
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry
        .addInterceptor(new LoginInterceptor())
        // add interceptor routes
        .addPathPatterns("/api/account/*/**", "/api/traffic/*/**")
        // exclude interceptor routes
        .excludePathPatterns(
            "/api/account/*/register",
            "/api/account/*/login",
            "/api/notify/*/captcha",
            "/api/account/*/upload",
            "/api/notify/*/send_code");
  }
}
