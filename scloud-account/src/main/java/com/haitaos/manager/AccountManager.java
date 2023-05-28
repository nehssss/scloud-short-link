package com.haitaos.manager;

import com.haitaos.model.AccountDO;

import java.util.List;

public interface AccountManager {
  int insert(AccountDO accountDO);

  List<AccountDO> findByPhone(String phone);
}
