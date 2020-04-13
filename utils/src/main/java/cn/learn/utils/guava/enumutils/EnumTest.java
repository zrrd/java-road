package cn.learn.utils.guava.enumutils;

import com.google.common.base.Converter;
import com.google.common.base.Enums;
import java.lang.reflect.Field;

/**
 * @author shaoyijiong
 * @date 2018/12/27
 */
public class EnumTest {

  public static void main(String[] args) throws IllegalAccessException {
    //通过枚举名获得枚举
    Sex w = Enums.getIfPresent(Sex.class, "MAN").orNull();
    System.out.println(w);

    Field field = Enums.getField(Sex.MAN);
    System.out.println(field);

    // 枚举值 与 枚举名的转化器
    Converter<String, Sex> stringSexConverter = Enums.stringConverter(Sex.class);
    System.out.println(stringSexConverter.convert("MAN"));
    System.out.println(stringSexConverter.reverse().convert(Sex.MAN));
  }
}
