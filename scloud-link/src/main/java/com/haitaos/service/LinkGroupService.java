package com.haitaos.service;

import com.haitaos.controller.request.LinkGroupAddRequest;
import com.haitaos.controller.request.LinkGroupUpdateRequest;
import com.haitaos.vo.LinkGroupVO;

import java.util.List;

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

  /**
   * detail
   *
   * @param groupId
   * @return
   */
  LinkGroupVO detail(Long groupId);

  /**
   * list user all group
   *
   * @return
   */
  List<LinkGroupVO> listAllGroup();

  /**
   * update group
   *
   * @param updateRequest
   * @return
   */
  int updateById(LinkGroupUpdateRequest updateRequest);
}
