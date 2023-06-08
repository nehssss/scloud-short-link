package com.haitaos.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "spring.cloud.aws.s3")
@Configuration
@Data
public class S3Configuration {
  private String bucketName;
}
