package cn.learn.regex.matcher;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 查询正则表达式是否匹配
 *
 * @author 邵益炯
 * @date 2018/8/13
 */
public class FindMatcher {

  /**
   * 匹配真正表达式
   *
   * @param line 需要匹配的字符串
   * @param pattern 正则串
   */
  public static void matcher(String line, String pattern) {
    Pattern r = Pattern.compile(pattern);
    Matcher m = r.matcher(line);
    if (m.find()) {
      System.out.println("找到了");
    } else {
      System.out.println("没找到");
    }
    if (m.matches()) {
      System.out.println("匹配");
    } else {
      System.out.println("没匹配");
    }
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    System.out.println("----请输出正则表达式----");
    String pattern = in.nextLine();
    System.out.printf("正则表达式为%s\n", pattern);

    System.out.println("----请输入要匹配的字符----");
    String line = in.nextLine();
    System.out.printf("要匹配的字符为%s\n\n", line);

    matcher(line, pattern);
  }
}
