package com.company.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Eric Cheng
 */
public class N皇后II_52 {

  public static void main(String[] args) {
  }

  public static int totalNQueens(int n) {
    if (n == 0) {
      return 0;
    }
    boolean[][] dp = new boolean[n][n];
    int solotion = 0;
    for (int i = 0; i < n; ++i) {
      Set<Integer> deprecatedRows = new HashSet<>();
      dp = new boolean[n][n];
      dp[i][0] = true;
      deprecatedRows.add(i);
      // fillNearby(dp, i, 0);
      for (int j = 1; j < n; ++j) {
        int validRow = -1;
        for (int k = 0; k < n; ++k) {
          if (deprecatedRows.contains(k)) {
            continue;
          }
        }
      }
    }
    //TODO:
    return -1;
  }

  private static boolean isValidPosition(boolean[][] dp, int row, int column) {
    return false;
  }

  private static void fillNearby(boolean[][] dp, int row, int column) {
    int length = dp.length;
    for (int i = row - 1; i <= row + 1; ++i) {
      if (i < 0 || i > length) {
        continue;
      }
      for (int j = column - 1; j <= column + 1; ++j) {
        if (j < 0 || j > length) {
          continue;
        }
        if (i == row && j == column) {
          continue;
        }
        dp[i][j] = true;
      }
    }
  }
}
