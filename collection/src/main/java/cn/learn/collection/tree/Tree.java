package cn.learn.collection.tree;

/**
 * 树
 *
 * @author 邵益炯
 * @date 2018/8/14
 */
public class Tree {

  /**
   * 树节点
   */
  class TreeNode {

    /**
     * 结点数据
     */
    Object element;
    /**
     * 兄弟结点
     */
    TreeNode firstChild;
    /**
     * 下一个兄弟结点
     */
    TreeNode nextSibling;

  }

  /**
   * 二叉树结点
   */
  class BinaryNode {

    /**
     * 结点数据
     */
    Object element;
    /**
     * 左结点
     */
    BinaryNode left;
    /**
     * 右结点
     */
    BinaryNode right;

  }


}
