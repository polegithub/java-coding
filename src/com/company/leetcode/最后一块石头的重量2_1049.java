package com.company.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Eric Cheng
 */
public class 最后一块石头的重量2_1049 {

  /**
   * 有一堆石头，每块石头的重量都是正整数。
   * <p>
   * 每一回合，从中选出任意两块石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
   * <p>
   * 如果 x == y，那么两块石头都会被完全粉碎；
   * 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
   * 最后，最多只会剩下一块石头。返回此石头最小的可能重量。如果没有石头剩下，就返回 0。
   * <p>
   * 1 <= stones.length <= 30
   * 1 <= stones[i] <= 1000
   */
  public static void main(String[] args) {
    int[] a = { 2, 7, 4, 1, 8, 1 };
    //    int[] a = { 6, 6, 4, 5, 2 };
    int[] b = { 31, 26, 33, 21, 40 };
    int[] c =
        { 35, 33, 30, 25, 19, 11, 53, 40, 36, 10, 31, 23, 26, 13, 14, 18, 33, 22, 16, 22, 16, 28,
            16, 72, 25, 23, 19 };

    int a1 = lastStoneWeightIIByMaxPQ(c);
    System.out.println(a1);
    //    System.out.println(lastStoneWeightIIByMaxPQ(b));

  }

  /**
   * 网友答案， https://www.zhangjc.site/archives-327/
   * <p>
   * Accepted
   * 82/82 cases passed (3 ms)
   * Your runtime beats 76.54 % of java submissions
   * Your memory usage beats 100 % of java submissions (34.3 MB)
   */
  public static int lastStoneWeightIIByPack(int[] stones) {
    /*
     * 由于石头拿走还能放回去，因此可以简单地把所有石头看作两堆 假设总重量为 sum, 则问题转化为背包问题：如何使两堆石头总重量接近 sum / 2
     */
    int len = stones.length;
    /* 获取石头总重量 */
    int sum = 0;
    for (int i : stones) {
      sum += i;
    }
    /* 定义 dp[i] 重量上限为 i 时背包所能装载的最大石头重量 */
    int maxCapacity = sum / 2;
    int[] dp = new int[maxCapacity + 1];
    for (int i = 0; i < len; i++) {
      int curStone = stones[i];
      for (int j = maxCapacity; j >= curStone; j--) {
        dp[j] = Math.max(dp[j], dp[j - curStone] + curStone);
      }
    }
    return sum - 2 * dp[maxCapacity];
  }

  /**
   * 网友答案，python，我翻译成了java，但是超时了，悲剧。
   * 分别用了最大堆和list，都不行，最后有几十万上百万的结果
   * Time Limit Exceeded
   * 76/82 cases passed (N/A)
   */
  public static int lastStoneWeightIIByMaxPQ(int[] stones) {
    int sum = 0;
    for (int i : stones) {
      sum += i;
    }
    List<Integer> maxResulsList = new ArrayList<>();
    maxResulsList.add(0);
    float target = (float) (sum / 2.0);
    for (int x : stones) {
      List<Integer> list = new ArrayList<>();
      for (int y : maxResulsList) {
        int s = x + y;
        if (s == target) {
          return 0;
        } else if (s < target) {
          list.add(s);
        }
      }
      maxResulsList.addAll(list);
    }
    int max = 0;
    for (int num : maxResulsList) {
      if (num > max) {
        max = num;
      }
    }
    return sum - 2 * max;
  }

  /**
   * 错误答案， 失败的case： [31,26,33,21,40]， 应该是5，但这种算出来是9
   * 排序，然后最后一个减去前一个。循环。
   */
  private static int lastStoneWeightIIBySortAndMergeLast(int[] stones) {
    Arrays.sort(stones);
    int lastIndex = stones.length - 1;
    while (lastIndex > 0) {
      mergeLast(stones, lastIndex);
      if (stones[lastIndex - 1] != 0) {
        insertSort(stones, lastIndex - 1);
        lastIndex--;
      } else {
        lastIndex -= 2;
      }
    }
    return stones[0];
  }

  private static void mergeLast(int[] stones, int lastIndex) {
    if (lastIndex < 1) {
      return;
    }
    stones[lastIndex - 1] = stones[lastIndex] - stones[lastIndex - 1];
    stones[lastIndex] = 0;
  }

  private static void insertSort(int[] stones, int lastIndex) {
    if (lastIndex < 1) {
      return;
    }
    int last = stones[lastIndex];
    int targetIndex = lastIndex;
    for (int i = lastIndex - 1; i >= 0; i--) {
      if (stones[i] < last) {
        break;
      }
      stones[i + 1] = stones[i];
      targetIndex = i;
    }
    if (stones[targetIndex] != last) {
      stones[targetIndex] = last;
    }
  }
}
