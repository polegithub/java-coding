package com.company.leetcode;

/**
 * @author Eric Cheng
 */
public class 判断子序列_392 {

  /**
   * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
   * 你可以认为 s 和 t 中仅包含英文小写字母。字符串 t 可能会很长（长度 ~= 500,000），而 s 是个短字符串（长度 <=100）。
   * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
   */
  public static void main(String[] args) {
    //    System.out.println(isSubsequence2("adwad", "dwakkdwadawdkkokdoowawa"));
    System.out.println(isSubsequence2("abc", "ahbgdc"));
  }

  /**
   * 原始版本：
   * Accepted
   * 14/14 cases passed (20 ms)
   * Your runtime beats 53.99 % of java submissions
   * Your memory usage beats 95.23 % of java submissions (49.4 MB)
   * <p>
   * <p>
   * 优化：找到之后，直接break
   * Accepted
   * 14/14 cases passed (7 ms)
   * Your runtime beats 72.25 % of java submissions
   * Your memory usage beats 95.23 % of java submissions (49.9 MB)
   */
  public static boolean isSubsequence(String s, String t) {
    if (s == null || s.length() == 0) {
      return true;
    }
    if (t == null || t.length() == 0) {
      return false;
    }
    char[] sChars = s.toCharArray();
    char[] tChar = t.toCharArray();
    int lastValidIndex = 0;
    for (char sChar : sChars) {
      boolean found = false;
      for (int j = lastValidIndex; j < tChar.length; ++j) {
        if (tChar[j] == sChar) {
          found = true;
          lastValidIndex = j + 1;
          break;
        }
      }
      if (!found) {
        return false;
      }
    }
    return true;
  }

  /**
   * 网友的思路，不用for循环，直接用indexOf()
   * Accepted
   * 14/14 cases passed (1 ms)
   * Your runtime beats 92.95 % of java submissions
   * Your memory usage beats 95.23 % of java submissions (49.2 MB)
   */
  public static boolean isSubsequence2(String s, String t) {
    if (s == null || s.length() == 0) {
      return true;
    }
    if (t == null || t.length() == 0) {
      return false;
    }
    char[] sChars = s.toCharArray();
    int lastValidIndex = -1;
    for (char sChar : sChars) {
      lastValidIndex = t.indexOf(sChar, lastValidIndex + 1);
      if (lastValidIndex == -1) {
        return false;
      }
    }
    return true;
  }
}
