package cn.learn.jvm.classloader;

/**
 * @author shaoyijiong
 * @date 2022/8/18
 */
public class ClassLoader01 {
    public static void main(String[] args) {
        System.out.println(String.class.getClassLoader());
        System.out.println(sun.awt.HKSCS.class.getClassLoader());
        System.out.println(sun.net.spi.nameservice.dns.DNSNameService.class.getClassLoader());
        System.out.println(ClassLoader01.class.getClassLoader());

        System.out.println(sun.net.spi.nameservice.dns.DNSNameService.class.getClassLoader().getClass().getClassLoader());
        System.out.println(ClassLoader01.class.getClassLoader().getClass().getClassLoader());
    }
}