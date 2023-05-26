package com.haitaos.controller;

import com.haitaos.enums.BizCodeEnum;
import com.haitaos.service.FileService;
import com.haitaos.util.JsonData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

  private FileService fileService;

  public AccountController(FileService fileService) {
    super();
    this.fileService = fileService;
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
}
