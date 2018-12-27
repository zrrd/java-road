package cn.learn.utils.guava.EnumUtils;

import com.google.common.base.Converter;
import com.google.common.base.Enums;
import java.lang.reflect.Field;

/**
 * @author shaoyijiong
 * @date 2018/12/27
 */
public class EnumTest {

  public static void main(String[] args) {
    //通过枚举名获得枚举
    Sex w = Enums.getIfPresent(Sex.class, "MAN").orNull();
    System.out.println(w);
    Field field = Enums.getField(Sex.MAN);
    Converter<String, Sex> stringSexConverter = Enums.stringConverter(Sex.class);

  }
}
