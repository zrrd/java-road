package cn.learn.enuml.common;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shaoyijiong
 */

public enum Operation {
  //加号
  PLUS("+") {
    @Override
    double apply(double x, double y) {
      return x + y;
    }
  },
  //减号
  MINUS("-") {
    @Override
    double apply(double x, double y) {
      return x - y;
    }
  },
  //乘号
  TIMES("*") {
    @Override
    double apply(double x, double y) {
      return x * y;
    }
  },
  //除号
  DIVIDE("/") {
    @Override
    double apply(double x, double y) {
      return x / y;
    }
  };

  String symbol;

  Operation(String symbol) {
    this.symbol = symbol;
  }

  private static final Map<String, Operation> STRING_TO_ENUM = new HashMap<>();

  /**
   * 枚举方法  每个枚举都要继承这个方法
   */
  abstract double apply(double x, double y);

  public static Operation valueofsymbol(String symbol) {
    for (Operation operation : Operation.values()) {
      if (operation.symbol.equals(symbol)) {
        return operation;
      }
    }
    return null;
  }

  public static void main(String[] args) {
    //通过字符串获得枚举
    System.out.println(Operation.valueOf("PLUS"));

    //直接获得枚举
    System.out.println(Operation.DIVIDE);

    //通过自定义的方式获得枚举  将没有对应的返回null
    System.out.println(Operation.valueofsymbol("+"));
  }
}


