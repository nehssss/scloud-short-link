package com.haitaos.util;

import com.haitaos.exception.BizException;
import com.haitaos.model.LoginUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
public class JWTUtil {

  /** subject * */
  private static final String SUBJECT = "scloud";

  /** secret key* */
  private static final String SECRET = "123456";

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

    String token =
        Jwts.builder()
            .setSubject(SUBJECT)
            // setting payload
            .claim("head)img", loginUser.getHeadImg())
            .claim("username", loginUser.getUserName())
            .claim("mail", loginUser.getMail())
            .claim("phone", loginUser.getPhone())
            .claim("auth", loginUser.getAuth())
            .setIssuedAt(new Date())
            .setExpiration(new Date(CommonUtil.getCurrentTimestamp() + EXPIRED))
            .signWith(SignatureAlgorithm.HS256, SECRET)
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
