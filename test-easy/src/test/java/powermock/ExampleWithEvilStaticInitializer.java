package powermock;

/**
 * @author shaoyijiong
 * @date 2021/8/22
 */
public class ExampleWithEvilStaticInitializer {
  static {
    System.loadLibrary("evil.dll");
  }

  private final String message;

  public ExampleWithEvilStaticInitializer(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}
