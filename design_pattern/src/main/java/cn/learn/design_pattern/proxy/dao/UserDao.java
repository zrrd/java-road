package cn.learn.design_pattern.proxy.dao;

import cn.learn.design_pattern.model.User;

/**
 * @author shaoyijiong
 */
public interface UserDao {
    /**
     * 保存用户
     */
    void save(User user);

    /**
     * 用户年龄加一
     */
    void grow(User user);
}
