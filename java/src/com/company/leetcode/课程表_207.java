package com.company.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author Eric Cheng
 */
public class 课程表_207 {

  public static void main(String[] args) {
    int[][] preA0 = { { 1, 0 } };
    //    int[][] preA0 = { { 0, 2 }, { 2, 1 }, { 1, 2 } };
    int[][] preA1 = { { 1, 2 }, { 0, 1 }, { 2, 3 }, { 3, 1 } };
    int[][] preA2 = {};

    boolean a0 = canFinish(4, preA0);
    boolean a2 = canFinish2(4, preA0);

    System.out.println(a0);
  }

  public static boolean canFinish(int numCourses, int[][] prerequisites) {
    if (prerequisites.length == 0) {
      return true;
    }
    Map<Integer, HashSet<Integer>> treeMap = createTreeMap(prerequisites);
    int[] visitedNodes = new int[numCourses];
    for (int i = 0; i < numCourses; ++i) {
      if (cannotFinishNodeInTree(i, treeMap, visitedNodes)) {
        return false;
      }
    }
    return true;
  }

  private static Map<Integer, HashSet<Integer>> createTreeMap(int[][] prerequisites) {
    Map<Integer, HashSet<Integer>> treeMap = new HashMap<>();
    for (int[] relation : prerequisites) {
      int parent = relation[0];
      int child = relation[1];
      if (!treeMap.containsKey(parent)) {
        treeMap.put(parent, new HashSet<>());
      }
      treeMap.get(parent).add(child);
    }
    return treeMap;
  }

  /**
   * 从一个节点开始遍历，如果出现重复节点，说明出现`死循环课表`，则证明无法选课
   *
   * Accepted
   * 42/42 cases passed (502 ms)
   * Your runtime beats 5.06 % of java submissions
   * Your memory usage beats 22.68 % of java submissions (55.7 MB)
   */
  private static boolean canFinishNodeInTree(int node, Map<Integer, HashSet<Integer>> treeMap,
      HashSet<Integer> visitedNodes) {
    if (treeMap.containsKey(node)) {
      for (int neightbour : treeMap.get(node)) {
        if (visitedNodes.contains(neightbour)) {
          return false;
        }
        visitedNodes.add(neightbour);
        if (!canFinishNodeInTree(neightbour, treeMap, visitedNodes)) {
          return false;
        }
        visitedNodes.remove(neightbour);
      }
    }
    return true;
  }

  /**
   * visited 使用 map的时候：
   * Accepted
   * 42/42 cases passed (14 ms)
   * Your runtime beats 81.7 % of java submissions
   * Your memory usage beats 65.81 % of java submissions (45.2 MB)
   *
   * visited 改成 `int[]` 之后：
   * Accepted
   * 42/42 cases passed (10 ms)
   * Your runtime beats 87.37 % of java submissions
   * Your memory usage beats 80.83 % of java submissions (42.9 MB)
   *
   * 快的原因：
   * 1.使用map的时候，还需要for循环遍历一下map，填充个空list
   * 2.int[] 比map更省空间
   * 3.如果把`treeMap` 改成 ArrayList<ArrayList<Integer>> 之后，能再快5ms
   */
  private static boolean cannotFinishNodeInTree(int curr, Map<Integer, HashSet<Integer>> treeMap,
      int[] visited) {
    //递归结束条件
    if (visited[curr] == 1)//这个节点正在访问
      return true;
    if (visited[curr] == 2)//这个节点被访问过
      return false;

    //业务逻辑处理
    visited[curr] = 1;//表示正在访问
    if (treeMap.containsKey(curr)) {
      for (int id : treeMap.get(curr))
        if (cannotFinishNodeInTree(id, treeMap, visited))
          return true;
    }
    visited[curr] = 2;//表示已经访问
    return false;
  }

  // ---------------------------------- DFS 网友答案 ------------------------------------ //

  /**
   * Accepted
   * 42/42 cases passed (4 ms)
   * Your runtime beats 98.4 % of java submissions
   * Your memory usage beats 85.62 % of java submissions (42.1 MB)
   */
  public static boolean canFinish2(int numCourses, int[][] prerequisites) {
    ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    for (int i = 0; i < numCourses; i++)
      graph.add(new ArrayList<>());
    for (int i = 0; i < prerequisites.length; i++) {
      int course = prerequisites[i][0];
      int pcourse = prerequisites[i][1];
      graph.get(course).add(pcourse);
    }
    int[] visited = new int[numCourses];
    for (int i = 0; i < numCourses; i++)
      if (cannotFinishedByDFS(i, graph, visited))
        return false;
    return true;
  }

  private static boolean cannotFinishedByDFS(int curr, ArrayList<ArrayList<Integer>> graph,
      int[] visited) {
    //递归结束条件
    if (visited[curr] == 1)//这个节点正在访问
      return true;
    if (visited[curr] == 2)//这个节点被访问过
      return false;

    //业务逻辑处理
    visited[curr] = 1;//表示正在访问
    for (int id : graph.get(curr))
      if (cannotFinishedByDFS(id, graph, visited))
        return true;
    visited[curr] = 2;//表示已经访问
    return false;
  }
  // ---------------------------------- DFS 网友答案 ------------------------------------ //
}
