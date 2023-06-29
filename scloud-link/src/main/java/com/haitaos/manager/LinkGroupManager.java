package com.haitaos.manager;

import com.haitaos.model.LinkGroupDO;

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
}
