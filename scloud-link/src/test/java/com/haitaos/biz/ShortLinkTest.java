package com.haitaos.biz;

import com.google.common.hash.Hashing;
import com.haitaos.component.ShortLinkComponent;
import com.haitaos.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

@SpringBootTest
@Slf4j
public class ShortLinkTest {

  @Autowired private ShortLinkComponent shortLinkComponent;

  @Test
  public void testMurMurHash() {
    for (int i = 0; i < 5; i++) {
      String originalUrl = "https://nehsss.com?id=" + CommonUtil.generateUUID();
      long padToLong = Hashing.murmur3_32().hashUnencodedChars(originalUrl).padToLong();
      log.info("murmur32={}", padToLong);
    }
  }

  @Test
  public void testCreateShortLink() {
    Random random = new Random();
    for (int i = 0; i < 100; i++) {
      int num1 = random.nextInt(100);
      int num2 = random.nextInt(100000);
      int num3 = random.nextInt(100000);
      String originalUrl = "https://nehsss.com?id=" + num1 + num2 + num3;
      String shortLinkCode = shortLinkComponent.createShortLinkCode(originalUrl);
      log.info("originalUrl={}, shortLinkCode={}", originalUrl, shortLinkCode);
    }
  }
}
