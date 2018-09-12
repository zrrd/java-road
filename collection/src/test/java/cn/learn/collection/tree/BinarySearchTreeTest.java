package cn.learn.collection.tree;

import org.junit.Test;

public class BinarySearchTreeTest {

  @Test
  public void delete() {
    BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<>();
    for (int i = 4; i < 10; i++) {
      binarySearchTree.insert(i);
    }
    for (int i = 0; i < 4; i++) {
      binarySearchTree.insert(i);
    }

    binarySearchTree.delete(5);
    System.out.println("a");
  }
}