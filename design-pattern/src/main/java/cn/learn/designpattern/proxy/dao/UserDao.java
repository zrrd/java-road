package cn.learn.designpattern.proxy.dao;

import cn.learn.designpattern.model.User;

/**
 * @author shaoyijiong
 */
public interface UserDao {

  /**
   * 保存用户
   *
   * @param user 用户实体类
   */
  void save(User user);

  /**
   * 用户年龄加一
   *
   * @param user 用户实体类
   */
  void growUp(User user);
}
