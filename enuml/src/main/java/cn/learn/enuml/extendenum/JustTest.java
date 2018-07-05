package cn.learn.enuml.extendenum;

public class JustTest {
    public static <T extends Enum<T> & Operation> void test(Class<T> opSet,double x, double y){
        /**
         * getEnumConstants获得values的clone
         * */
        for (Operation op : opSet.getEnumConstants()){
            System.out.println(""+x+op+y+"="+op.apply(x, y));
        }
    }
}
