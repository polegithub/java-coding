package com.company.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * @author Eric Cheng
 */
public class 单词接龙_数组_126 {

  static class LadderModel {

    String[] words;
    String beginWord;
    String endWord;

    public LadderModel(String[] words, String beginWord, String endWord) {
      this.words = words;
      this.beginWord = beginWord;
      this.endWord = endWord;
    }
  }

  public static void main(String[] args) {
    runTest();
  }

  static void runTest() {
    //    String[] words0 = { "ted", "tex", "red", "tax", "tad", "den", "rex", "pee" };
    String[] words0 = { "ted", "tex", "red", "tax", "tad", "rex" };
    List<List<String>> a0 = findLadders0("red", "tax", Arrays.asList(words0));
    System.out.println(a0);

    String[] words = { "hot", "dot", "dog", "lot", "log", "cog" };
    //    String[] words = { "hot", "dot", "dog" };
    List<List<String>> a = findLadders("hit", "cog", Arrays.asList(words));
    System.out.println(a);
    String[] words2 = { "hot", "dot", "dog", "lot", "log" };
    List<List<String>> a2 = findLadders("hit", "cog", Arrays.asList(words2));
    System.out.println(a2);

    LadderModel ladderModel3 = createTest3();
    List<List<String>> a3 = findLadders(ladderModel3.beginWord, ladderModel3.endWord,
        Arrays.asList(ladderModel3.words));
    System.out.println(a3);
  }

  static LadderModel createTest1() {
    String[] words3 = { "ted", "tex", "red", "tax", "tad", "den", "rex", "pee" };
    return new LadderModel(words3, "red", "tax");
  }

  static LadderModel createTest3() {
    String[] words3 =
        { "si", "go", "se", "cm", "so", "ph", "mt", "db", "mb", "sb", "kr", "ln", "tm", "le", "av",
            "sm", "ar", "ci", "ca", "br", "ti", "ba", "to", "ra", "fa", "yo", "ow", "sn", "ya",
            "cr", "po", "fe", "ho", "ma", "re", "or", "rn", "au", "ur", "rh", "sr", "tc", "lt",
            "lo", "as", "fr", "nb", "yb", "if", "pb", "ge", "th", "pm", "rb", "sh", "co", "ga",
            "li", "ha", "hz", "no", "bi", "di", "hi", "qa", "pi", "os", "uh", "wm", "an", "me",
            "mo", "na", "la", "st", "er", "sc", "ne", "mn", "mi", "am", "ex", "pt", "io", "be",
            "fm", "ta", "tb", "ni", "mr", "pa", "he", "lr", "sq", "ye" };
    return new LadderModel(words3, "qa", "sq");
  }

  // ------------------------------ 目前最好的一个答案 ------------------------------------------ //

  /**
   * Accepted
   * 39/39 cases passed (21 ms)
   * Your runtime beats 94.92 % of java submissions
   * Your memory usage beats 100 % of java submissions (38.5 MB)
   */
  public static List<List<String>> findLadders(String beginWord, String endWord,
      List<String> wordList) {
    Set<String> dict = new HashSet<>(wordList);
    List<List<String>> result = new ArrayList<>();
    if (!dict.contains(endWord)) {
      return result;
    }
    Map<String, List<String>> parentChildMap = getChildren(beginWord, endWord, dict);
    List<String> subPath = new ArrayList<>();
    subPath.add(beginWord);
    findLadders(beginWord, endWord, parentChildMap, result, subPath);
    return result;
  }

  private static void findLadders(String beginWord, String endWord,
      Map<String, List<String>> parentChildMap,
      List<List<String>> result, List<String> subPpath) {
    if (beginWord.equals(endWord)) {
      result.add(new ArrayList<>(subPpath));
    }
    if (!parentChildMap.containsKey(beginWord)) {
      return;
    }
    for (String next : parentChildMap.get(beginWord)) {
      subPpath.add(next);
      findLadders(next, endWord, parentChildMap, result, subPpath);
      subPpath.remove(subPpath.size() - 1);
    }
  }

