package com.company.leetcode;

/**
 * @author Eric Cheng
 */
public class Z字形变换_6 {

  public static void main(String[] args) {
    String a = "PAHNAPLSIIGYIR";
    System.out.println(convert("abcd", 3));
    //System.out.println(convert("LEETCODEISHIRING", 4));
  }

  /**
   * ✔ Accepted
   * ✔ 1158/1158 cases passed (11 ms)
   * ✔ Your runtime beats 90.84 % of java submissions
   * ✔ Your memory usage beats 78.08 % of java submissions (41.2 MB)
   */
  public static String convert(String s, int numRows) {
    if (s == null || s.isEmpty() || s.length() <= numRows || numRows == 1) {
      return s;
    }
    StringBuffer result = new StringBuffer();
    int length = s.length();
    for (int row = 0; row < numRows; row++) {
      int chatIndex = 0;
      while (chatIndex < length + row) {
        // 第一行和最后一行不需要斜边，其他行都需要
        boolean notFirstOrLast = (row > 0) && (row < numRows - 1);
        if (notFirstOrLast && chatIndex - row >= 0 && chatIndex - row < length) {
          result.append(s.charAt(chatIndex - row));
        }
        if (chatIndex + row < length) {
          result.append(s.charAt(chatIndex + row));
        }
        chatIndex += (2 * numRows - 2);
      }
    }
    return result.toString();
  }
}
