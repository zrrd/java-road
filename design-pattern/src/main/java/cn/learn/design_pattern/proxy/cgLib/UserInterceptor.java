package cn.learn.design_pattern.proxy.cgLib;


import cn.learn.design_pattern.model.User;
import java.lang.reflect.Method;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

/**
 * 用户增强类 拦截 通过CgLib来进行代理
 *
 * @author shaoyijiong
 * @date 2018/7/12
 */
public class UserInterceptor implements MethodInterceptor {

  private Object target;

  UserInterceptor(Object target) {
    this.target = target;
  }

  @Override
  public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy)
      throws Throwable {
    System.out.println("cglib代理 开始了 开始了 ");
    //执行对象的方法
    Object result = method.invoke(target, objects);
    System.out.println("cglib代理 结束了 结束了");
    return result;
  }
}

class Test {

  public static void main(String[] args) {
    User user = new User("a", 10);
    //通过继承要增强的接口来增强接口
    //不仅仅是增强user 其他类型的类也能增强 与动态代理的区别是  动态代理要增强的类需要实现一个接口 这个不需要
    Enhancer enhancer = new Enhancer();
    //设置要继承的目标类
    enhancer.setSuperclass(user.getClass());
    //设置增强的方法
    enhancer.setCallback(new UserInterceptor(user));
    //生成新的代理类
    User user1 = (User) enhancer.create();
    user1.sayHi();

  }
}
