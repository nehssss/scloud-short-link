package com.haitaos.service;

import com.haitaos.controller.request.AccountRegisterRequest;
import com.haitaos.util.JsonData;

public interface AccountService {
  /**
   * user register
   *
   * @param accountRegisterRequest
   * @return
   */
  JsonData register(AccountRegisterRequest accountRegisterRequest);
}
