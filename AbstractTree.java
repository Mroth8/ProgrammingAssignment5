/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa5;

/**
 *
 * @author Monte
 */
public abstract class AbstractTree<E extends Comparable<E>>implements Tree<E> {

  /** Inorder traversal from the root*/
  public void inorder() {
  }

  /** Postorder traversal from the root */
  public void postorder() {
  }

  /** Preorder traversal from the root */
  public void preorder() {
  }

  /** Return true if the tree is empty */
  public boolean isEmpty() {
    return getSize() == 0;
  }

  /** Return an iterator to traverse elements in the tree */
  public java.util.Iterator iterator() {
    return null;
  }
}
