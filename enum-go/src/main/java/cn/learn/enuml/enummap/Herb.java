package cn.learn.enuml.enummap;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author shaoyijiong
 */
public class Herb {

  public enum Type {
    /*一年生*
     * 多年生
     * 两年生
     */
    ANNUAL, PERENNIAL, BIENNIAL
  }

  private final String name;
  private final Type type;

  public Herb(String name, Type type) {
    this.name = name;
    this.type = type;
  }

  @Override
  public String toString() {
    return name;
  }

  /**
   * 植物通过几年生分类
   */
  public void EnumMapTest(List<Herb> garden) {
    Map<Type, Set<Herb>> herbBYType = new EnumMap<>(Type.class);
    for (Type type : Type.values()) {
      /* System.out.println(type.name());
       System.out.println(type);
       这两个代码段打印出来是一个意思     */
      herbBYType.put(type, new HashSet<>());
    }
    for (Herb h : garden) {
      herbBYType.get(h.type).add(h);
    }
    System.out.println(herbBYType);
  }
}
