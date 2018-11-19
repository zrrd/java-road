package cn.learn.fastjson.jsontest;

import lombok.Data;

/**
 * 宠物.
 *
 * @author shaoyijiong
 * @date 2018/7/23
 */
@Data
class Pet{

  private String type;
  private String name;

  Pet(String type, String name) {
    this.type = type;
    this.name = name;
  }
}