package com.company.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.company.TreeNode;

/**
 * @author Eric Cheng
 */
public class 二叉树的前序遍历_144 {

  public static void main(String[] args) {
    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(2);
    root.left.left = new TreeNode(3);
    root.left.right = new TreeNode(4);
    root.right = new TreeNode(5);
    root.right.left = new TreeNode(6);
    root.right.right = new TreeNode(7);
    List<Integer> list = preorderTraversalByRecursive(root);
    System.out.println(list);
  }

  public static List<Integer> preorderTraversalByRecursive(TreeNode root) {
    List<Integer> list = new ArrayList<>();
    preOrderTraversalByLoop(root, list);
    return list;
  }

  /**
   * Accepted
   * 68/68 cases passed (1 ms)
   * Your runtime beats 95.69 % of java submissions
   * Your memory usage beats 42.59 % of java submissions (34.6 MB)
   */
  private static void preOrderTraversalByRecursive(TreeNode root, List<Integer> list) {
    if (root == null) {
      return;
    }
    list.add(root.val);
    preOrderTraversalByRecursive(root.left, list);
    preOrderTraversalByRecursive(root.right, list);
  }

  //Accepted
  //68/68 cases passed (1 ms)
  //Your runtime beats 95.69 % of java submissions
  //Your memory usage beats 43.7 % of java submissions (34.5 MB)
  private static void preOrderTraversalByLoop(TreeNode root, List<Integer> list) {
    if (root == null) {
      return;
    }
    List<TreeNode> leftTreeNoodes = new ArrayList<>();
    leftTreeNoodes.add(root);
    while (leftTreeNoodes.size() > 0) {
      TreeNode node = leftTreeNoodes.get(0);
      list.add(node.val);
      leftTreeNoodes.remove(0);
      if (node.right != null) {
        leftTreeNoodes.add(0, node.right);
      }
      if (node.left != null) {
        leftTreeNoodes.add(0, node.left);
      }

    }
  }

  //Accepted
  //68/68 cases passed (1 ms)
  //Your runtime beats 95.69 % of java submissions
  //Your memory usage beats 41.89 % of java submissions (34.6 MB)
  private static void preOrderTraversalByLoop2(TreeNode root, List<Integer> list) {
    if (root == null) {
      return;
    }
    Stack<TreeNode> leftTreeNoodes = new Stack<>();
    leftTreeNoodes.push(root);
    while (leftTreeNoodes.size() > 0) {
      TreeNode node = leftTreeNoodes.pop();
      list.add(node.val);
      if (node.right != null) {
        leftTreeNoodes.push(node.right);
      }
      if (node.left != null) {
        leftTreeNoodes.push(node.left);
      }
    }
  }

  // 逐层的遍历，每层横向遍历结束，然后进入下一层
  private static void layerOrderTraversalByLoop(TreeNode root, List<Integer> list) {
    if (root == null) {
      return;
    }
    List<TreeNode> leftTreeNoodes = new ArrayList<>();
    leftTreeNoodes.add(root);
    while (leftTreeNoodes.size() > 0) {
      TreeNode node = leftTreeNoodes.get(0);
      list.add(node.val);
      leftTreeNoodes.remove(0);
      if (node.left != null) {
        leftTreeNoodes.add(node.left);
      }
      if (node.right != null) {
        leftTreeNoodes.add(node.right);
      }
    }
  }
}
