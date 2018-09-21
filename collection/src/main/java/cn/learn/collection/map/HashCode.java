package cn.learn.collection.map;

/**
 * 一个分部良好的哈希函数
 *
 * @author 邵益炯
 * @date 2018/9/19
 */
public class HashCode {

  public static int hash(String key, int tableSize) {
    int hashVal = 0;
    for (int i = 0; i < key.length(); i++) {
      hashVal = 37 * hashVal + key.charAt(i);
    }
    hashVal %= tableSize;
    if (hashVal < 0) {
      hashVal += tableSize;
    }
    return hashVal;
  }

}
