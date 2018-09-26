package cn.learn.jvm.constantpool;

/**
 * 常量池测试
 *
 * @author 邵益炯
 * @date 2018/9/26
 */
@SuppressWarnings("all")
public class ConstantPool {

  /**
   * 将'a'放入了常量池
   */
  static String a = "a";

  /**
   * 将'acdc放入了常量池中'
   */
  static String c = "ac" + "dc";

  /**
   * 什么都没放入常量池   现在常量池中只有 a  acdc
   */
  static String d = a + c;

  /**
   * 将 'P' 放入常量池中
   */
  static String e = a + "P";

  public void test1() {
    //将'b'放入了常量池
    String b = "b";
  }

  public void test2() {
    //hello 放入常量池中
    String str1 = "hello";
    //str2指向的对象放入堆中
    String str2 = new String("hello");
    //world 放入常量池中 str3指向的对象放入堆中
    String str3 = new String("world");
    //结果为false
    System.out.println(str1 == str2);
  }

  public void test3() {
    String str = "laji";
    String str2 = new String("laji");
    String str3 = null;
    String str4 = "la" + new String("ji");
    // 运行后结果为false
    System.out.println(str == str2);
    //结果为false
    System.out.println(str == str4);
    // 如果str在字符串池中 那么直接返回这个字符串的引用 若果没有 那么将该字符串拉到字符串池中 并返回引用
    str3 = str2.intern();
    // 运行后结果为true
    System.out.println(str == str3);
  }
}
