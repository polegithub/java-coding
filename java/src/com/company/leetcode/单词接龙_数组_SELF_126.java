package com.company.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Eric Ch
 * eng
 */
public class 单词接龙_数组_SELF_126 extends 单词接龙_数组_126 {

  public static void main(String[] args) {

    //    String[] words0 = { "a", "b", "c" };
    //    //    String[] words = { "hot", "dot", "dog" };
    //    List<List<String>> a0 = findLadders("a", "c", Arrays.asList(words0));
    //    System.out.println(a0);

    String[] words = { "hot", "cog", "dog", "tot", "hog", "hop", "pot", "dot" };
    //    String[] words = { "hot", "dot", "dog" };
    List<List<String>> a = findLadders("hot", "dog", Arrays.asList(words));
    System.out.println(a);

    LadderModel ladderModel1 = createTest1();
    List<List<String>> a1 = findLadders(ladderModel1.beginWord, ladderModel1.endWord,
        Arrays.asList(ladderModel1.words));
    System.out.println(a1);

    LadderModel ladderModel3 = createTest3();
    List<List<String>> a3 = findLadders(ladderModel3.beginWord, ladderModel3.endWord,
        Arrays.asList(ladderModel3.words));
    System.out.println(a3);
  }

  /**
   * Accepted
   * 39/39 cases passed (19 ms)
   * Your runtime beats 96.61 % of java submissions
   * Your memory usage beats 100 % of java submissions (39.2 MB)
   */
  public static List<List<String>> findLadders(String beginWord, String endWord,
      List<String> wordList) {
    if (!wordList.contains(endWord)) {
      return new ArrayList<>();
    }
    Map<String, List<String>> neighbourNodeMap =
        getNeighbourNodeMap(beginWord, endWord, new HashSet<>(wordList));
    List<List<String>> ladders = new ArrayList<>();
    List<String> currentLadder = new ArrayList<>();
    updateShortestLaddersByDFS(ladders, beginWord, endWord, neighbourNodeMap, currentLadder);

    Map<String, List<String>> neighbourNodeMap2 =
        getNeighbourNodeMap2(beginWord, endWord, new HashSet<>(wordList));
    List<List<String>> ladders2 = new ArrayList<>();
    List<String> currentLadder2 = new ArrayList<>();
    updateShortestLaddersByDFS(ladders, beginWord, endWord, neighbourNodeMap2, currentLadder2);
    System.out.println(ladders);
    System.out.println(ladders2);

    return ladders;
  }

  private static void updateShortestLaddersByDFS(List<List<String>> ladders, String beginWord,
      String endWord, Map<String, List<String>> neighbourNodeMap, List<String> currentLadder) {
    // currentLadder must not null
    currentLadder.add(beginWord);
    if (beginWord.equals(endWord)) {
      ladders.add(new ArrayList<>(currentLadder));
    } else if (neighbourNodeMap.containsKey(beginWord)) {
      for (String neighbour : neighbourNodeMap.get(beginWord)) {
        updateShortestLaddersByDFS(ladders, neighbour, endWord, neighbourNodeMap, currentLadder);
      }
    }
    currentLadder.remove(currentLadder.size() - 1);
  }

  /**
   * Accepted
   * 39/39 cases passed (19 ms)
   * Your runtime beats 96.61 % of java submissions
   * Your memory usage beats 100 % of java submissions (39.2 MB)
   */
  private static Map<String, List<String>> getNeighbourNodeMap(String beginWord, String endWord,
      Set<String> wordsSet) {
    Map<String, List<String>> nodeMap = new HashMap<>();
    HashSet<String> visitedParent = new HashSet<>();
    boolean isFound = false;
    boolean isReversed = false;

    HashSet<String> startNodes = new HashSet<>();
    startNodes.add(beginWord);
    HashSet<String> endNodes = new HashSet<>();
    endNodes.add(endWord);
    while (!startNodes.isEmpty() && !isFound) {
      // 反转
      if (endNodes.size() < startNodes.size()) {
        HashSet<String> tmp = startNodes;
        startNodes = endNodes;
        endNodes = tmp;
        isReversed = !isReversed;
      }
      HashSet<String> nextNodes = new HashSet<>();
      for (String currentNode : startNodes) {
        visitedParent.add(currentNode);
        List<String> neighbours = getNeighbourNodes(currentNode, wordsSet);
        for (String neighbour : neighbours) {
          if (visitedParent.contains(neighbour) || startNodes.contains(neighbour)) {
            continue;
          }
          isFound = endNodes.contains(neighbour) || isFound;
          nextNodes.add(neighbour);
          updateNodeMap(nodeMap, currentNode, neighbour, isReversed);
        }
      }
      startNodes = nextNodes;
    }
    return nodeMap;
  }

  private static void updateNodeMap(Map<String, List<String>> nodeMap, String master,
      String neighbour,
      Boolean isReversed) {
    String parent = isReversed ? neighbour : master;
    String sub = isReversed ? master : neighbour;
    if (!nodeMap.containsKey(parent)) {
      nodeMap.put(parent, new ArrayList<>());
    }
    nodeMap.get(parent).add(sub);
  }

  /**
   * 效率比 `getNeighbourNodeMap` 低一点，是因为这个是单向的，所以存储的map也会略有差别，但最终结果是正确的，这个可以确定
   *
   * Accepted
   * 39/39 cases passed (86 ms)
   * Your runtime beats 73.61 % of java submissions
   * Your memory usage beats 100 % of java submissions (44.4 MB)
   */
  private static Map<String, List<String>> getNeighbourNodeMap2(String beginWord, String endWord,
      Set<String> wordsSet) {
    Map<String, List<String>> neighbourMap = new HashMap<>();
    HashSet<String> visitedParent = new HashSet<>();
    // 当前正在处理的，同一层级的节点
    HashSet<String> startNodes = new HashSet<>();
    startNodes.add(beginWord);
    boolean found = false;
    while (!startNodes.isEmpty() && !found) {
      HashSet<String> nextNodes = new HashSet<>();
      for (String currentNode : startNodes) {
        visitedParent.add(currentNode);
        List<String> neighbours = getNeighbourNodes(currentNode, wordsSet);
        for (String neighbour : neighbours) {
          if (visitedParent.contains(neighbour) || startNodes.contains(neighbour)) {
            continue;
          }
          if (neighbour.equals(endWord)) {
            // find it
            found = true;
          }
          nextNodes.add(neighbour);
          updateNodeMap(neighbourMap, currentNode, neighbour, false);
        }
      }
      startNodes = nextNodes;
    }
    return neighbourMap;
  }

  private static List<String> getNeighbourNodes(String node, Set<String> wordsSet) {
    List<String> neighbourList = new ArrayList<>();
    for (int i = 0; i < node.length(); ++i) {
      char[] chars = node.toCharArray();
      char chOld = chars[i];
      for (char ch = 'a'; ch <= 'z'; ch++) {
        if (chOld == ch) {
          continue;
        }
        chars[i] = ch;
        String nearbyWord = String.valueOf(chars);
        if (wordsSet.contains(nearbyWord)) {
          neighbourList.add(nearbyWord);
        }
      }
      // 这里不恢复，就会导致 原始的 `currentFirstWord` 会被算进去
      chars[i] = chOld;
    }
    return neighbourList;
  }
}
