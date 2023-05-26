package com.haitaos.service.impl;

import com.haitaos.config.S3Config;
import com.haitaos.service.FileService;
import com.haitaos.util.CommonUtil;
import io.awspring.cloud.s3.S3Resource;
import io.awspring.cloud.s3.S3Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@Slf4j
public class FileServiceImpl implements FileService {

  /** s3 template, the higher level of s3 client */
  private S3Template s3Template;

  private S3Config s3Config;

  public FileServiceImpl(S3Template s3Template, S3Config s3Config) {
    super();
    this.s3Template = s3Template;
    this.s3Config = s3Config;
  }

  @Override
  public String uploadUserImg(MultipartFile file) {
    String bucketName = s3Config.getBucketName();
    // get original file name
    String originalFilename = file.getOriginalFilename();

    // jdk8 feature, get date format string
    LocalDateTime ldt = LocalDateTime.now();
    DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    // user/2022/12/12/xxx.jpg
    String folder = pattern.format(ldt);
    String fileName = CommonUtil.generateUUID();
    assert originalFilename != null;
    String extension = originalFilename.substring(originalFilename.lastIndexOf("."));

    // create folder if not exist in s3 bucket
    String newFolderName = "user/" + folder + "/" + fileName + extension;
    try {
      S3Resource upload = s3Template.upload(bucketName, newFolderName, file.getInputStream());
      // concat return url
      return upload.getURL().toString();
    } catch (IOException e) {
      log.error("upload user image fail:{}", e.getMessage());
    }
    return null;
  }
}
