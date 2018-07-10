package cn.learn.mybaits_plus.query.steamParty.dto;

import java.util.Date;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * game传输类
 *
 * @author shaoyijiong
 * @since 2018/6/30
 */
@Data
public class GameDto {

  private String name;

  private Double price;

  private Date publishDate;

  private Double score;

  private String image;
}
