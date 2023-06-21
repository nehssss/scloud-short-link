package com.haitaos.biz;

import com.google.common.hash.Hashing;
import com.haitaos.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class ShortLinkTest {
  @Test
  public void testMurMurHash() {
    for (int i = 0; i < 5; i++) {
      String originalUrl = "https://nehsss.com?id=" + CommonUtil.generateUUID();
      long padToLong = Hashing.murmur3_32().hashUnencodedChars(originalUrl).padToLong();
      log.info("murmur32={}", padToLong);
    }
  }
}
