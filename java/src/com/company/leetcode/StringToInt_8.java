package com.company.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Eric Cheng
 */
public class StringToInt_8 {

  public static void main(String[] args) {
    System.out.println(myAtoi("-     2"));
    System.out.println(myAtoi("4193 with words"));
    System.out.println(myAtoi("   -42"));
    System.out.println(myAtoi("42"));
  }

  /**
   * ✔ Accepted
   * ✔ 1079/1079 cases passed (8 ms)
   * ✔ Your runtime beats 60.34 % of java submissions
   * ✔ Your memory usage beats 83.89 % of java submissions (36.3 MB)
   *
   * 本来最后一步用的是 Long.min(Long.max(a,b),c), 改成
   * ```
   * if (result > Integer.MAX_VALUE) {
   *     return isNegative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
   * }
   * ```
   * 之后runtime时间，从 "beats 30.34 % of java submissions" 上升到 "beats 60.34 % of java submissions"
   *
   * @param str
   * @return
   */
  public static int myAtoi(String str) {
    List<Character> formattedStr = new ArrayList<>();
    Boolean isNegative = false;
    boolean isNegativeAlreadySet = false;
    for (Character ch : str.toCharArray()) {
      if (ch == ' ') {
        if (formattedStr.size() == 0 && !isNegativeAlreadySet) {
          continue;
        } else {
          break;
        }
      }
      if (!isNegativeAlreadySet && formattedStr.size() == 0 && (ch == '+' || ch == '-')) {
        isNegative = ch == '-';
        isNegativeAlreadySet = true;
        continue;
      } else if (!Character.isDigit(ch)) {
        break;
      }
      formattedStr.add(ch);
    }
    int size = formattedStr.size();
    if (size == 0) {
      return 0;
    }
    long result = 0;
    for (int i = 0; i < size; i++) {
      Character ch = formattedStr.get(i);
      result += Character.getNumericValue(ch) * Math.pow(10, size - i - 1);
    }
    if (result > Integer.MAX_VALUE) {
      return isNegative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
    }
    Long finalResult = isNegative ? 0 - result : result;
    return finalResult.intValue();
  }
}
