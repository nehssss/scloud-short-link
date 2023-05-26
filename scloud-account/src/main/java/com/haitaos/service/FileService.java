package com.haitaos.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

  /**
   * upload user image
   *
   * @param file
   * @return
   */
  String uploadUserImg(MultipartFile file);
}
