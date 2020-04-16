package cn.learn.utils.guava.graphs;

import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableGraph;

/**
 * 图工具类
 *
 * @author shaoyijiong
 * @date 2020/4/16
 */
@SuppressWarnings("all")
public class GraphsTest {

  /**
   * <pre>
   * 如果graphs是有向图 nodeU 通过edgeUv指向 nodeV
   * 如果graphs是无向图 nodeU 通过edgeUv互相连接
   *
   * common.graphs 支持以下图
   * <li>有向图</li>
   * <li>无向图</li>
   * <li>带权重的节点或者边</li>
   * <li>不支持自环的图(边的出入点都为同一个节点)</li>
   * <li>不支持平行的图(有向图两个节点有同方向的边,无向图两个节点有多个边)</li>
   * </pre>
   *
   * <pre>
   *  三种类型
   *  <li>Graph   最普通的图</li>
   *  <li>ValueGraph  边具有权重</li>
   *  <li>Network 边是全局唯一的 可以使用平行边</li>
   * </pre>
   */
  private static void definitionsTest() {
    // nodeU nodeV edgeUv
  }


  private static void instances() {
    MutableGraph<Integer> graph = GraphBuilder.undirected().build();

  }
}
