package com.company.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Eric Cheng
 */
public class 不同路径_62 {

  /**
   * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
   * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
   * 问总共有多少条不同的路径？
   */
  public static void main(String[] args) {
    int a = uniquePaths(3, 2);
    int b = uniquePaths(100, 100);
    System.out.println(a);
    System.out.println(b);
  }

  public static int uniquePaths(int m, int n) {
    // 光 `new HashMap<>()` 就要耗时一定时间（ > 0.5ms），
    Map<String, Integer> pathMap = new HashMap<>();
    return uniquePathsByMap(m, n, pathMap);
  }

  /**
   * Accepted
   * 62/62 cases passed (0 ms)
   * Your runtime beats 100 % of java submissions
   * Your memory usage beats 13.92 % of java submissions (33.1 MB)
   */
  public static int uniquePathsByArray(int m, int n) {
    if (m == 0 || n == 0) {
      return 0;
    }
    int[][] numberOfPaths = new int[m][n];
    for (int i = 0; i < m; i++) {
      numberOfPaths[i][0] = 1;
    }
    for (int j = 0; j < n; j++) {
      numberOfPaths[0][j] = 1;
    }
    for (int i = 1; i < m; i++) {
      for (int j = 1; j < n; j++) {
        numberOfPaths[i][j] = numberOfPaths[i - 1][j] + numberOfPaths[i][j - 1];
      }
    }
    return numberOfPaths[m - 1][n - 1];
    // Time Complexity: O(m * n)
    // Space Complexity: O(m * n)
  }

  /**
   * Accepted
   * 62/62 cases passed (10 ms)
   * Your runtime beats 5.16 % of java submissions
   * Your memory usage beats 5.06 % of java submissions (35.5 MB)
   */
  private static int uniquePathsByMap(int m, int n, Map<String, Integer> pathMap) {
    if (m == 1 || n == 1) {
      return 1;
    }
    for (int i = 1; i < m; i++) {
      for (int j = 1; j < n; j++) {
        String mkey = String.valueOf(i - 1) + "-" + String.valueOf(j);
        String nkey = String.valueOf(i) + "-" + String.valueOf(j - 1);
        int mPath = pathMap.getOrDefault(mkey, 1);
        int nPath = pathMap.getOrDefault(nkey, 1);
        if (mPath < 0 || nPath < 0) {
          System.out.println();
        }
        pathMap.put(String.valueOf(i) + "-" + String.valueOf(j), mPath + nPath);
      }
    }
    return pathMap.get(String.valueOf(m - 1) + "-" + String.valueOf(n - 1));
  }

  private static int uniquePaths0(int m, int n) {
    if (m == 1 || n == 1) {
      return 1;
    }
    return uniquePaths(m - 1, n) + uniquePaths(m, n - 1);
  }
}
