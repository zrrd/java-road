package cn.learn.collection.datastructure.map;

/**
 * 自定义hashmap
 *
 * @author 邵益炯
 * @date 2018/9/20
 */
public class MyHashMap {

  private int hash = 0;

  @Override
  public int hashCode() {
    if (hash != 0) {
      return hash;
    }

    for (int i = 0; i < 10; i++) {
      hash = hash * 31 + 0;
    }
    return hash;
  }
}
