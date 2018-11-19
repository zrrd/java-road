package cn.learn.enuml.extendenum;

/**
 * @author shaoyijiong
 */
public enum BaseOperation implements Operation {
  //加
  PLUS("+") {
    @Override
    public double apply(double x, double y) {
      return x + y;
    }
  },
  //减
  MINUS("-") {
    @Override
    public double apply(double x, double y) {
      return x - y;
    }
  },
  //乘
  TIMES("*") {
    @Override
    public double apply(double x, double y) {
      return x * y;
    }
  },
  //除
  DIVIDE("/") {
    @Override
    public double apply(double x, double y) {
      return x / y;
    }
  };

  private final String symbol;

  BaseOperation(String symbol) {
    this.symbol = symbol;
  }

  @Override
  public String toString() {
    return symbol;
  }
}

