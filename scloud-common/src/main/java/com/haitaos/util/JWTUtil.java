package com.haitaos.util;

import com.haitaos.model.LoginUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.SecretKey;
import java.util.Date;

@Slf4j
public class JWTUtil {

  /** subject * */
  private static final String SUBJECT = "scloud";

  /**
   * secret key as AES encryption key key length must be 256 bytes and must be base64 encode this
   * key to config file TODO
   */
  private static final String SECRET = "Ok570d4DN33l2kTufXJI9EcOt33xQHZkRJfsYvZAY+4=";

  /** token prefix * */
  private static final String TOKEN_PREFIX = "scloud-link";

  /** expire time: 7 days* */
  private static final long EXPIRED = 1000 * 60 * 60 * 24 * 7;

  /**
   * generate json web token
   *
   * @param loginUser
   * @return
   */
  public static String generateJsonWebToken(LoginUser loginUser) {
    if (loginUser == null) {
      throw new NullPointerException("loginUser is null");
    }

    byte[] keyBytes = Decoders.BASE64.decode(SECRET);
    SecretKey secretKey = Keys.hmacShaKeyFor(keyBytes);

    String token =
        Jwts.builder()
            .setSubject(SUBJECT)
            // setting payload
            .claim("head_img", loginUser.getHeadImg())
            .claim("account_no", loginUser.getAccountNo())
            .claim("username", loginUser.getUsername())
            .claim("mail", loginUser.getMail())
            .claim("phone", loginUser.getPhone())
            .claim("auth", loginUser.getAuth())
            .setIssuedAt(new Date())
            .setExpiration(new Date(CommonUtil.getCurrentTimestamp() + EXPIRED))
            .signWith(secretKey, SignatureAlgorithm.HS256)
            .compact();
    token = TOKEN_PREFIX + token;
    return token;
  }

  public static Claims checkJWT(String token) {
    try {
      final Claims claims =
          Jwts.parser()
              .setSigningKey(SECRET)
              .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
              .getBody();
      return claims;
    } catch (Exception e) {
      log.error("jwt check failed:{}", e.getMessage());
      return null;
    }
  }
}
