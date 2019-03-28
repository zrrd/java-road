package cn.learn.datastructure.tree;

/**
 * 二叉查找树
 *
 * @author 邵益炯
 * @date 2018/8/14
 */
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>> {

  /**
   * 二叉树结点 原理 1.左结点始终小于根结点 2.右结点始终大于根结点 3.左右子树也能够称为二叉排序树
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

  /**
   * 二叉查找树root结点
   */
  private BinaryNode<AnyType> root;

  /**
   * 新建二叉查找树
   */
  public BinarySearchTree() {
    root = null;
  }

  /**
   * 清空二叉查找树
   */
  public void makeEmpty() {
    root = null;
  }

  /**
   * 判断二叉查找树是否为空
   *
   * @return true 空 false 不为空
   */
  public boolean isEmpty() {
    return root == null;
  }

  /**
   * 二叉查找树是否包含某个结点
   *
   * @param x 结点
   * @return true 包含 false 不包含
   */
  public boolean contains(AnyType x) {
    return contains(x, root);
  }

  /**
   * 寻找二叉树最小的结点
   *
   * @return 结点信息
   */
  public AnyType findMin() {
    if (isEmpty()) {
      throw new RuntimeException();
    }
    return findMin(root).element;
  }

  /**
   * 寻找二叉树最大的结点
   *
   * @return 结点信息
   */
  public AnyType findMax() {
    if (isEmpty()) {
      throw new RuntimeException();
    }
    return findMax(root).element;
  }

  public void insert(AnyType x) {
    root = insert(x, root);
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

  /**
   * 二叉查找树找最大 一直向左找
   */
  private BinaryNode<AnyType> findMax(BinaryNode<AnyType> t) {
    if (t != null) {
      while (t.left != null) {
        t = t.left;
      }
    }
    return t;
  }


  /**
   * 插入二叉查找树 递归插入
   *
   * @param x 结点
   */
  private BinaryNode<AnyType> insert(AnyType x, BinaryNode<AnyType> t) {
    //头结点为空 将这个数据设置为头结点
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

  public BinaryNode<AnyType> delete(AnyType x) {
    return delete(x, root);
  }

  private BinaryNode<AnyType> delete(AnyType x, BinaryNode<AnyType> t) {
    if (t == null) {
      return null;
    }
    int compareResult = x.compareTo(t.element);
    if (compareResult > 0) {
      t.right = delete(x, t.right);
    } else if (compareResult < 0) {
      t.left = delete(x, t.left);
    } else if (t.left != null && t.right != null) {
      t.element = findMin(t.right).element;
      t.right = delete(x, t.right);
    } else {
      t = (t.left != null) ? t.left : t.right;
    }
    return t;
  }
}
