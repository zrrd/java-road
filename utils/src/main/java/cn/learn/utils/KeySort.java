package cn.learn.utils;

/**
 * @author shaoyijiong
 * @date 2019/8/6
 */

import java.util.*;

public class KeySort {

  public static String  getKeySortString(Map<String, String> map,String endKey,String endValue) {
    String data = "";
    if(map == null && map.size() == 0){
      return  null;
    }
    List<Map.Entry<String, String>> list = new ArrayList<Map.Entry<String, String>>(map.entrySet());
    Collections.sort(list, new Comparator<Map.Entry<String, String>>() {
      public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
        return o1.getKey().compareTo(o2.getKey());
      }
    });
    for (int i = 0; i < list.size() ; i++) {
      data +=  String.valueOf(list.get(i))+"&";
    }
    return  data+endKey+"="+endValue;
  }

  public static void main(String[] args) {

    Map<String, String> map = new TreeMap<String, String>();
    map.put("ed", "ddddd");
    map.put("cd", "ccccc");
    map.put("cid", "ccccc");
    map.put("chd", "ccccc");

    List<Map.Entry<String, String>> list = new ArrayList<Map.Entry<String, String>>(map.entrySet());
    Collections.sort(list, new Comparator<Map.Entry<String, String>>() {
      public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
        return o1.getKey().compareTo(o2.getKey());
      }
    });
    String string = null;
    for (int i = 0; i < list.size() ; i++) {
      string +=  String.valueOf(list.get(i))+"&";
    }
    System.out.println(string);
  }

}
