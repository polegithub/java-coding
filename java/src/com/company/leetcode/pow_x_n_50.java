package com.company.leetcode;

import java.util.Random;

/**
 * @author Eric Cheng
 */
public class pow_x_n_50 {

  public static void main(String[] args) {

    //    double a = myPow(2, 10);
    double a = myPow(2, 10);
    double a2 = myPow(0.000001, 2147483646);
    //    double a = myPow(-1.00000, -2147483648);

    Random ran = new Random();

    //    for (int i = 0; i < 1; i++) {
    //      //      double base = ran.nextDouble();
    //      //      int pow = ran.nextInt();
    //      double base = -1.00000;
    //      int pow = -2147483648;
    //      System.out.println("sys: " + Math.pow(base, pow));
    //      System.out.println("my: " + myPow(base, pow));
    //    }
  }

  /**
   * 二分法来递归 (使用原生的Math的算法，也大概这水平)
   * Accepted
   * 304/304 cases passed (1 ms)
   * Your runtime beats 99.92 % of java submissions
   * Your memory usage beats 73.09 % of java submissions (33.8 MB)
   */
  public static double myPow(double x, int n) {
    if (Double.compare(x, 0) == 0 || Double.compare(x, 1) == 0) {
      return x;
    }
    if (n == 0) {
      return 1;
    }
    if (Double.compare(x, -1) == 0) {
      return n % 2 == 0 ? 1 : -1;
    }
    if (n >= Integer.MAX_VALUE || n <= Integer.MIN_VALUE) {
      return 0;
    }
    if (n > 0) {
      double value = myPow(x, n / 2);
      if (Double.compare(value, 0) == 0) {
        return value;
      }
      if (n % 2 == 0) {
        return value * value;
      } else {
        return value * value * x;
      }
    } else {
      return 1 / myPow(x, -n);
    }
  }

  /**
   * 传统的计算方法，效率最低
   */
  public static double myPow2(double x, int n) {
    if (Double.compare(x, 0) == 0 || Double.compare(x, 1) == 0) {
      return x;
    }
    if (n == 0) {
      return 1;
    }
    if (Double.compare(x, -1) == 0) {
      return n % 2 == 0 ? 1 : -1;
    }
    if (n >= Integer.MAX_VALUE || n <= Integer.MIN_VALUE) {
      return 0;
    }
    if (n > 0) {
      double pow = x;
      for (int i = 1; i < n; i++) {
        pow *= x;
        if (Double.compare(pow, 0) == 0) {
          return pow;
        }
      }
      return pow;
    } else {
      return 1 / myPow(x, -n);
    }
  }
}