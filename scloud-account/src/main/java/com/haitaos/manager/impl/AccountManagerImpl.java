package com.haitaos.manager.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.haitaos.manager.AccountManager;
import com.haitaos.mapper.AccountMapper;
import com.haitaos.model.AccountDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class AccountManagerImpl implements AccountManager {

  private AccountMapper accountMapper;

  public AccountManagerImpl(AccountMapper accountMapper) {
    super();
    this.accountMapper = accountMapper;
  }

  @Override
  public int insert(AccountDO accountDO) {
    return accountMapper.insert(accountDO);
  }

  @Override
  public List<AccountDO> findByPhone(String phone) {
    List<AccountDO> accountDOList =
        accountMapper.selectList(new QueryWrapper<AccountDO>().eq("phone", phone));
    return accountDOList;
  }
}