  private static Map<String, List<String>> getChildren(String beginWord, String endWord,
      Set<String> dict) {
    Map<String, List<String>> map = new HashMap<>();
    Set<String> start = new HashSet<>();
    start.add(beginWord);
    Set<String> end = new HashSet<>();
    Set<String> visited = new HashSet<>();
    end.add(endWord);
    boolean found = false;
    boolean isBackward = false;
    while (!start.isEmpty() && !found) {
      if (start.size() > end.size()) {
        Set<String> tem = start;
        start = end;
        end = tem;
        isBackward = !isBackward;
      }
      Set<String> set = new HashSet<>();
      for (String cur : start) {
        visited.add(cur);
        for (String next : getNext(cur, dict)) {
          if (visited.contains(next) || start.contains(next)) {
            continue;
          }
          if (end.contains(next)) {
            found = true;
          }
          set.add(next);
          String parent = isBackward ? next : cur;
          String child = isBackward ? cur : next;
          if (!map.containsKey(parent)) {
            map.put(parent, new ArrayList<>());
          }
          map.get(parent).add(child);

        }
      }
      start = set;
    }
    return map;
  }

  private static List<String> getNext(String cur, Set<String> dict) {
    List<String> res = new ArrayList<>();
    char[] chars = cur.toCharArray();
    for (int i = 0; i < chars.length; i++) {
      char old = chars[i];
      for (char c = 'a'; c <= 'z'; c++) {
        if (c == old) {
          continue;
        }
        chars[i] = c;
        String next = new String(chars);
        if (dict.contains(next)) {
          res.add(next);
        }
      }
      chars[i] = old;
    }
    return res;
  }

  // ------------------------------- bdf + dfs from leetcode ------------------------------ //

  /**
   * Accepted
   * 39/39 cases passed (108 ms)
   * Your runtime beats 73.17 % of java submissions
   * Your memory usage beats 91.89 % of java submissions (47.8 MB)
   */
  public static List<List<String>> findLadders0(String start, String end, List<String> wordList) {
    HashSet<String> dict = new HashSet<>(wordList);
    List<List<String>> res = new ArrayList<>();
    // Neighbors for every node
    HashMap<String, ArrayList<String>> nodeNeighbors = new HashMap<>();
    // Distance of every node from the start node
    HashMap<String, Integer> distance =
        new HashMap<>();
    ArrayList<String> solution = new ArrayList<>();

    dict.add(start);
    bfs(start, end, dict, nodeNeighbors, distance);
    dfs(start, end, nodeNeighbors, distance, solution, res);
    return res;
  }

  // BFS: Trace every node's distance from the start node (level by level).
  private static void bfs(String start, String end, Set<String> dict,
      HashMap<String, ArrayList<String>> nodeNeighbors,
      HashMap<String, Integer> distance) {
    for (String str : dict)
      nodeNeighbors.put(str, new ArrayList<>());

    Queue<String> queue = new LinkedList<>();
    queue.offer(start);
    distance.put(start, 0);

    while (!queue.isEmpty()) {
      int count = queue.size();
      boolean foundEnd = false;
      for (int i = 0; i < count; i++) {
        String cur = queue.poll();
        int curDistance = distance.get(cur);
        ArrayList<String> neighbors = getNeighbors(cur, dict);

        for (String neighbor : neighbors) {
          nodeNeighbors.get(cur).add(neighbor);
          if (!distance.containsKey(neighbor)) {// Check if visited
            distance.put(neighbor, curDistance + 1);
            if (end.equals(neighbor))// Found the shortest path
              foundEnd = true;
            else
              queue.offer(neighbor);
          }
        }
      }

      if (foundEnd)
        break;
    }
  }

  // Find all next level nodes.
  private static ArrayList<String> getNeighbors(String node, Set<String> dict) {
    ArrayList<String> res = new ArrayList<>();
    char chs[] = node.toCharArray();

    for (char ch = 'a'; ch <= 'z'; ch++) {
      for (int i = 0; i < chs.length; i++) {
        if (chs[i] == ch)
          continue;
        char old_ch = chs[i];
        chs[i] = ch;
        if (dict.contains(String.valueOf(chs))) {
          res.add(String.valueOf(chs));
        }
        chs[i] = old_ch;
      }
    }
    return res;
  }

