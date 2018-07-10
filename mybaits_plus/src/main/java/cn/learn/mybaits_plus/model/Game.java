package cn.learn.mybaits_plus.model;

import java.util.Date;
import lombok.Data;

/**
 * 游戏实体类
 *
 * @author win
 * @date 2018/6/30
 */
@Data
public class Game {

  private Integer id;

  private String name;

  private Double price;

  private Date publishDate;

  private Double score;

  private String image;

}