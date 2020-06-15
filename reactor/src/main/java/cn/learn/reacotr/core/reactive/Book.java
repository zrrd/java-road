package cn.learn.reacotr.core.reactive;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author shaoyijiong
 * @date 2020/6/8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

  private String title;
  private BigDecimal price;
  private String category;
}
