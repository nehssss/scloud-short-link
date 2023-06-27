package com.haitaos.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("link_group")
public class LinkGroupDO implements Serializable {

  private static final long serialVersionUID = 1L;

  // @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  /** group name */
  private String title;

  /** account unify number */
  private Long accountNo;

  private Date gmtCreate;

  private Date gmtModified;
}
