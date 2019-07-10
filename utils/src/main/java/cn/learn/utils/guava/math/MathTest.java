package cn.learn.utils.guava.math;

import com.google.common.math.IntMath;
import java.math.RoundingMode;

/**
 * @author shaoyijiong
 * @date 2019/7/10
 */
public class MathTest {

  private static void intMathTest() {
    // throws ArithmeticException

    //加
    IntMath.checkedAdd(Integer.MAX_VALUE, Integer.MAX_VALUE);
    //减
    IntMath.checkedSubtract(Integer.MAX_VALUE, Integer.MAX_VALUE);
    //乘
    IntMath.checkedMultiply(Integer.MAX_VALUE, Integer.MAX_VALUE);
    //乘方
    IntMath.checkedPow(Integer.MAX_VALUE, Integer.MAX_VALUE);

    //RoundingMode 舍入方向

    //除
    IntMath.divide(1, 1, RoundingMode.UP);
    //2为底的对数

    //10为底的对数

    //平方根

    //最大公约数

    //取模

    //取幂

    //是否2的幂

    //阶乘*

    //二项式系数*



  }
}
