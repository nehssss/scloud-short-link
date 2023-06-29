package com.haitaos.service;

import com.haitaos.controller.request.LinkGroupAddRequest;

public interface LinkGroupService {
  /**
   * add group
   *
   * @param addRequest
   * @return
   */
  int add(LinkGroupAddRequest addRequest);

  /**
   * delete group
   *
   * @param groupId
   * @return
   */
  int del(Long groupId);
}
