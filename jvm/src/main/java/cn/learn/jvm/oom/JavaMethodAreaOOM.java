package cn.learn.jvm.oom;

import java.lang.reflect.Method;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

/**
 * Java方法区内存溢出
 * VM args: -XX:PermSize=10M -XX:MaxPermSize=10M
 * @author 邵益炯
 * @date 2018/9/27
 */
public class JavaMethodAreaOOM {

  /**
   * 通过CGLib运行时动态创建对象
   */
  public static void main(String[] args) {
    while (true) {
      Enhancer enhancer = new Enhancer();
      enhancer.setSuperclass(OOMObject.class);
      enhancer.setUseCache(false);
      enhancer.setCallback(new MethodInterceptor() {
        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy)
            throws Throwable {
          return methodProxy.invoke(o, objects);
        }
      });
      enhancer.create();
    }
  }
}
