package cn.learn.enuml.extendenum;

/**
 * @author shaoyijiong
 */
public interface Operation {

  /**
   * 两个数的运算
   *
   * @param x 参数1
   * @param y 参数2
   * @return 返回运算后的数
   */
  double apply(double x, double y);
}