  // cannotFinishedByDFS: output all paths with the shortest distance.
  private static void dfs(String cur, String end,
      HashMap<String, ArrayList<String>> nodeNeighbors,
      HashMap<String, Integer> distance, ArrayList<String> solution, List<List<String>> res) {
    solution.add(cur);
    if (end.equals(cur)) {
      res.add(new ArrayList<>(solution));
    } else {
      for (String next : nodeNeighbors.get(cur)) {
        if (distance.get(next) == distance.get(cur) + 1) {
          dfs(next, end, nodeNeighbors, distance, solution, res);
        }
      }
    }
    solution.remove(solution.size() - 1);
  }
  // ------------------------------- bdf + dfs from leetcode ------------------------------ //

  // -----------------------------  在127的基础上，简单修改，但是有bug，会漏掉 ------------------ //
  public static List<List<String>> findLadders2(String beginWord, String endWord,
      List<String> wordList) {
    if (!wordList.contains(endWord)) {
      return new ArrayList<>();
    }
    List<List<String>> finalLadders = new ArrayList<>();
    HashSet<String> wordsSet = new HashSet<>(wordList);
    HashMap<String, List<List<String>>> treeDepthMap = new HashMap<>();
    Queue<String> queue = new LinkedList<>();

    // init
    List<String> beginSubLadder = new ArrayList<>();
    beginSubLadder.add(beginWord);
    List<List<String>> beginLadder = new ArrayList<>();
    beginLadder.add(beginSubLadder);
    treeDepthMap.put(beginWord, beginLadder);
    queue.offer(beginWord);

    int shortLength = -1;
    while (!queue.isEmpty()) {
      String currentFirstWord = queue.poll();
      List<List<String>> currentDepths = treeDepthMap.get(currentFirstWord);
      for (int i = 0; i < currentFirstWord.length(); ++i) {
        char[] chars = currentFirstWord.toCharArray();
        for (char ch = 'a'; ch <= 'z'; ch++) {
          if (chars[i] == ch) {
            continue;
          }
          chars[i] = ch;
          String nearbyWord = String.valueOf(chars);
          if (nearbyWord.equals(endWord)) {
            // find it
            for (List<String> subLadder : currentDepths) {
              List<String> finalLadder = new ArrayList<>(subLadder);
              finalLadder.add(nearbyWord);
              if (shortLength < 0) {
                shortLength = finalLadder.size();
              } else {
                shortLength = Integer.min(shortLength, finalLadder.size());
              }
              finalLadders.add(finalLadder);
            }
          } else if (wordsSet.contains(nearbyWord) && !treeDepthMap.containsKey(nearbyWord)) {
            boolean alreadyHadShorter =
                shortLength > 0 && (currentDepths.get(0).size() + 1) > shortLength;
            if (alreadyHadShorter) {
              continue;
            }
            List<List<String>> nearbyWordLadder = new ArrayList<>();
            for (List<String> subLadder : currentDepths) {
              List<String> ladder = new ArrayList<>(subLadder);
              ladder.add(nearbyWord);
              nearbyWordLadder.add(ladder);
            }
            treeDepthMap.put(nearbyWord, nearbyWordLadder);
            queue.offer(nearbyWord);
          }
        }
      }
    }
    if (finalLadders.size() > 0) {
      int minSize = minSizeInList(finalLadders);
      finalLadders.removeIf(c -> c.size() > minSize);
    }
    return finalLadders;
  }

  private static int minSizeInList(List<List<String>> lists) {
    int min = Integer.MAX_VALUE;
    for (List<String> list : lists) {
      min = Integer.min(min, list.size());
    }
    return min;
  }
  // -----------------------------  在127的基础上，简单修改，但是有bug，会漏掉 ------------------ //
}
