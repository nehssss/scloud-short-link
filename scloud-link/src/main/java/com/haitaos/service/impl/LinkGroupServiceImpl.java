package com.haitaos.service.impl;

import com.haitaos.controller.request.LinkGroupAddRequest;
import com.haitaos.controller.request.LinkGroupUpdateRequest;
import com.haitaos.interceptor.LoginInterceptor;
import com.haitaos.manager.LinkGroupManager;
import com.haitaos.model.LinkGroupDO;
import com.haitaos.service.LinkGroupService;
import com.haitaos.vo.LinkGroupVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

  @Override
  public LinkGroupVO detail(Long groupId) {
    long accountNo = LoginInterceptor.threadLocal.get().getAccountNo();
    LinkGroupDO linkGroupDO = linkGroupManager.detail(groupId, accountNo);
    LinkGroupVO linkGroupVO = new LinkGroupVO();
    // map-struts
    BeanUtils.copyProperties(linkGroupDO, linkGroupVO);
    return linkGroupVO;
  }

  @Override
  public List<LinkGroupVO> listAllGroup() {
    long accountNo = LoginInterceptor.threadLocal.get().getAccountNo();
    List<LinkGroupDO> linkGroupDOList = linkGroupManager.listAllGroup(accountNo);
    List<LinkGroupVO> linkGroupVoList =
        linkGroupDOList.stream()
            .map(
                obj -> {
                  LinkGroupVO linkGroupVO = new LinkGroupVO();
                  BeanUtils.copyProperties(obj, linkGroupVO);
                  return linkGroupVO;
                })
            .collect(Collectors.toList());
    return linkGroupVoList;
  }

  @Override
  public int updateById(LinkGroupUpdateRequest updateRequest) {
    long accountNo = LoginInterceptor.threadLocal.get().getAccountNo();
    LinkGroupDO linkGroupDO = new LinkGroupDO();
    linkGroupDO.setId(updateRequest.getId());
    linkGroupDO.setTitle(updateRequest.getTitle());
    linkGroupDO.setAccountNo(accountNo);
    return linkGroupManager.update(linkGroupDO);
  }
}
