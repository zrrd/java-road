package cn.learn.designpattern.proxy.dao;

import cn.learn.designpattern.model.User;

/**
 * UserDao实现类
 *
 * @author shaoyijiong
 * @date 2018/7/12
 */
public class UserDaoImpl implements UserDao {

  @Override
  public void save(User user) {
    System.out.println(user + "以保存");
  }

  @Override
  public void growUp(User user) {
    System.out.println(user.getAge() + 1 + "岁," + "用户有长大一岁了");
  }
}
