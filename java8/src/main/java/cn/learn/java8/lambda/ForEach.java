package cn.learn.java8.lambda;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ForEach {
    public void forEach(){
        Map<String,Integer> map = new HashMap<>(16);
        map.put("a",10);
        map.put("b",20);
        map.put("c",30);
        map.put("d",40);
        map.put("e",50);

        long beginTime = System.nanoTime();
        //通常方法1  entrySet遍历  entry是map里面定义的一个接口
        for (Map.Entry<String,Integer> entry:map.entrySet()){
            System.out.println("1");
        }
        long endTime = System.nanoTime();
        long costTime = (endTime-beginTime)/1000;
        System.out.println("forEach遍历时间：" +costTime);


        beginTime = System.nanoTime();
        //通常方法2 Iterator接口遍历
        Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            System.out.println("1");
            Map.Entry<String,Integer> entry = iterator.next();
        }
        endTime = System.nanoTime();
        costTime = (endTime-beginTime)/1000;
        System.out.println("iterator遍历时间："+costTime);

        beginTime = System.nanoTime();
        //通常方法3 遍历keySet 通过key 取值
        for (String key : map.keySet()){
            Integer value = map.get(key);
        }
        endTime = System.nanoTime();
        costTime = (endTime-beginTime)/1000;
        System.out.println("遍历key再取值 遍历时间："+costTime);


        beginTime = System.nanoTime();
        //java8 foreach+lambda表达式遍历
        map.forEach((k,v)-> {

        });
        endTime = System.nanoTime();
        costTime = (endTime-beginTime)/1000;
        System.out.println("java8 forEach遍历时间："+costTime);
    }
}
