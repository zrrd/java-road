package cn.learn.enuml.enumset;

import java.util.EnumSet;
import java.util.Set;

public class Text {
    public  enum Style{
        BOLD,INALIC,UNDERLINE,STRIKETHROUGH
    }
    public void applyStyles(Set<Style> styles){

    }
    /**通过allof方法获得一个拥有所有枚举类型的EnumSet*/
    Set<Style> enumSet = EnumSet.allOf(Style.class);
    /**
     * 通过of方法返回部分元素的EnumSet
     * */
    Set<Style> enumSet1 = EnumSet.of(Style.BOLD);

    public void enumSetTest(){
        enumSet.forEach(style ->  System.out.print(style.name()+"  "));
        System.out.println("\r");
        enumSet1.forEach(style ->  System.out.print(style.name()+"  "));
    }
}
