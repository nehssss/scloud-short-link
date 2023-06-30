package com.haitaos.manager.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.haitaos.manager.LinkGroupManager;
import com.haitaos.mapper.LinkGroupMapper;
import com.haitaos.model.LinkGroupDO;
import org.springframework.stereotype.Component;

import java.util.List;

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

  @Override
  public LinkGroupDO detail(Long groupId, long accountNo) {
    return linkGroupMapper.selectOne(
        new QueryWrapper<LinkGroupDO>().eq("id", groupId).eq("account_no", accountNo));
  }

  @Override
  public List<LinkGroupDO> listAllGroup(long accountNo) {
    return linkGroupMapper.selectList(new QueryWrapper<LinkGroupDO>().eq("account_no", accountNo));
  }

  @Override
  public int update(LinkGroupDO linkGroupDO) {
    return linkGroupMapper.update(
        linkGroupDO, new QueryWrapper<LinkGroupDO>().eq("id", linkGroupDO.getId()));
  }
}
