package com.company.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Eric Cheng
 */
public class 正数反转_7 {

  public static void main(String[] args) {
    System.out.println(reverse(1534236469));
    System.out.println(reverse(74332000));

  }

  public static int reverse(int x) {
    List<Integer> numbers = numbers(x);
    if (numbers.size() <= 1) {
      return x;
    }
    long result = 0;
    for (int i = numbers.size() - 1; i >= 0; i--) {
      result += (numbers.get(i) * Math.pow(10, numbers.size() - i - 1));
    }
    return fixedResult(result);
  }

  private static int fixedResult(Long x) {
    return (x > Integer.MAX_VALUE || x < Integer.MIN_VALUE) ? 0 : x.intValue();
  }

  private static List<Integer> numbers(int x) {
    if (x == 0) {
      return Arrays.asList(1);
    }
    List<Integer> numbers = new ArrayList<>();
    while (x != 0) {
      numbers.add(x % 10);
      x = x / 10;
    }
    return numbers;
  }
}
