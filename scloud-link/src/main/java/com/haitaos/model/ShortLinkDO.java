package com.haitaos.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("short_link")
public class ShortLinkDO implements Serializable {

  private static final long serialVersionUID = 1L;

  // @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  /** group */
  private Long groupId;

  /** short link title */
  private String title;

  /** origin url address  */
  private String originalUrl;

  /** short link domain */
  private String domain;

  /** short link code */
  private String code;

  /** long link md5 code */
  private String sign;

  /** expired time, long time is -1 */
  private Date expired;

  /** account unify number */
  private Long accountNo;

  /** create time */
  private Date gmtCreate;

  /** update time */
  private Date gmtModified;

  /** 0 is default, 1 is deleted */
  private Integer del;

  /** state, lock is locked and unavailable, active is available */
  private String state;

  /** link product level: FIRST free bronze, SECOND gold, THIRD diamond */
  private String linkType;
}
