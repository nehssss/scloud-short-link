package com.haitaos.service.impl;

import com.haitaos.controller.request.LinkGroupAddRequest;
import com.haitaos.interceptor.LoginInterceptor;
import com.haitaos.manager.LinkGroupManager;
import com.haitaos.model.LinkGroupDO;
import com.haitaos.service.LinkGroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LinkGroupServiceImpl implements LinkGroupService {

  private LinkGroupManager linkGroupManager;

  public LinkGroupServiceImpl(LinkGroupManager linkGroupManager) {
    this.linkGroupManager = linkGroupManager;
  }

  @Override
  public int add(LinkGroupAddRequest addRequest) {
    long accountNo = LoginInterceptor.threadLocal.get().getAccountNo();
    log.info("accountNo:{}", accountNo);
    LinkGroupDO linkGroupDO = new LinkGroupDO();
    linkGroupDO.setTitle(addRequest.getTitle());
    linkGroupDO.setAccountNo(accountNo);

    return linkGroupManager.add(linkGroupDO);
  }

  @Override
  public int del(Long groupId) {
    long accountNo = LoginInterceptor.threadLocal.get().getAccountNo();
    return linkGroupManager.del(groupId, accountNo);
  }
}
