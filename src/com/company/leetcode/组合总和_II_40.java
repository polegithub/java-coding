package com.company.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Eric Cheng
 */
public class 组合总和_II_40 {

  /**
   * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
   * candidates 中的每个数字在每个组合中只能使用一次。
   * <p>
   * 说明：
   * 所有数字（包括目标数）都是正整数。
   * 解集不能包含重复的组合。
   */
  public static void main(String[] args) {
    int[] a = { 10, 1, 2, 7, 6, 1, 5 };
    List<List<Integer>> aR = combinationSum2(a, 8);
    System.out.println(aR);
  }

  public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
    List<List<Integer>> totalPaths = new ArrayList<>();
    List<Integer> currPath = new ArrayList<>();
    Arrays.sort(candidates);
    combinationSumByDFS(totalPaths, currPath, candidates, target, 0);
    return totalPaths;
  }

  /**
   * 参照39题目的做法，简单修改得到：
   * Accepted
   * 172/172 cases passed (13 ms)
   * Your runtime beats 36.79 % of java submissions
   * Your memory usage beats 89.38 % of java submissions (37.5 MB)
   *
   * 比较慢的原因是如果数组里有重复的值，就可能出现重复的结果，所以加了个 if ( xx.contains(xx) ) 的去重判断，但是太耗时
   *
   * 看了网上答案，`if (i > start && candidates[i] == candidates[i - 1])`
   * 提前判断一下和前一个一样就可以跳过这样就不用判断contains了，减少去重，效率大升
   *
   * 优化后：
   * Accepted
   * 172/172 cases passed (5 ms)
   * Your runtime beats 77.36 % of java submissions
   * Your memory usage beats 93.01 % of java submissions (36.9 MB)
   */
  private static void combinationSumByDFS(List<List<Integer>> totalPaths, List<Integer> currPath,
      int[] candidates, int target, int start) {
    if (target < 0) {
      return;
    } else if (target == 0) {
      List<Integer> staticPath = new ArrayList<>(currPath);
      totalPaths.add(staticPath);
      return;
    } else {
      for (int i = start; i < candidates.length; ++i) {
        if (i > start && candidates[i] == candidates[i - 1]) {
          continue;
        }
        int candidate = candidates[i];
        currPath.add(candidate);
        combinationSumByDFS(totalPaths, currPath, candidates, target - candidate, i + 1);
        currPath.remove(currPath.size() - 1);
      }
    }
    return;
  }

  public static List<List<Integer>> combinationSumVotedMost(int[] candidates, int target) {
    List<List<Integer>> list = new LinkedList<List<Integer>>();
    Arrays.sort(candidates);
    backtrack(list, new ArrayList<Integer>(), candidates, target, 0);
    return list;
  }

  private static void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] cand,
      int remain, int start) {

    if (remain < 0)
      return; /** no solution */
    else if (remain == 0)
      list.add(new ArrayList<>(tempList));
    else {
      for (int i = start; i < cand.length; i++) {
        if (i > start && cand[i] == cand[i - 1])
          continue; /** skip duplicates */
        tempList.add(cand[i]);
        backtrack(list, tempList, cand, remain - cand[i], i + 1);
        tempList.remove(tempList.size() - 1);
      }
    }
  }
}
