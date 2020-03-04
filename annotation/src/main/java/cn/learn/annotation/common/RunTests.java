package cn.learn.annotation.common;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.junit.Test;

/**
 * @author syj
 */
public class RunTests {

  public static void main(String[] arg) throws Exception {
    int tests = 0;
    int passed = 0;
    //通过反射动态的加载类
    Class<?> testClass = Class.forName("cn.learn.annotation.common.Sample2");
    //通过反射动态的获得testClass中的方法
    for (Method m : testClass.getDeclaredMethods()) {
      //m方法是否是被@Test方法注解
      if (m.isAnnotationPresent(Test.class)) {
        tests++;
        try {
          //通过反射动态的调用m方法
          m.invoke(null);
          passed++;
        } catch (InvocationTargetException wrappedExc) {
          Throwable exc = wrappedExc.getCause();
          Class<? extends Exception> excType = m.getAnnotation(ExceptionTest.class).value();
          if (excType.isInstance(exc)) {
            passed++;
          } else {
            System.out.println(m + "failed:" + exc);
          }
        } catch (Exception exc) {
          System.out.println("INVALID @TEST :" + m);
        }
      }
    }
    int failed = tests - passed;
    System.out.println("Test:" + tests + ",Passed:" + passed + ",Failed:" + failed);
  }
}
