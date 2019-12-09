package com.company.leetcode;

/**
 * @author Eric Cheng
 */
public class 通配符匹配_44 {

  // 给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。
  public static void main(String[] args) {
    Boolean matchA0 = isMatch("d?*", "?*0");
    assert (!matchA0);
    Boolean matchA = isMatch("aa", "*");
    assert (matchA);
    Boolean matchB = isMatch("adceb", "*a*b");
    assert (matchB);
    Boolean matchC = isMatch("aab", "c*a*b");
    assert (!matchC);
    Boolean matchD = isMatch("mississippi", "m??*ss*?i*pi");

    Boolean matchE = isMatch("", "");
    Boolean matchF = isMatch("", "*");

    System.out.println(matchC);
  }

  /**
   * 自己没想出来，看了主要的讨论就是动态规划，
   * 那么就来分析，如何转化为动态规划问题。
   * 回想之前动态规划，都是 m*n, 从起点走向终点。那么这个问题可以转移为：
   * 矩阵，左边是原始string A, 右边是匹配String P, 从矩阵的左上角，走到右下角，
   * 每一步能匹配就是1，一直到最后一步，可以匹配，最后一步就是1
   * <p>
   * Accepted
   * 1809/1809 cases passed (17 ms)
   * Your runtime beats 65.98 % of java submissions
   * Your memory usage beats 93.77 % of java submissions (38.6 MB)
   */
  public static boolean isMatch(String s, String p) {
    char[] pChars = p.toCharArray();
    char[] sChars = s.toCharArray();
    int m = sChars.length;
    int n = pChars.length;
    // 这里 +1 是因为：如果不+1，如果n=1，那么for循环就永远进不去，那就一直是false
    boolean[][] dp = new boolean[m + 1][n + 1];
    // s,p 长度是0的时候，是否匹配
    dp[0][0] = true;
    for (int j = 1; j <= n; ++j) {
      dp[0][j] = dp[0][j - 1] && pChars[j - 1] == '*';
    }

    for (int i = 1; i <= m; ++i) {
      for (int j = 1; j <= n; ++j) {
        if (pChars[j - 1] == '*') {
          dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
        } else {
          dp[i][j] = dp[i - 1][j - 1] && match(sChars[i - 1], pChars[j - 1]);
        }
      }
    }
    return dp[m][n];
  }

  private static boolean match(char s, char p) {
    return s == p || p == '?' || p == '*';
  }
}
