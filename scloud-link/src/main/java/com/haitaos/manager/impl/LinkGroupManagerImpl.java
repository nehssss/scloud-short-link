package com.haitaos.manager.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.haitaos.manager.LinkGroupManager;
import com.haitaos.mapper.LinkGroupMapper;
import com.haitaos.model.LinkGroupDO;
import org.springframework.stereotype.Component;

@Component
public class LinkGroupManagerImpl implements LinkGroupManager {
  private LinkGroupMapper linkGroupMapper;

  public LinkGroupManagerImpl(LinkGroupMapper linkGroupMapper) {
    this.linkGroupMapper = linkGroupMapper;
  }

  @Override
  public int add(LinkGroupDO linkGroupDO) {
    return linkGroupMapper.insert(linkGroupDO);
  }

  @Override
  public int del(Long groupId, long accountNo) {
    return linkGroupMapper.delete(
        new QueryWrapper<LinkGroupDO>().eq("id", groupId).eq("account_no", accountNo));
  }
}
