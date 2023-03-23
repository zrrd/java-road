package cn.learn.jvm.gc;

/**
 * @author shaoyijiong
 * @date 2023/3/22
 */
public class GcRootTest {

    public static void main(String[] args) {
        // new Object()对象放在堆空间中
        // a变量放在栈帧中的局部变量表中 所以a变量存放在栈中
        Object a = new Object();
        Object b = new Object();

        // 对象a置为空  a的gcroot是没有引用了 可达性算法会回收
        a = null;

        System.gc();
    }
}