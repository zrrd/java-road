package cn.learn.designpattern.model;

import lombok.Data;

/**
 * 用户类
 *
 * @author shaoyijiong
 * @date 2018/7/12
 */
@Data
public class User {
    private String name;
    private Integer age;

    public User() {
    }

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public void sayHi(){
        System.out.println("say hi!");
    }
}
