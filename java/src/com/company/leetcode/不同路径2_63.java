package com.company.leetcode;

/**
 * @author Eric Cheng
 */
public class 不同路径2_63 {

  public static void main(String[] args) {
    int[][] a = { { 0, 0, 0 }, { 0, 1, 0 }, { 0, 0, 0 } };
    int[][] b = { { 1, 0 } };
    int[][] c = { { 0, 0 }, { 0, 1 } };

    int a1 = uniquePathsWithObstacles(c);
    System.out.println(a);
    System.out.println(b);
  }

  /**
   * Accepted
   * 43/43 cases passed (1 ms)
   * Your runtime beats 97.51 % of java submissions
   * Your memory usage beats 5.08 % of java submissions (39.1 MB)
   */
  public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
    if (obstacleGrid.length == 0 || obstacleGrid[0].length == 0) {
      return 0;
    }
    int m = obstacleGrid.length;
    int n = obstacleGrid[0].length;
    int[][] numberOfPaths = new int[m][n];
    boolean hasBlock = false;
    for (int i = 0; i < m; i++) {
      hasBlock = hasBlock || obstacleGrid[i][0] == 1;
      numberOfPaths[i][0] = hasBlock ? 0 : 1;
    }
    hasBlock = false;
    for (int j = 0; j < n; j++) {
      hasBlock = hasBlock || obstacleGrid[0][j] == 1;
      numberOfPaths[0][j] = hasBlock ? 0 : 1;
    }
    for (int i = 1; i < m; i++) {
      for (int j = 1; j < n; j++) {
        if (obstacleGrid[i][j] == 1) {
          numberOfPaths[i][j] = 0;
        } else {
          int iPath = obstacleGrid[i - 1][j] == 1 ? 0 : numberOfPaths[i - 1][j];
          int jPath = obstacleGrid[i][j - 1] == 1 ? 0 : numberOfPaths[i][j - 1];
          numberOfPaths[i][j] = iPath + jPath;
        }
      }
    }
    return numberOfPaths[m - 1][n - 1];
  }
}
