package cn.learn.jvm.classloader;

/**
 *  如何加载一个类
 *
 * @author shaoyijiong
 * @date 2022/8/18
 */
public class ClassLoader03 {

    public static void main(String[] args) throws ClassNotFoundException {
        Class clazz = ClassLoader03.class.getClassLoader().loadClass("cn.learn.jvm.classloader.ClassLoader01");
        System.out.println(clazz.getName());
        // class Loader 是反射的基础
        //利用类加载器加载资源，参考坦克图片的加载
        //T005_LoadClassByHand.class.getClassLoader().getResourceAsStream("");
    }
}