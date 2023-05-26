package com.haitaos.biz;

import io.jsonwebtoken.lang.Assert;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.Bucket;
import software.amazon.awssdk.services.s3.model.ListBucketsRequest;

import java.util.List;

@SpringBootTest
@Slf4j
public class S3Test {

  @Autowired private S3Client s3Client;

  @Test
  public void listBuckets() {
    List<Bucket> buckets = s3Client.listBuckets().buckets();
    Assert.notEmpty(buckets);
    for (Bucket bucket : buckets) {
      log.info(bucket.name());
    }
  }
}
