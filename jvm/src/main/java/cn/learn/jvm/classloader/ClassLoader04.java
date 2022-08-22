package cn.learn.jvm.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 * 自定义类加载器
 *
 * @author shaoyijiong
 * @date 2022/8/19
 */
public class ClassLoader04 extends ClassLoader {

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            File file = new File("/Users/meiliodas/Documents", name.replace(".", "/").concat(".class"));
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int b = 0;

            while ((b = fis.read()) != 0) {
                baos.write(b);
            }

            byte[] bytes = baos.toByteArray();
            baos.close();
            fis.close();//可以写的更加严谨

            return defineClass(name, bytes, 0, bytes.length);
        } catch (Exception e) {
            throw new ClassNotFoundException();
        }
    }

    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoader04 customerClassLoader = new ClassLoader04();
        Class<?> aClass = customerClassLoader.loadClass("cn.learn.jvm.classloader.Hello");
        // 反射
        System.out.println(aClass.getClassLoader());
        System.out.println("a");
    }
}