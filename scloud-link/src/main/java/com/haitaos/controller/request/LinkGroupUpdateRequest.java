package com.haitaos.controller.request;

import lombok.Data;

@Data
public class LinkGroupUpdateRequest {
  /** group id */
  private Long id;
  /** group name */
  private String title;
}
