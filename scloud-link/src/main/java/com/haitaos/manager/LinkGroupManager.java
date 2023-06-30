package com.haitaos.manager;

import com.haitaos.model.LinkGroupDO;

import java.util.List;

public interface LinkGroupManager {
  /**
   * add group
   *
   * @param linkGroupDO
   * @return
   */
  int add(LinkGroupDO linkGroupDO);

  /**
   * delete group
   *
   * @param groupId
   * @param accountNo
   * @return
   */
  int del(Long groupId, long accountNo);

  /**
   * according groupId and accountNo to get group detail
   *
   * @param groupId
   * @param accountNo
   * @return
   */
  LinkGroupDO detail(Long groupId, long accountNo);

  /**
   * list user all group
   *
   * @param accountNo
   * @return
   */
  List<LinkGroupDO> listAllGroup(long accountNo);

  /**
   * update group
   *
   * @param linkGroupDO
   * @return
   */
  int update(LinkGroupDO linkGroupDO);
}
