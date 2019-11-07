package com.company.leetcode;

/**
 * @author Eric Cheng
 */
public class 最长回文子串_5 {

  private String result = "";

  public static void main(String[] args) {
    System.out.println(findFromCenter("abacab"));
    System.out.println(findFromCenter2("abacab"));

  }

  public static String longestPalindrome(String s) {
    // test case要求，默认返回""，最小的case就是单个字符
    return findFromCenter(s);
  }

  //============================= Solution: 从中间往两边推的优化版  ======================================//

  /**
   * ✔ Accepted
   * ✔ 103/103 cases passed (128 ms)
   * ✔ Your runtime beats 29.19 % of java submissions
   * ✔ Your memory usage beats 11.53 % of java submissions (98.8 MB)
   */
  private static String findFromCenter2(String s) {
    if (s == null || s.length() <= 1) {
      return s;
    } else if (s.length() == 2) {
      return s.charAt(0) == s.charAt(s.length() - 1) ? s : firstChar(s);
    }

    String expandStr = "";
    for (char ch : s.toCharArray()) {
      expandStr += "#" + ch;
    }
    int maxIndex = -1;
    int maxLength = 0;
    for (int i = 1; i < expandStr.length(); i++) {
      int currentLeftMax = i - maxLength / 2 - 1;
      int currentRightMax = i + maxLength / 2 + 1;
      if (currentLeftMax < 0 || currentRightMax >= expandStr.length()
          || expandStr.charAt(currentLeftMax) != expandStr
          .charAt(currentRightMax)) {
        continue;
      }
      int left = i - 1;
      int right = i + 1;
      while (left >= 0 && right <= expandStr.length() - 1) {
        char leftChar = expandStr.charAt(left);
        char rightChar = expandStr.charAt(right);
        if (leftChar == rightChar) {
          int currentMaxlength = leftChar == '#' ? right - left - 2 : right - left;
          if (currentMaxlength > maxLength) {
            maxLength = currentMaxlength;
            maxIndex = i;
          }
          left -= 1;
          right += 1;
        } else {
          break;
        }
      }
    }
    if (maxIndex >= 0) {
      return expandStr.substring(maxIndex - maxLength / 2, maxIndex + maxLength / 2 + 1)
          .replace("#", "");
    } else {
      return firstChar(s);
    }
  }
  //============================= Solution: 从中间往两边推的优化版  ======================================//

  //============================= Solution: 从中间往两边推  ======================================//

  /**
   * ✔ Accepted
   * ✔ 103/103 cases passed (158 ms)
   * ✔ Your runtime beats 21.56 % of java submissions
   * ✔ Your memory usage beats 11.78 % of java submissions (96.7 MB)
   */
  private static String findFromCenter(String s) {
    if (s == null || s.length() <= 1) {
      return s;
    } else if (s.length() == 2) {
      return s.charAt(0) == s.charAt(s.length() - 1) ? s : firstChar(s);
    }

    String expandStr = "";
    for (char ch : s.toCharArray()) {
      expandStr += "#" + ch;
    }
    int[] maxPalidromeLengthOfEachChar = new int[expandStr.length()];
    for (int i = 1; i < expandStr.length(); i++) {
      int left = i - 1;
      int right = i + 1;
      while (left >= 0 && right <= expandStr.length() - 1) {
        char leftChar = expandStr.charAt(left);
        char rightChar = expandStr.charAt(right);
        if (leftChar == rightChar) {
          maxPalidromeLengthOfEachChar[i] = leftChar == '#' ? right - left - 2 : right - left;
          left -= 1;
          right += 1;
        } else {
          break;
        }
      }
    }
    int maxIndex = -1;
    int maxLength = 0;
    for (int i = 0; i < maxPalidromeLengthOfEachChar.length; i++) {
      if (maxPalidromeLengthOfEachChar[i] > maxLength) {
        maxIndex = i;
        maxLength = maxPalidromeLengthOfEachChar[i];
      }
    }
    if (maxIndex >= 0) {
      return expandStr.substring(maxIndex - maxLength / 2, maxIndex + maxLength / 2 + 1)
          .replace("#", "");
    } else {
      return firstChar(s);
    }
  }

  private static String firstChar(String s) {
    return s.substring(0, 1);
  }
  //============================= Solution: 从中间往两边推  ======================================//

  //============================= Solution: 最慢的循环递归，会超时  ======================================//
  private static String longestPalindromeByForLoop(String s) {
    if (s == null || s.length() <= 1) {
      return s;
    }
    String longestResult = "";
    for (int start = 0; start < s.length(); start++) {
      for (int end = start + 1; end <= s.length(); end++) {
        // subString is [1,2), 左闭右开，所以 end = length
        String subStr = s.substring(start, end);
        if (isPalindrome(subStr) && subStr.length() > longestResult.length()) {
          longestResult = subStr;
        }
      }
    }
    return longestResult;
  }

  private static boolean isPalindrome(String s) {
    if (s == null || s.isEmpty()) {
      return false;
    } else if (s.length() == 1) {
      return true;
    } else if (s.length() == 2) {
      return s.charAt(0) == s.charAt(s.length() - 1);
    }
    int length = s.length();
    int rightStart = length % 2 == 0 ? length / 2 : length / 2 + 1;
    String left = s.substring(0, length / 2);
    String right = s.substring(rightStart, length);
    if (left.length() != right.length()) {
      return false;
    }
    int rightLength = right.length();
    for (int i = 0; i < left.length(); i++) {
      if (left.charAt(i) != right.charAt(rightLength - i - 1)) {
        return false;
      }
    }
    return true;
  }
  //============================= Solution: 最慢的循环递归，会超时  ======================================//

}
