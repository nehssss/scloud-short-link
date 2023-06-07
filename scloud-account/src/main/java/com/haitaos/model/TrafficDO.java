package com.haitaos.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("traffic")
public class TrafficDO implements Serializable {

  private static final long serialVersionUID = 1L;

  //@TableId(value = "id", type = IdType.AUTO)
  private Long id;

  /** Number of short links per day limit */
  private Integer dayLimit;

  /** Number of short links per day used */
  private Integer dayUsed;

  /** total times */
  private Integer totalLimit;

  /** account */
  private Long accountNo;

  /** trade number */
  private String outTradeNo;

  /** product level：Bronze：FIRST, Gold: SECOND, Diamond: THIRD */
  private String level;

  /** expire date */
  private Date expiredDate;

  /** plugin type */
  private String pluginType;

  /** product primary key */
  private Long productId;

  private Date gmtCreate;

  private Date gmtModified;
}
