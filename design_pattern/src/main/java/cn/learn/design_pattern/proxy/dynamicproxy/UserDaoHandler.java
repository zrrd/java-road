package cn.learn.design_pattern.proxy.dynamicproxy;

import cn.learn.design_pattern.model.User;
import cn.learn.design_pattern.proxy.dao.UserDao;
import cn.learn.design_pattern.proxy.dao.UserDaoImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理类
 *
 * @author shaoyijiong
 * @date 2018/7/12
 */
public class UserDaoHandler implements InvocationHandler {

  private Object target;

  UserDaoHandler(Object target) {
    this.target = target;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    String methodNme = method.getName();
    if ("growUp".equals(methodNme)) {
      System.out.println("用户长大的动态代理");
    }
    if ("save".equals(methodNme)) {
      System.out.println("用户保存动态代理");
    }
    return method.invoke(target, args);
  }
}

class Test {

  public static void main(String[] args) {
    UserDao userDao = new UserDaoImpl();
    //通过实现同一个接口来增强类
    //不仅仅是增强user 其他类型的类也能增强
    //将代理类和要代理的类进行绑定  动态代理userDaoImpl必须实现一个接口
    UserDaoHandler userDaoHandler = new UserDaoHandler(userDao);
    //生成一个代理类
    UserDao proxy = (UserDao) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
        userDao.getClass().getInterfaces(), userDaoHandler);
    User user = new User("a", 10);
    proxy.growUp(user);
    proxy.save(user);
  }
}
