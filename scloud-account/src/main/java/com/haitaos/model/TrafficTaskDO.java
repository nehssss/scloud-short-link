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
@TableName("traffic_task")
public class TrafficTaskDO implements Serializable {

  private static final long serialVersionUID = 1L;

  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  private Long accountNo;

  private Long trafficId;

  private Integer useTimes;

  /** Status: LOCK, FINISH, CANCEL */
  private String lockState;

  /** Unique Identification */
  private String messageId;

  private Date gmtCreate;

  private Date gmtModified;
}
