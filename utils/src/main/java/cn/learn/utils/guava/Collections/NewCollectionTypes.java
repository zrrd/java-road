package cn.learn.utils.guava.Collections;

import com.google.common.collect.BiMap;
import com.google.common.collect.ClassToInstanceMap;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.MultimapBuilder;
import com.google.common.collect.Multiset;
import com.google.common.collect.MutableClassToInstanceMap;
import com.google.common.collect.Range;
import com.google.common.collect.RangeSet;
import com.google.common.collect.Table;
import com.google.common.collect.TreeRangeSet;
import java.util.List;

/**
 * Guava的新类型
 *
 * @author 邵益炯
 * @date 2018/9/29
 */
@SuppressWarnings("all")
public class NewCollectionTypes {

  private static void multisetTest() {
    //运行重复不保证顺序
    //统计字符串在数组中出现的次数 相当于很多桶 每个桶存放了很多相同类型的元素
    ImmutableList<String> strings = ImmutableList.of("a", "b", "c", "d", "a", "c", "d", "d");
    Multiset<String> wordsMultiset = HashMultiset.create();
    wordsMultiset.addAll(strings);
    for (String key : wordsMultiset.elementSet()) {
      System.out.println(key + " count：" + wordsMultiset.count(key));
    }
  /*　　　　add(E element) :向其中添加单个元素
　　　　add(E element,int occurrences) : 向其中添加指定个数的元素
　　　　count(Object element) : 返回给定参数元素的个数
　　　　remove(E element) : 移除一个元素，其count值 会响应减少
　　　　remove(E element,int occurrences): 移除相应个数的元素
　　　　elementSet() : 将不同的元素放入一个Set中
　　　　entrySet(): 类似与Map.entrySet 返回Set<Multiset.Entry>。包含的Entry支持使用getElement()和getCount()
　　　　setCount(E element ,int count): 设定某一个元素的重复次数
　　　　setCount(E element,int oldCount,int newCount): 将符合原有重复个数的元素修改为新的重复次数
　　　　retainAll(Collection c) : 保留出现在给定集合参数的所有的元素
　　　　removeAll(Collectionc) : 去除出现给给定集合参数的所有的元素
*/
  }

  private static void multimapTest() {
    //用于处理 Map<K, List<V>> Map<K, Set<V>> 之类的结构
    //key 可以选择hashkeys linkhashkeys treekeys 等多种key的结构
    //value也可以选择多种value结构
    ListMultimap<String, Integer> multimap =
        MultimapBuilder.hashKeys().arrayListValues().build();
    multimap.put("a", 1);
    multimap.put("a", 2);
    List<Integer> list = multimap.get("a");
    System.out.println(list);
  }

  private static void biMapTest() {
    //k v 是唯一不能重复的
    BiMap<String, Integer> biMap = HashBiMap.create();
    //将k v 倒转
    BiMap<Integer, String> inverse = biMap.inverse();

  }

  private static void tableTest() {
    Table<Integer, Integer, Double> weightedGraph = HashBasedTable.create();
    //行 列
    weightedGraph.put(1, 2, 4.1);
    weightedGraph.put(1, 3, 20.2);
    weightedGraph.put(2, 2, 52.5);
  }

  private static void classToInstanceMapTest() {
    ClassToInstanceMap classToInstanceMap = MutableClassToInstanceMap.create();
    classToInstanceMap.put(Integer.class, 0);
    classToInstanceMap.put(Integer.class, 2);
    System.out.println(classToInstanceMap.size());
  }

  private static void rangeSetTest() {
    RangeSet<Integer> rangeSet = TreeRangeSet.create();
    rangeSet.add(Range.closed(1, 10)); // {[1, 10]}
    rangeSet.add(Range.closedOpen(11, 15)); // disconnected range: {[1, 10], [11, 15)}
    rangeSet.add(Range.closedOpen(15, 20)); // connected range; {[1, 10], [11, 20)}
    rangeSet.add(Range.openClosed(0, 0)); // empty range; {[1, 10], [11, 20)}
    rangeSet.remove(Range.open(5, 10)); // splits [1, 10]; {[1, 5], [10, 10], [11, 20)}
  }

  public static void main(String[] args) {
    multisetTest();
    multimapTest();
    classToInstanceMapTest();
  }


}
