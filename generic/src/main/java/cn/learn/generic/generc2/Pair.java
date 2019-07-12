package cn.learn.generic.generc2;

/**
 * @author shaoyijiong
 * @date 2019/7/11
 */
public class Pair<K, V> {


  private K key;


  private V value;


  public Pair(K key, V value) {

    this.key = key;
    this.value = value;
  }


  public void setKey(K key) {
    this.key = key;
  }


  public void setValue(V value) {
    this.value = value;
  }


  public K getKey() {
    return key;
  }


  public V getValue() {
    return value;
  }
}
