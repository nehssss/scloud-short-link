package com.haitaos.component;

import com.haitaos.util.CommonUtil;
import org.springframework.stereotype.Component;

@Component
public class ShortLinkComponent {
  private static final String BASE62 =
      "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
  /**
   * create short link code
   *
   * @param param
   * @return
   */
  public String createShortLinkCode(String param) {
    var murmurhash = CommonUtil.murmurHash32(param);
    // Incremental conversion
    return encodeToBase62(murmurhash);
  }

  /**
   * base10 encode to base62
   *
   * @param num
   * @return
   */
  private String encodeToBase62(long num) {
    // StringBuffer is thread safe, StringBuilder is not thread safe
    StringBuffer sb = new StringBuffer();
    do {
      int i = (int) (num % 62);
      sb.append(BASE62.charAt(i));
      num /= 62;
    } while (num > 0);
    return sb.reverse().toString();
  }
}
