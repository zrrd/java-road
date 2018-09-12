package cn.learn.collection.tree;

/**
 * 平衡二叉树
 *
 * @author 邵益炯
 * @date 2018/9/10
 */
public class AvlTree<AnyType> {

  private static class AvlNode<AnyType> {

    AnyType element;
    AvlNode<AvlNode> left;
    AvlNode<AvlNode> right;
    int height;

    public AvlNode(AnyType element) {
      this(element, null, null);
    }

    public AvlNode(AnyType element,
        AvlNode<AvlNode> left,
        AvlNode<AvlNode> right) {
      this.element = element;
      this.left = left;
      this.right = right;
      height = 0;
    }
  }


}
