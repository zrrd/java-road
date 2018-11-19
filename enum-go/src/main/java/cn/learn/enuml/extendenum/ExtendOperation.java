package cn.learn.enuml.extendenum;

/**
 * @author shaoyijiong
 */
public enum ExtendOperation implements Operation {
  //乘方
  EXP("^") {
    @Override
    public double apply(double x, double y) {
      return Math.pow(x, y);
    }
  },
  //求余
  REMAINDER("%") {
    @Override
    public double apply(double x, double y) {
      return x % y;
    }
  };
  private final String symbol;

  ExtendOperation(String symbol) {
    this.symbol = symbol;
  }

  @Override
  public String toString() {
    return symbol;
  }
}
