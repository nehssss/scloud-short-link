package com.haitaos.interceptor;

import com.haitaos.enums.BizCodeEnum;
import com.haitaos.model.LoginUser;
import com.haitaos.util.CommonUtil;
import com.haitaos.util.JWTUtil;
import com.haitaos.util.JsonData;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jodd.net.HttpMethod;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

  public static ThreadLocal<LoginUser> threadLocal = new ThreadLocal<>();

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    HandlerInterceptor.super.preHandle(request, response, handler);
    if (HttpMethod.OPTIONS.toString().equalsIgnoreCase(request.getMethod())) {
      response.setStatus(HttpStatus.NO_CONTENT.value());
      return true;
    }
    String accessToken = request.getHeader("token");
    if (StringUtils.isBlank(accessToken)) {
      accessToken = request.getParameter("token");
    }

    if (StringUtils.isNotBlank(accessToken)) {
      Claims claims = JWTUtil.checkJWT(accessToken);
      if (claims == null) {
        // token is invalid
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        CommonUtil.sendJsonMessage(response, JsonData.buildResult(BizCodeEnum.ACCOUNT_UNLOGIN));
        return false;
      }

      long accountNo = Long.parseLong(claims.get("account_no").toString());
      String headImg = claims.get("head_img").toString();
      String userName = claims.get("username").toString();
      String mail = claims.get("mail").toString();
      String phone = claims.get("phone").toString();
      String auto = claims.get("auth").toString();

      LoginUser loginUser =
          LoginUser.builder()
              .accountNo(accountNo)
              .headImg(headImg)
              .userName(userName)
              .mail(mail)
              .phone(phone)
              .auth(auto)
              .build();

      // request.setAttribute("LoginUser", loginUser);
      // Using ThreadLocal to store LoginUser
      threadLocal.set(loginUser);

      return true;
    }
    return false;
  }

  @Override
  public void postHandle(
      HttpServletRequest request,
      HttpServletResponse response,
      Object handler,
      ModelAndView modelAndView)
      throws Exception {
    HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
  }

  @Override
  public void afterCompletion(
      HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
      throws Exception {
    threadLocal.remove();
    HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
  }
}
