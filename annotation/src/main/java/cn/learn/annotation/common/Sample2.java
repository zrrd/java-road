package cn.learn.annotation.common;

/**
 * @author shaoyijiong
 */
public class Sample2 {
    @ExceptionTest(ArithmeticException.class)
    public static void m1(){
        int i = 0;
        i = i/i;
    }

    public static void main(String args[]){
        m1();
    }
}
