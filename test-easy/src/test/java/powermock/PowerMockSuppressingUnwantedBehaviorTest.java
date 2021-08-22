package powermock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.powermock.api.support.membermodification.MemberMatcher.constructor;
import static org.powermock.api.support.membermodification.MemberMatcher.field;
import static org.powermock.api.support.membermodification.MemberMatcher.method;
import static org.powermock.api.support.membermodification.MemberModifier.suppress;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.core.classloader.annotations.SuppressStaticInitializationFor;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;
import powermock.PowerMockSuppressingUnwantedBehaviorTest.ExampleWithEvilMethod;
import powermock.PowerMockSuppressingUnwantedBehaviorTest.ExampleWithEvilParent;

/**
 * <pre>
 *
 * 1. Use the @RunWith(PowerMockRunner.class) annotation at the class-level of the test case.
 * 2. Use the @PrepareForTest(ClassWithEvilParentConstructor.class) annotation at the class-level of the test case in combination with suppress(constructor(EvilParent.class)) to suppress all constructors for the EvilParent class.
 * 3. Use the Whitebox.newInstance(ClassWithEvilConstructor.class) method to instantiate a class without invoking the constructor what so ever.
 * 4. Use the @SuppressStaticInitializationFor("org.mycompany.ClassWithEvilStaticInitializer") annotation to remove the static initializer for the the org.mycompany.ClassWithEvilStaticInitializer class.
 * 5. Use the @PrepareForTest(ClassWithEvilMethod.class) annotation at the class-level of the test case in combination with suppress(method(ClassWithEvilMethod.class, "methodName")) to suppress the method with name "methodName" in the ClassWithEvilMethod class.
 * 6. Use the @PrepareForTest(ClassWithEvilField.class) annotation at the class-level of the test case in combination with suppress(field(ClassWithEvilField.class, "fieldName")) to suppress the field with name "fieldName" in the ClassWithEvilField class.
 * </pre>
 * 需要加上下面两个注解
 *
 * @author shaoyijiong
 * @date 2021/8/22
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ExampleWithEvilParent.class, ExampleWithEvilMethod.class})
@SuppressStaticInitializationFor("powermock.ExampleWithEvilStaticInitializer")
public class PowerMockSuppressingUnwantedBehaviorTest {

  public static class EvilParent {

    public EvilParent() {
      System.loadLibrary("evil.dll");
    }
  }

  public static class ExampleWithEvilParent extends EvilParent {

    private final String message;

    public ExampleWithEvilParent(String message) {
      this.message = message;
    }

    public String getMessage() {
      return message;
    }
  }

  /**
   * 禁止超类构造函数
   */
  @Test
  public void testSuppressConstructorOfEvilParent() throws Exception {
    suppress(constructor(EvilParent.class));
    final String message = "myMessage";
    ExampleWithEvilParent tested = new ExampleWithEvilParent(message);
    assertEquals(message, tested.getMessage());
  }

  public static class ExampleWithEvilConstructor {

    private final String message;

    public ExampleWithEvilConstructor(String message) {
      System.loadLibrary("evil.dll");
      this.message = message;
    }

    public String getMessage() {
      return message;
    }
  }

  /**
   * 禁止构造函数 使用Whitebox.newInstance实例化对象 , 绕过构造函数 不需要@RunWith和@PrepareForTest注解
   */
  @Test
  public void testSuppressOwnConstructor() throws Exception {
    ExampleWithEvilConstructor tested = Whitebox.newInstance(ExampleWithEvilConstructor.class);
    assertNull(tested.getMessage());
  }


  public static class ExampleWithEvilMethod {

    private final String message;

    public ExampleWithEvilMethod(String message) {
      this.message = message;
    }

    public String getMessage() {
      return message + getEvilMessage();
    }

    private String getEvilMessage() {
      System.loadLibrary("evil.dll");
      return "evil!";
    }
  }

  /**
   * 禁止方法
   */
  @Test
  public void testSuppressMethod() throws Exception {
    suppress(method(ExampleWithEvilMethod.class, "getEvilMessage"));
    final String message = "myMessage";
    ExampleWithEvilMethod tested = new ExampleWithEvilMethod(message);
    assertEquals(message, tested.getMessage());
  }


  /**
   * 静止静态初始化方法 需要加上 @SuppressStaticInitializationFor 注解
   */
  @Test
  public void testSuppressStaticInitializer() throws Exception {
    final String message = "myMessage";
    ExampleWithEvilStaticInitializer tested = new ExampleWithEvilStaticInitializer(message);
    assertEquals(message, tested.getMessage());
  }

  public static class MyClass {
    private Object myObject = new Object();


    public Object getMyObject() {
      return myObject;
    }
  }

  /**
   * 禁止初始化字段 调用getMyObject将返回空
   */
  @Test
  public void testSuppressField() throws Exception {
    suppress(field(MyClass.class, "myObject"));
    MyClass myClass = new MyClass();
    myClass.getMyObject();
  }
}
