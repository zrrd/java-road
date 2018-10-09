package cn.learn.reflection.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * 主要是指程序可以访问， 检测和修改它本身状态或行为的一种能力
 *
 * @author 邵益炯
 */
public class Reflection {
  @SuppressWarnings({"unchecked","unused"})
  public static void animal()
      throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
    /*
     * 运用反射先要获得这个类
     * 三种方式
     * */
    Class<Animal> class1 = (Class<Animal>) Class.forName("cn.learn.reflection.reflection.Animal");
    Class<Animal> class2 = Animal.class;
    Animal animalTest = new Animal("猫", "喵");
    /*
     * 可能是Animal的子类
     * */
    Class<? extends Animal> class3 = animalTest.getClass();


    /*
     * 创建对象
     * 可能会抛出 IllegalAccessException, InstantiationException这两个异常
     * */

    /*
     * 调用了无参构造
     * 无参构造为包级别的
     * 会报错
     * */
    Animal animal = class1.newInstance();
    /*
     * 获取构所有公有造方法
     * 并且构造一个实体调用函数
     * */

    Constructor<Animal>[] constructors = (Constructor<Animal>[]) class1.getDeclaredConstructors();
    System.out.println("有下列构造函数" + constructors.length);
    for (Constructor<Animal> constructor : constructors) {
      System.out.println(constructor.getName());
      if (constructor.getParameterCount() > 0) {
        Animal animal1 = constructor.newInstance("猫", "喵");
        System.out.println(animal1.shout("aaa"));
      }
    }

    /*
     * 获得所有的方法
     * 并掉用
     * */
    System.out.println("有下列这些方法");
    Method[] methods = class1.getDeclaredMethods();
    for (Method m : methods) {
      System.out.println(m.getName());
    }

    /*
     * 获得指定方法 第一个参数为方法名，后面的参数为传入参数的类型
     * 并调用
     * */
    Method method = class1.getDeclaredMethod("shout", String.class);
    Animal animal2 = new Animal("狗", "汪");
    method.invoke(animal2, "喵");

    /*
     * 获得所有属性并打印
     * */
    Field[] fields = class1.getDeclaredFields();
    StringBuilder str = new StringBuilder();
    str.append(Modifier.toString(class1.getModifiers())).append(" class ")
        .append(class1.getSimpleName()).append("{\n");
    //里边的每一个属性
    for (Field field : fields) {
      //空格
      str.append("\t");
      //获得属性的修饰符，例如public，static等等
      str.append(Modifier.toString(field.getModifiers())).append(" ");
      //属性的类型的名字
      str.append(field.getType().getSimpleName()).append(" ");
      //属性的名字+回车
      str.append(field.getName()).append(";\n");
    }
    str.append("}");
    System.out.println(str);
  }
}
