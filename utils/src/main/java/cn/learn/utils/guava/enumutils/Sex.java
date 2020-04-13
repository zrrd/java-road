package cn.learn.utils.guava.enumutils;

/**
 * @author shaoyijiong
 * @date 2018/12/27
 */
public enum Sex {
  /**
   * 男
   */
  MAN("m"),
  /**
   * 女
   */
  WOMAN("w");

  private String value;

  Sex(String value) {
    this.value = value;
  }}
