package cn.learn.mybaits_plus.application.steamParty.dto;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 * game传输类
 *
 * @author shaoyijiong
 * @since 2018/6/30
 */
@Getter
@Setter
public class GameDto {

  private String name;

  private Double price;

  private Date publishDate;

  private Double score;

  public GameDto(String name, Double price, Date publishDate, Double score) {
    this.name = name;
    this.price = price;
    this.publishDate = publishDate;
    this.score = score;
  }
}
