package com.haitaos.service.impl;

import com.haitaos.controller.request.AccountLoginRequest;
import com.haitaos.controller.request.AccountRegisterRequest;
import com.haitaos.enums.AuthTypeEnum;
import com.haitaos.enums.BizCodeEnum;
import com.haitaos.enums.SendCodeEnum;
import com.haitaos.manager.AccountManager;
import com.haitaos.model.AccountDO;
import com.haitaos.model.LoginUser;
import com.haitaos.service.AccountService;
import com.haitaos.service.NotifyService;
import com.haitaos.util.CommonUtil;
import com.haitaos.util.JWTUtil;
import com.haitaos.util.JsonData;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

  private NotifyService notifyService;

  private AccountManager accountManager;

  public AccountServiceImpl(NotifyService notifyService, AccountManager accountManager) {
    super();
    this.notifyService = notifyService;
    this.accountManager = accountManager;
  }

  @Override
  public JsonData register(AccountRegisterRequest accountRegisterRequest) {
    boolean checkCode = false;
    // check verification code
    if (StringUtils.isNotBlank(accountRegisterRequest.getPhone())) {
      checkCode =
          notifyService.checkCode(
              SendCodeEnum.USER_REGISTER,
              accountRegisterRequest.getPhone(),
              accountRegisterRequest.getCode());
    }
    if (!checkCode) {
      return JsonData.buildResult(BizCodeEnum.CODE_ERROR);
    }

    AccountDO accountDO = new AccountDO();
    BeanUtils.copyProperties(accountRegisterRequest, accountDO);

    // set default authorization level
    accountDO.setAuth(AuthTypeEnum.DEFAULT.name());

    // generate unify account number TODO
    accountDO.setAccountNo(CommonUtil.getCurrentTimestamp());

    // setting password
    accountDO.setSecret("$1$" + CommonUtil.getStringNumRandom(8));
    String cryptPwd =
        Md5Crypt.md5Crypt(accountRegisterRequest.getPwd().getBytes(), accountDO.getSecret());
    accountDO.setPwd(cryptPwd);

    int rows = accountManager.insert(accountDO);
    log.info("rows:{}, register success", rows);

    // user registration success, give a default traffic task
    userRegisterInitTask(accountDO);
    return null;
  }

  /**
   * user login 1. according phone number to find user
   *
   * @param accountLoginRequest
   * @return
   */
  @Override
  public JsonData login(AccountLoginRequest accountLoginRequest) {
    List<AccountDO> accountDOList = accountManager.findByPhone(accountLoginRequest.getPhone());
    if (accountDOList != null || accountDOList.size() == 1) {
      AccountDO accountDO = accountDOList.get(0);
      String md5Crypt =
          Md5Crypt.md5Crypt(accountLoginRequest.getPwd().getBytes(), accountDO.getSecret());
      if (md5Crypt.equals(accountDO.getPwd())) {
        LoginUser loginUser = LoginUser.builder().build();
        BeanUtils.copyProperties(accountDO, loginUser);
        // generate token
        String token = JWTUtil.generateJsonWebToken(loginUser);
        return JsonData.buildSuccess(token);
      } else {
        return JsonData.buildResult(BizCodeEnum.ACCOUNT_PWD_ERROR);
      }
    } else {
      return JsonData.buildResult(BizCodeEnum.ACCOUNT_UNREGISTER);
    }
  }

  /**
   * user initialization, give a default traffic task
   *
   * @param accountDO
   */
  private void userRegisterInitTask(AccountDO accountDO) {}
}
