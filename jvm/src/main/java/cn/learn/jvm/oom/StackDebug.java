package cn.learn.jvm.oom;

/**
 * @author shaoyijiong
 * @date 2023/7/14
 */
public class StackDebug {

    public static void main(String[] args) {
        method1();
    }

    public static void method1() {
        System.out.println("method1() 开始执行");
        method2();
        System.out.println("method1() 执行结束");
    }

    public static void method2() {
        System.out.println("method2() 开始执行");
        method3();
        System.out.println("method2() 执行结束");
    }

    public static void method3() {
        System.out.println("method3() 开始执行");
        System.out.println("method3() 执行结束");
    }
}