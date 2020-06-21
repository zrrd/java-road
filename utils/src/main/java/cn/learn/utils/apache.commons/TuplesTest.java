package cn.learn.utils.apache.commons;

import lombok.Data;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

/**
 * 包装多个对象为一个对象返回
 *
 * @author shaoyijiong
 * @date 2020/6/22
 */
public class TuplesTest {

  /**
   * 人
   */
  @Data
  private static class Person {

    int age;
    String name;
  }

  /**
   * 考试
   */
  @Data
  private static class Exam {

    int score;
    String subject;
  }

  /**
   * 如果要返回 一个人的考试结果 , 得再做一个类封装起来
   */
  private Pair<Person, Exam> getExamResult() {
    // Triple 封装3个对象
    return ImmutablePair.of(new Person(), new Exam());
  }



}
