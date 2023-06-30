package com.haitaos.controller;

import com.haitaos.controller.request.LinkGroupAddRequest;
import com.haitaos.controller.request.LinkGroupUpdateRequest;
import com.haitaos.enums.BizCodeEnum;
import com.haitaos.service.LinkGroupService;
import com.haitaos.util.JsonData;
import com.haitaos.vo.LinkGroupVO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/group/v1")
public class LinkGroupController {

  private LinkGroupService linkGroupService;

  public LinkGroupController(LinkGroupService linkGroupService) {
    this.linkGroupService = linkGroupService;
  }

  /**
   * add group
   *
   * @param addRequest
   * @return
   */
  @PostMapping("/add")
  public JsonData add(@RequestBody LinkGroupAddRequest addRequest) {
    int rows = linkGroupService.add(addRequest);
    return rows == 1 ? JsonData.buildSuccess() : JsonData.buildResult(BizCodeEnum.GROUP_ADD_FAIL);
  }

  /**
   * delete group by groupId
   *
   * @param groupId
   * @return
   */
  @DeleteMapping("/del/{group_id}")
  public JsonData del(@PathVariable("group_id") Long groupId) {
    int rows = linkGroupService.del(groupId);
    return rows == 1 ? JsonData.buildSuccess() : JsonData.buildResult(BizCodeEnum.GROUP_NOT_EXIST);
  }

  /**
   * according to groupId to get group detail
   *
   * @param groupId
   * @return
   */
  @GetMapping("/detail/{group_id}")
  public JsonData detail(@PathVariable("group_id") Long groupId) {
    LinkGroupVO linkGroupVo = linkGroupService.detail(groupId);
    return JsonData.buildSuccess(linkGroupVo);
  }

  /**
   * list user all group
   *
   * @return
   */
  @GetMapping("/list")
  public JsonData findUserAllLinkGroup() {
    List<LinkGroupVO> list = linkGroupService.listAllGroup();
    return JsonData.buildSuccess(list);
  }

  /**
   * update group by id
   *
   * @param updateRequest
   * @return
   */
  @PutMapping("/update")
  public JsonData updateById(@RequestBody LinkGroupUpdateRequest updateRequest) {
    int rows = linkGroupService.updateById(updateRequest);
    return rows == 1 ? JsonData.buildSuccess() : JsonData.buildResult(BizCodeEnum.GROUP_OPER_FAIL);
  }
}
