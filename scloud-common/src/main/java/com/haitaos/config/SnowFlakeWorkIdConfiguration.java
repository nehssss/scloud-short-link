package com.haitaos.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
@Slf4j
public class SnowFlakeWorkIdConfiguration {

  /**
   * Dynamically specify the wordId property in the sharding jdbc's snowflake algorithm property
   * This is achieved by calling System.setProperty() with the id of the container or the machine
   * identification bit. workId max 1L << 100, which is 1024, i.e. 0 <= workId < 1024 {@link
   * SnowflakeShardingKeyGenerator#getWorkerId()}
   */
  static {
    try {
      InetAddress inetAddress = Inet4Address.getLocalHost();

      String hostAddressIp = inetAddress.getHostAddress();

      String workId = String.valueOf(Math.abs(hostAddressIp.hashCode()) % 1024);

      System.setProperty("work-id", workId);

      log.info("workId:{}", System.getProperty("workId"));

    } catch (UnknownHostException e) {
      e.printStackTrace();
    }
  }
}
