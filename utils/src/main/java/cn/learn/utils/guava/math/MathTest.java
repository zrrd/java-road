package cn.learn.utils.guava.math;

import com.alibaba.fastjson.JSON;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiMessageCorpconversationAsyncsendV2Request;
import com.dingtalk.api.request.OapiServiceGetAuthInfoRequest;
import com.dingtalk.api.response.OapiMessageCorpconversationAsyncsendV2Response;
import com.dingtalk.api.response.OapiServiceGetAuthInfoResponse;
import com.google.common.base.Joiner;
import com.google.common.math.DoubleMath;
import com.google.common.math.IntMath;
import com.google.common.math.LongMath;
import com.taobao.api.ApiException;

import java.math.RoundingMode;
import java.util.concurrent.CompletableFuture;

/**
 * https://wizardforcel.gitbooks.io/guava-tutorial/content/28.html
 *
 * @author shaoyijiong
 * @date 2019/7/10
 */
public class MathTest {

  private static void intMathTest() {

    //IntMath     LongMath     BigIntegerMath

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

    //判断该浮点数是不是一个整数
    boolean mathematicalInteger = DoubleMath.isMathematicalInteger(1.2);

    //舍入为Int
    DoubleMath.roundToInt(1.2, RoundingMode.UP);
    //舍入为Long
    DoubleMath.roundToLong(1.3, RoundingMode.UP);
    //舍入为BigInteger
    DoubleMath.roundToBigInteger(1.3, RoundingMode.UP);
    //2的浮点对数，并且舍入为int，比JDK的Math.log(double) 更快
    DoubleMath.log2(1.2, RoundingMode.UP);
  }

  public static void main(String[] args) {
    System.out.println(IntMath.divide(61, 60, RoundingMode.UP));
  }
}
