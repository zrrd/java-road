package cn.learn.reacotr.core.reactive;

import java.math.BigDecimal;

/**
 * @author shaoyijiong
 * @date 2020/6/8
 */
public class InMemoryDataSource {

  public static final Book[] BOOKS = new Book[]{
      new Book("CS Book #1", BigDecimal.valueOf(19.9D), "CS"),
      new Book("CS Book #2", BigDecimal.valueOf(9.9D), "CS"),
      new Book("CS Book #3", BigDecimal.valueOf(39.9D), "CS"),

      new Book("Children Book #1", BigDecimal.valueOf(20.9D), "CHILDREN"),
      new Book("Children Book #2", BigDecimal.valueOf(25.9D), "CHILDREN"),
      new Book("Children Book #3", BigDecimal.valueOf(24.9D), "CHILDREN"),
      new Book("Children Book #4", BigDecimal.valueOf(10.9D), "CHILDREN"),

      new Book("Novel #1", BigDecimal.valueOf(6.9D), "NOVEL"),
      new Book("Novel #2", BigDecimal.valueOf(12.9D), "NOVEL"),
      new Book("Novel #3", BigDecimal.valueOf(8.9D), "NOVEL"),
      new Book("Novel #4", BigDecimal.valueOf(1.9D), "NOVEL"),
  };
}
