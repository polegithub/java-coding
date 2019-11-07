package com.company.bytedance;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Eric Cheng
 */
public class 头条用户喜好为K的统计 {

  static class UserModel {

    int userIndex;
    int userRateValue;
  }

  static class Query {

    int leftUserIndex;
    int rightUserIndex;
    int userRateValue;

    public Query(int leftUserIndex, int rightUserIndex, int userRateValue) {
      this.leftUserIndex = leftUserIndex;
      this.rightUserIndex = rightUserIndex;
      this.userRateValue = userRateValue;
    }
  }

  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    int userCount = scan.nextInt();
    Map<Integer, Integer> userRateValues = new HashMap();
    for (int i = 0; i < userCount; i++) {
      userRateValues.put(i, scan.nextInt());
    }
    int queryCount = scan.nextInt();
    int[] results = new int[queryCount];
    for (int i = 0; i < queryCount; i++) {
      int left = scan.nextInt();
      int right = scan.nextInt();
      int rateValue = scan.nextInt();
      results[i] = result(userRateValues, left - 1, right - 1, rateValue);
    }
    for (int result : results){
      System.out.println(result);
    }
  }

  private static int result(Map<Integer, Integer> userRateValues, int leftUserIndex,
      int rightUserIndex, int userRateValue) {
    int sum = 0;
    for (int i = leftUserIndex; i <= rightUserIndex; i++) {
      sum += userRateValues.get(i) == userRateValue ? 1 : 0;
    }
    return sum;
  }
}

