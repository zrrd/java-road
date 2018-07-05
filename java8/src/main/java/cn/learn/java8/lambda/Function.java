package cn.learn.java8.lambda;


/**
 * @author jiudao
 */
@FunctionalInterface
public interface Function {
    /**
     * 函数式接口，一个接口包含一个方法  默认方法和静态方法不包括
     */
    void method();


    /**默认方法与抽象方法不同之处在于抽象方法必须要求实现，但是默认方法则没有这个要求。
     * 相反，每个接口都必须提供一个所谓的默认实现，这样所有的接口实现者将会默认继承它
     * （如果有必要的话，可以覆盖这个默认实现）*/

    default void defaultMethod(){
        System.out.println("默认方法");
    }

    static String staticMethod(String aa) {
        return aa;
    }
}
class Test implements Function{

    @Override
    public void method() {

    }

    /**覆盖了默认方法*/
    @Override
    public void defaultMethod(){

    }
}




