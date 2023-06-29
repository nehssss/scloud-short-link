package com.haitaos.controller;

import com.haitaos.controller.request.LinkGroupAddRequest;
import com.haitaos.enums.BizCodeEnum;
import com.haitaos.service.LinkGroupService;
import com.haitaos.util.JsonData;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/group/v1")
public class LinkGroupController {

    private LinkGroupService linkGroupService;

    public LinkGroupController(LinkGroupService linkGroupService) {
        this.linkGroupService = linkGroupService;
    }

    /**
     * add group
     * @param addRequest
     * @return
     */
    @PostMapping("/add")
    public JsonData add(@RequestBody LinkGroupAddRequest addRequest) {
        int rows = linkGroupService.add(addRequest);
        return rows == 1 ? JsonData.buildSuccess() : JsonData.buildResult(BizCodeEnum.GROUP_ADD_FAIL);
    }

  /**
   *
   * @param groupId
   * @return
   */
  @DeleteMapping("/del/{group_id}")
    public JsonData del(@PathVariable("group_id") Long groupId) {
        int rows = linkGroupService.del(groupId);
        return rows == 1 ? JsonData.buildSuccess() : JsonData.buildResult(BizCodeEnum.GROUP_NOT_EXIST);
    }
}
