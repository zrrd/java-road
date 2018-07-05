package cn.learn.reflection.reflection;
/**
 *作为反射的测试调用类
 */

public class Animal {
    private String type;
    private String voice;

    /**将无参构造
     * 设置为包级别的
     * 看反射能不能掉用*/
    private Animal() {

    }
    public Animal(String type,String voice) {
        this.type = type;
        this.voice = voice;
    }

    public String shout(String outVoice){
        System.out.println("outvoice :"+outVoice+ "    invoice:" +voice);
        return voice;
    }
    private void inClass(){
        System.out.println("这个方法是类级别的");
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVoice() {
        return voice;
    }

    public void setVoice(String voice) {
        this.voice = voice;
    }
}
