package com.haitaos.config;

import com.haitaos.interceptor.LoginInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Slf4j
public class InterceptorConfiguration implements WebMvcConfigurer {
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry
        .addInterceptor(new LoginInterceptor())
        // add interceptor routes
        .addPathPatterns("/api/link/*/**", "/api/group/*/**", "/api/domain/*/**")
        // exclude interceptor routes
        .excludePathPatterns("");
  }
}
