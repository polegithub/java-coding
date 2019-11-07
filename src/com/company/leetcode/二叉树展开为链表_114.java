package com.company.leetcode;

import com.company.TreeNode;

/**
 * @author Eric Cheng
 */
public class 二叉树展开为链表_114 {

  public static void main(String[] args) {
    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(2);
    root.left.left = new TreeNode(3);
    root.left.right = new TreeNode(4);
    root.right = new TreeNode(5);
    root.right.left = new TreeNode(6);
    root.right.right = new TreeNode(7);
    flatten(root);
    System.out.println(root);
  }

  /**
   * Accepted
   * 225/225 cases passed (1 ms)
   * Your runtime beats 97.9 % of java submissions
   * Your memory usage beats 81.11 % of java submissions (36.1 MB)
   */
  public static void flatten(TreeNode root) {
    if (root == null) {
      return;
    }
    flatten(root.left);
    flatten(root.right);
    if (root.left != null) {
      TreeNode rightChild = root.right;
      root.right = root.left;
      root.left = null;
      if (rightChild != null) {
        lastRightChild(root.right).right = rightChild;
      }
    }
  }

  // if right is null, return self
  private static TreeNode lastRightChild(TreeNode root) {
    if (root == null || root.right == null) {
      return root;
    }
    return lastRightChild(root.right);
  }

}
