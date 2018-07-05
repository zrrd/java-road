package cn.learn.mybaits_plus.model;

import lombok.Data;

/**
 * 用户实体类
 *
 * @author win
 * @date 2018-6-30
 */
@Data
public class User {

  private Integer id;

  private String name;

  private Integer gameNum;

  private Double money;

  private String prefer;

}