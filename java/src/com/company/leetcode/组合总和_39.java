package com.company.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Eric Cheng
 */
public class 组合总和_39 {

  /**
   * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
   * <p>
   * candidates 中的数字可以无限制重复被选取。
   */
  public static void main(String[] args) {
    int[] a = { 2, 3, 5 };
    List<List<Integer>> aR = combinationSum2(a, 8);
    System.out.println(aR);
  }

  // ---------------------------  Solution1: 参照网友的python 写法------------------------------- //

  /**
   * Accepted
   * 168/168 cases passed (49 ms)
   * Your runtime beats 8.08 % of java submissions
   * Your memory usage beats 56.85 % of java submissions (43.2 MB)
   */
  public static List<List<Integer>> combinationSum(int[] candidates, int target) {
    if (candidates.length == 0) {

      return null;
    }
    List<Integer> initCandidates = new ArrayList<>();
    for (int c : candidates) {
      initCandidates.add(c);
    }
    return combinationSum(initCandidates, target);
  }

  private static List<List<Integer>> combinationSum(List<Integer> candidates, int target) {
    if (candidates.size() == 0 || target < 0) {
      return null;
    }
    List<List<Integer>> comResults = new ArrayList<>();
    if (target == 0) {
      comResults.add(new ArrayList<>());
      return comResults;
    }
    for (int c : candidates) {
      List<List<Integer>> subResults =
          combinationSum(filterLessEqualThan(c, candidates), target - c);
      if (subResults == null) {
        continue;
      }
      for (List<Integer> subResult : subResults) {
        List<Integer> finaleResult = new ArrayList<>(subResult);
        finaleResult.add(c);
        comResults.add(finaleResult);
      }
    }
    return comResults;
  }

  private static List<Integer> filterLessEqualThan(int balance, List<Integer> candidates) {
    List<Integer> filtered = new ArrayList<>();
    for (int c : candidates) {
      if (c <= balance) {
        filtered.add(c);
      }
    }
    return filtered;
  }
  // ---------------------------  Solution1: 参照网友的python 写法------------------------------- //

  // --------------------------------  Solution2: 依旧来自网友 ---------------------------------- //

  /**
   * Accepted
   * 168/168 cases passed (6 ms)
   * Your runtime beats 72.3 % of java submissions
   * Your memory usage beats 95.57 % of java submissions (37.8 MB)
   * <p>
   * 典型的深度优先遍历所有可能解的问题,由于没有设置解集的大小且所有数字都可以重复选取,必须采用能够自动挖掘解集的解法
   * 通过递归的方式对所有可能解集进行遍历
   * 使用Arrays.sort(candidates);先对数组进行排序,依然是该类题目提高效率和增加解法的方式
   * 由于采用递归,需要优化递归方法的每一行代码,防止出现无限循环,无效或低效代码等问题
   * <p>
   * <p>
   * 作者：烛火的咆哮
   * 链接：https://www.jianshu.com/p/42211be17acb
   */
  public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
    int len = candidates.length;
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> list = new ArrayList<>();
    if (len == 0)
      return res;
    // 先排个序
    Arrays.sort(candidates);
    combinationSumHelp(res, list, candidates, target, 0);
    return res;
  }

  private static boolean combinationSumHelp(List<List<Integer>> res, List<Integer> list, int[] candidates,
      int target, int start) {
    // 对减去后的target值进行判断,
    if (target < 0)
      return false;
    else if (target == 0) {
      // list对象一直处于变动中,不能直接导入list对象,
      List<Integer> temp = new ArrayList<>(list);
      res.add(temp);
      return false;
    } else {
      for (int i = start; i < candidates.length; i++) {
        list.add(candidates[i]);
        // 深度优先遍历,就像一只深入地底的探测器
        boolean flag = combinationSumHelp(res, list, candidates, target - candidates[i], i);
        list.remove(list.size() - 1);
        // 此路不通时,由于排序后的数组,i之后数字只能更大,跳出循环
        if (!flag)
          break;
      }
    }
    return true;
  }
  // --------------------------------  Solution2: 依旧来自网友 ---------------------------------- //
}
