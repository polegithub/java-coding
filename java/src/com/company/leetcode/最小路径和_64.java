package com.company.leetcode;

/**
 * @author Eric Cheng
 */
public class 最小路径和_64 {

  public static void main(String[] args) {
    int[][] a = { { 1, 3, 1 }, { 1, 5, 1 }, { 4, 2, 1 } };
    int[][] b = { { 1, 0 } };
    int[][] c = { { 0, 0 }, { 0, 1 } };

    int a1 = minPathSum(a);
    System.out.println(a1);
    System.out.println(b);
  }

  /**
   * Accepted
   * 61/61 cases passed (3 ms)
   * Your runtime beats 94.01 % of java submissions
   * Your memory usage beats 87.19 % of java submissions (39.5 MB)
   */
  public static int minPathSum(int[][] grid) {
    if (grid.length == 0 || grid[0].length == 0) {
      return 0;
    }
    int m = grid.length;
    int n = grid[0].length;
    int[][] minPathStore = new int[m][n];

    for (int i = 0; i < m; ++i) {
      if (i > 0) {
        minPathStore[i][0] = minPathStore[i - 1][0] + grid[i][0];
      } else {
        minPathStore[i][0] = grid[i][0];
      }
    }
    for (int i = 0; i < n; ++i) {
      if (i > 0) {
        minPathStore[0][i] = minPathStore[0][i - 1] + grid[0][i];
      } else {
        minPathStore[0][i] = grid[0][i];
      }
    }
    for (int i = 1; i < m; ++i) {
      for (int j = 1; j < n; ++j) {
        minPathStore[i][j] =
            Integer.min(minPathStore[i - 1][j], minPathStore[i][j - 1]) + grid[i][j];
      }
    }
    return minPathStore[m - 1][n - 1];
  }

}
