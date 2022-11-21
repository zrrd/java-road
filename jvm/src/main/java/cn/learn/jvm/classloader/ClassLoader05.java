package cn.learn.jvm.classloader;

/**
 * @author shaoyijiong
 * @date 2022/11/21
 */
public class ClassLoader05 {

    public static void main(String[] args) {
        System.out.println(T.count);
    }

    /**
     * 如果new T() 在前面是2 如果new T() 在后面是3
     * <br> 因为类的加载过程是先给静态变量赋默认值 再给静态变量赋初始值
     */
    static class T {
        public static int count = 2;

        public static T t = new T();

        private T() {
            count++;
        }
    }

}