package com.haitaos.controller;

import com.haitaos.controller.request.AccountRegisterRequest;
import com.haitaos.enums.BizCodeEnum;
import com.haitaos.service.AccountService;
import com.haitaos.service.FileService;
import com.haitaos.util.JsonData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/account")
@Slf4j
public class AccountController {

  private FileService fileService;

  private AccountService accountService;

  public AccountController(FileService fileService, AccountService accountService) {
    super();
    this.fileService = fileService;
    this.accountService = accountService;
  }

  /**
   * upload user image, max size 1M
   *
   * @param file
   * @return
   */
  @PostMapping("upload")
  public JsonData uploadUserImg(@RequestPart("file") MultipartFile file) {
    String result = fileService.uploadUserImg(file);
    return result != null
        ? JsonData.buildSuccess(result)
        : JsonData.buildResult(BizCodeEnum.FILE_UPLOAD_USER_IMG_FAIL);
  }

  /**
   * user register
   *
   * @param accountRegisterRequest
   * @return
   */
  @PostMapping("register")
  public JsonData register(@RequestBody AccountRegisterRequest accountRegisterRequest) {
    JsonData jsonData = accountService.register(accountRegisterRequest);
    return jsonData;
  }
}
