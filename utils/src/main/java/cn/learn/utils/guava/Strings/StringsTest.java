package cn.learn.utils.guava.Strings;

import com.google.common.base.CaseFormat;
import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import java.util.List;

/**
 * String工具类
 *
 * @author 邵益炯
 * @date 2018/9/30
 */
public class StringsTest {

  @SuppressWarnings("unused")
  private static void joinerTest() {
    //跳空
    Joiner joiner = Joiner.on(";").skipNulls();
    //为空格复制为
    Joiner joiner2 = Joiner.on(";").useForNull("空");
    String s = joiner2.join("aq", null, "dsa", "ds");
  }

  @SuppressWarnings("all")
  private static void splitterTest() {
    //自动省略空字符串
    Iterable t1 = Splitter.on(",").omitEmptyStrings().split(",a,,b,");
    //裁剪空格
    Iterable t2 = Splitter.on(",").trimResults().split("a, b, c, d");
    //裁剪_
    Iterable t3 = Splitter.on(",").trimResults(CharMatcher.is('_')).split("_a ,_b_ ,c__");
    //只分隔前两个
    Iterable t4 = Splitter.on(";").limit(3).split("a;b;c;d");
    //转列表
    List t5 = Splitter.on(",").limit(2).splitToList("a,b,c,d");
  }

  // 编码替代 StandardCharsets



  private static void caseFormatTest() {
    //大小写转换
    //LOWER CAMEL lowerCamel 小写驼峰
    //LOWER HYPHEN lower-hyphen 小写连接符
    //LOWER UNDERSCORE lower_underscore 小写下划线
    //UPPER CAMEL UpperCamel 大写驼峰
    //UPPER UNDERSCORE UPPER_UNDERSCORE 大写下划线
    CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "get_sex"); // returns "constantName"
  }

  @SuppressWarnings("ResultOfMethodCallIgnored")
  private static void stringsTest() {
    Strings.isNullOrEmpty("a");
    //各种StringUtils用到的类
  }


  /**
   * TODO
   */
  private static void charMatcherTest() {
    String string = "a";

    //第一个匹配字符 第二个是操作

    //移除control字符
    String noControl = CharMatcher.javaIsoControl().removeFrom(string);
    //只保留数字字符
    String theDigits = CharMatcher.inRange('0', '9').retainFrom(string);
    //去除两端的空格，并把中间的连续空格替换成单个空格
    String spaced = CharMatcher.whitespace().trimAndCollapseFrom(string, ' ');
    //用*号替换所有
    String noDigits = CharMatcher.any().replaceFrom("a", "*");

    // 只保留数字和小写字母
    String lowerAndDigit = CharMatcher.inRange('0', '9').retainFrom("112aa");
  }

  public static void main(String[] args) {
    caseFormatTest();
  }
}
