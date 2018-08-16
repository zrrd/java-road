package cn.learn.collection.tree;

/**
 * 二叉查找树
 *
 * @author 邵益炯
 * @date 2018/8/14
 */
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>> {

  /**
   * 二叉树结点
   */
  private static class BinaryNode<AnyType> {

    /**
     * 结点数据
     */
    AnyType element;
    /**
     * 左结点
     */
    BinaryNode<AnyType> left;
    /**
     * 右结点
     */
    BinaryNode<AnyType> right;

    BinaryNode(AnyType element, BinaryNode<AnyType> left,
        BinaryNode<AnyType> right) {
      this.element = element;
      this.left = left;
      this.right = right;
    }

    BinaryNode(AnyType element) {
      this(element, null, null);
    }
  }

  private BinaryNode<AnyType> root;

  public BinarySearchTree() {
    root = null;
  }

  public void makeEmpty() {
    root = null;
  }

  public boolean isEmpty() {
    return root == null;
  }

  public boolean contains(AnyType x) {
    return contains(x, root);
  }

  public AnyType findMin() {
    if (isEmpty()) {
      throw new RuntimeException();
    }
    return findMin(root).element;
  }

  public AnyType findMax() {
    if (isEmpty()) {
      throw new RuntimeException();
    }
    return findMax(root).element;
  }

  public void insert(AnyType x) {
    insert(x, root);
  }

  /**
   * 递归得出是否存在该结点
   */
  private boolean contains(AnyType x, BinaryNode<AnyType> t) {
    if (t == null) {
      return false;
    }
    int compareResult = x.compareTo(t.element);
    if (compareResult > 0) {
      return contains(x, t.right);
    } else if (compareResult < 0) {
      return contains(x, t.left);
    } else {
      return true;
    }
  }

  private BinaryNode<AnyType> findMin(BinaryNode<AnyType> t) {
    if (t != null) {
      while (t.right != null) {
        t = t.right;
      }
    }
    return t;
  }

  private BinaryNode<AnyType> findMax(BinaryNode<AnyType> t) {
    if (t != null) {
      while (t.left != null) {
        t = t.left;
      }
    }
    return t;
  }

  private BinaryNode<AnyType> insert(AnyType x, BinaryNode<AnyType> t) {
    if (t == null) {
      return new BinaryNode<>(x, null, null);
    }
    int compareResult = x.compareTo(t.element);
    if (compareResult < 0) {
      t.left = insert(x, t.left);
    } else if (compareResult > 0) {
      t.right = insert(x, t.right);
    } else {
      t.element = x;
    }
    return t;
  }

}
