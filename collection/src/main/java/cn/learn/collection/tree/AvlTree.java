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
    AvlNode<AnyType> left;
    AvlNode<AnyType> right;
    int height;

    public AvlNode(AnyType element) {
      this(element, null, null);
    }

    public AvlNode(AnyType element,
        AvlNode<AnyType> left,
        AvlNode<AnyType> right) {
      this.element = element;
      this.left = left;
      this.right = right;
      height = 0;
    }
  }

  private int height(AvlNode<AnyType> t) {
    return t == null ? -1 : t.height;
  }

  /**
   * 左旋转
   */
  private AvlNode<AnyType> rotateWithLeftChild(AvlNode<AnyType> k2) {

    AvlNode<AnyType> k1 = k2.left;
    k2.left = k1.right;
    k1.right = k2;
    k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
    k1.height = Math.max(height(k1.left), k2.height) + 1;
    return k1;
  }

  /**
   * 右旋转
   */
  private AvlNode<AnyType> rotateWithRightChild(AvlNode<AnyType> k2) {
    AvlNode<AnyType> k1 = k2.right;
    k2.right = k1.left;
    k1.left = k2;
    k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
    k1.height = Math.max(height(k1.right), k2.height) + 1;
    return k1;
  }

}
