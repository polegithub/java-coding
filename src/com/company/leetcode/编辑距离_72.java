package com.company.leetcode;

/**
 * @author Eric Cheng
 */
public class 编辑距离_72 {

  public static void main(String[] args) {
    int a = minDistance2("a", "c");
    //    int a = minDistance2("intention", "execution");
    System.out.println(a);
    int b1 = minDistance1("pneumonoultramicroscopicsilicovolcanoconiosis", "ultramicroscopically");
    int b2 = minDistance2("pneumonoultramicroscopicsilicovolcanoconiosis", "ultramicroscopically");
    int b3 = minDistanceLeetCodeFriend("pneumonoultramicroscopicsilicovolcanoconiosis",
        "ultramicroscopically");
    System.out.println(b1);
    System.out.println(b2);
    System.out.println(b3);
    int c = minDistance2("pneumonoultramicroscopicsilicovolcanoconiosis", "ultramicroscopical");
    //    System.out.println(c);
    int d = minDistance2("pneumonoultramicroscopicsilicovolcanoconiosis", "ultramicroscopic");
    //    System.out.println(d);
  }

  /**
   * Accepted
   * 1146/1146 cases passed (6 ms)
   * Your runtime beats 98.73 % of java submissions
   * Your memory usage beats 95.23 % of java submissions (35.9 MB)
   */
  public static int minDistance2(String word1, String word2) {
    // 真实生产环境，应该这么写：
    //    if (word2 == null || word2.length() == 0) {
    //      return word1 == null ? 0 : word1.length();
    //    }
    //    if (word1 == null || word1.length() == 0) {
    //      return word2.length();
    //    }
    if (word1.length() == 0 || word2.length() == 0) {
      return word1.length() + word2.length();
    }
    char[] word1Arr = word1.toCharArray();
    char[] word2Arr = word2.toCharArray();
    int m = word1Arr.length;
    int n = word2Arr.length;
    int[][] minDisStore = new int[m][n];
    boolean isFirstSame = word1Arr[0] == word2Arr[0];
    minDisStore[0][0] = isFirstSame ? 0 : 1;
    boolean hasSameLetter = isFirstSame;
    for (int i = 1; i < m; i++) {
      boolean isCurSame = word1Arr[i] == word2Arr[0];
      int lastStep = (hasSameLetter || isCurSame) ? 0 : 1;
      minDisStore[i][0] = i + lastStep;
      hasSameLetter = hasSameLetter || isCurSame;
    }
    hasSameLetter = isFirstSame;
    for (int j = 1; j < n; j++) {
      boolean isCurSame = word1Arr[0] == word2Arr[j];
      int lastStep = (hasSameLetter || isCurSame) ? 0 : 1;
      minDisStore[0][j] = j + lastStep;
      hasSameLetter = hasSameLetter || isCurSame;
    }

    for (int i = 1; i < m; i++) {
      for (int j = 1; j < n; j++) {
        int leftAndDown = minDisStore[i - 1][j - 1];
        if (word1Arr[i] == word2Arr[j]) {
          minDisStore[i][j] = leftAndDown;
        } else {
          int left = minDisStore[i - 1][j];
          int down = minDisStore[i][j - 1];
          int minStepBefore = min(left, down, leftAndDown);
          minDisStore[i][j] = minStepBefore + 1;
        }
      }
    }
    return minDisStore[m - 1][n - 1];
  }

  private static int min(int a, int b, int c) {
    return Math.min(Math.min(a, b), c);
  }

  // ------------------------------------- 1.0 版本答案，卡在 "pneumon..." ------------------------------------------ //
  public static int minDistance1(String word1, String word2) {
    if (word2 == null || word2.length() == 0) {
      return word1 == null ? 0 : word1.length();
    }
    if (word1 == null || word1.length() == 0) {
      return word2.length();
    }
    char[] word1Arr = word1.toCharArray();
    char[] word2Arr = word2.toCharArray();
    int m = word1Arr.length;
    int n = word2Arr.length;
    int[][] minDisStore = new int[m][n];
    minDisStore[0][0] = word1Arr[0] == word2Arr[0] ? 0 : 1;

    // 这里其实是有bug的，因为只考虑的最后一个是否相等，比如 "abab" -> "b", 这里"b"重复计算了2次
    for (int i = 1; i < m; i++) {
      int lastStep = word1Arr[i] == word2Arr[0] ? 0 : 1;
      minDisStore[i][0] = minDisStore[i - 1][0] + lastStep;
    }
    for (int j = 1; j < n; j++) {
      int lastStep = word1Arr[0] == word2Arr[j] ? 0 : 1;
      minDisStore[0][j] = minDisStore[0][j - 1] + lastStep;
    }

    //    for (int i = 0; i < m; i++) {
    //      minDisStore[i][0] = i;
    //    }
    //    for (int j = 0; j < n; j++) {
    //      minDisStore[0][j] = j;
    //    }

    for (int i = 1; i < m; i++) {
      for (int j = 1; j < n; j++) {
        if (word1Arr[i] == word2Arr[j]) {
          minDisStore[i][j] = minDisStore[i - 1][j - 1];
        } else {
          int minBefoore =
              min(minDisStore[i - 1][j], minDisStore[i][j - 1], minDisStore[i - 1][j - 1]);
          minDisStore[i][j] = minBefoore + 1;
        }
      }
    }
    return minDisStore[m - 1][n - 1];
  }
  // ------------------------------------- 网友答案 ------------------------------------------ //

  // ------------------------------------- 网友答案 ------------------------------------------ //

  /**
   * 个人分析，这里慢的主要原因是 `word2.charAt(j - 1)` 耗时旧，改成：`word2.toCharArray()`, 然后直接数组取肯定快很多
   * <p>
   * Accepted
   * 1146/1146 cases passed (14 ms)
   * Your runtime beats 55.75 % of java submissions
   * Your memory usage beats 95.23 % of java submissions (36.1 MB)
   */
  private static int minDistanceLeetCodeFriend(String word1, String word2) {
    int m = word1.length();
    int n = word2.length();

    int[][] cost = new int[m + 1][n + 1];
    for (int i = 0; i <= m; ++i) {
      cost[i][0] = i;
    }
    for (int j = 0; j <= n; ++j) {
      cost[0][j] = j;
    }
    for (int i = 1; i <= m; ++i) {
      for (int j = 1; j <= n; ++j) {
        if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
          cost[i][j] = cost[i - 1][j - 1];
        } else {
          cost[i][j] = 1 + Math.min(cost[i - 1][j - 1], Math.min(cost[i][j - 1], cost[i - 1][j]));
        }
      }
    }
    return cost[m][n];
  }
  // ------------------------------------- 网友答案 ------------------------------------------ //
}
