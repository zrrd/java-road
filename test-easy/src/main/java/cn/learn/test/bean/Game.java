package cn.learn.test.bean;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

/**
 * 游戏表.
 *
 * @author shaoyijiong
 * @date 2018/7/22
 */
@Getter
@Setter
public class Game {

  private Integer id;
  private String name;
  private BigDecimal price;
  private LocalDate publishDate;
  private Double score;
  private String image;
  private String type;
  private Integer status;
}
