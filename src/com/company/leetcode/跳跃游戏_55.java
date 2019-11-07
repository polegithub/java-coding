package com.company.leetcode;

/**
 * @author Eric Cheng
 */
public class 跳跃游戏_55 {

  public static void main(String[] args) {
    int[] preA0 = { 2, 3, 1, 1, 4 };
    boolean success0 = canJump(preA0);
    System.out.println("Success: " + success0);
    int[] preA1 = { 3, 2, 1, 0, 4 };
    boolean success1 = !canJump(preA1);
    System.out.println("Success: " + success1);

    int[] preA2 = { 2, 0 };
    boolean success2 = canJump(preA2);
    System.out.println("Success: " + success2);
  }

  public static boolean canJump(int[] nums) {
    if (nums.length == 0) {
      return false;
    }
    int[] isValid = new int[nums.length];
    isValid[nums.length - 1] = 1;
    for (int i = nums.length - 1 - 1; i >= 0; i--) {
      updateValidMap(nums, i, isValid);
    }

    return isValid[0] == 1;
  }

  /**
   * 递归算法，失败了
   * Time Limit Exceeded
   * 74/75 cases passed (N/A)
   */
  private static boolean canJump(int[] nums, int startIndex) {
    if (nums.length == 0) {
      return false;
    }

    int step = nums[startIndex];
    if (step >= nums.length - 1 - startIndex) {
      return true;
    }
    for (int j = 1; j <= step; ++j) {
      if (canJump(nums, startIndex + j)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Accepted
   * 75/75 cases passed (127 ms)
   * Your runtime beats 27.78 % of java submissions
   * Your memory usage beats 74.82 % of java submissions (41.2 MB)
   */
  private static void updateValidMap(int[] nums, int startIndex, int[] isValid) {
    int step = nums[startIndex];
    for (int j = 1; j <= step; ++j) {
      if (step >= nums.length - 1 - startIndex || isValid[startIndex + j] == 1) {
        isValid[startIndex] = 1;
      }
    }
  }

  /**
   * 网友最佳答案：
   * 我们要想，为什么到不了最终的点，说明中间有点断了。
   * 翻译过来就是如果有点断了，那么这个点之后的点，都瓦特了。
   * 这块有些绕，但是想清楚了，这个答案就很简单了，所以是个偏数学方法的问题
   * <p>
   * Accepted
   * 75/75 cases passed (2 ms)
   * Your runtime beats 83.04 % of java submissions
   * Your memory usage beats 94.9 % of java submissions (38.4 MB)
   */
  private boolean hasDisconnectedNum(int[] nums) {
    int reachable = 0;
    for (int i = 0; i < nums.length; ++i) {
      if (i > reachable)
        return false;
      reachable = Math.max(reachable, i + nums[i]);
    }
    return true;
  }

}
