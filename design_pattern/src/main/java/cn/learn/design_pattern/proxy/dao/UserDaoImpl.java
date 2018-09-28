package cn.learn.design_pattern.proxy.dao;

import cn.learn.design_pattern.model.User;
import cn.learn.design_pattern.proxy.dao.UserDao;

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
