package com.haitaos.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class LinkGroupVO {
  private Long id;

  /** group name */
  private String title;

  /** account unify number */
  private Long accountNo;

  private Date gmtCreate;

  private Date gmtModified;
}
