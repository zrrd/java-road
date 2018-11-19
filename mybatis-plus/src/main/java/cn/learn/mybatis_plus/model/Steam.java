package cn.learn.mybatis_plus.model;

import java.util.Date;
import lombok.Data;


/**
 * steam实体类
 *
 * @author win
 * @date 2018-6-30
 */
@Data
public class Steam {

  private Integer id;

  private Integer gameId;

  private Integer userId;

  private Date createTime;


}