package com.company.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 * @author Eric Cheng
 */
public class 单词接龙_127 {

  /**
   * 如果不存在这样的转换序列，返回 0。
   * 所有单词具有相同的长度。
   * 所有单词只由小写字母组成。
   * 字典中不存在重复的单词。
   * 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
   */
  public static void main(String[] args) {
    String[] words = { "hot", "dot", "dog", "lot", "log", "cog" };
    //    String[] words = { "hot", "dot", "dog" };
    int a = ladderLength("hit", "cog", Arrays.asList(words));
    System.out.println(a);
    String[] words2 = { "hot", "dot", "dog", "lot", "log" };
    int a2 = ladderLength("hit", "cog", Arrays.asList(words2));
    System.out.println(a2);

    String[] words3 =
        { "si", "go", "se", "cm", "so", "ph", "mt", "db", "mb", "sb", "kr", "ln", "tm", "le", "av",
            "sm", "ar", "ci", "ca", "br", "ti", "ba", "to", "ra", "fa", "yo", "ow", "sn", "ya",
            "cr", "po", "fe", "ho", "ma", "re", "or", "rn", "au", "ur", "rh", "sr", "tc", "lt",
            "lo", "as", "fr", "nb", "yb", "if", "pb", "ge", "th", "pm", "rb", "sh", "co", "ga",
            "li", "ha", "hz", "no", "bi", "di", "hi", "qa", "pi", "os", "uh", "wm", "an", "me",
            "mo", "na", "la", "st", "er", "sc", "ne", "mn", "mi", "am", "ex", "pt", "io", "be",
            "fm", "ta", "tb", "ni", "mr", "pa", "he", "lr", "sq", "ye" };
    int a3 = ladderLength("qa", "sq", Arrays.asList(words3));
    System.out.println(a3);
  }

  public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
    int len = ladderLength2(beginWord, endWord, wordList);
    int len2 = getDepthByTree(beginWord, endWord, wordList);
    return Math.max(len, 0);
  }

  private static int ladderLength2(String beginWord, String endWord, List<String> wordList) {
    HashSet<String> wordSet = new HashSet<>(wordList); //替换掉题目中List结构，加速查找
    if (!wordSet.contains(endWord))
      return 0; //如果目标顶点不在图中，直接返回0
    HashMap<String, Integer> map = new HashMap<>(); //用来存储已访问的节点，并存储其在路径上的位置，相当于BFS算法中的isVisted功能
    Queue<String> q = new LinkedList<>(); //构建队列，实现广度优先遍历
    q.add(beginWord); //加入源顶点
    map.put(beginWord, 1); //添加源顶点为“已访问”，并记录它在路径的位置
    while (!q.isEmpty()) { //开始遍历队列中的顶点
      String word = q.poll(); //记录现在正在处理的顶点
      int level = map.get(word); //记录现在路径的长度
      for (int i = 0; i < word.length(); i++) {
        char[] wordLetter = word.toCharArray();
        for (char j = 'a'; j <= 'z'; j++) {
          if (wordLetter[i] == j)
            continue;
          wordLetter[i] = j; //对于每一位字母，分别替换成另外25个字母
          String check = new String(wordLetter);
          if (check.equals(endWord))
            return map.get(word) + 1; //如果已经抵达目标节点，返回当前路径长度+1
          if (wordSet.contains(check) && !map.containsKey(check)) { //如果字典中存在邻接节点，且这个邻接节点还未被访问
            map.put(check, level + 1); //标记这个邻接节点为已访问，记录其在路径上的位置
            q.add(check); //加入队列，以供广度搜索
          }
        }
      }
    }
    return 0;
  }

  // --------------------------- 构建一棵树 -------------------------------------- //

  /**
   * Accepted
   * 40/40 cases passed (93 ms)
   * Your runtime beats 76.73 % of java submissions
   * Your memory usage beats 57.51 % of java submissions (42.6 MB)
   */
  private static int getDepthByTree(String beginWord, String endWord, List<String> wordList) {
    if (!wordList.contains(endWord)) {
      return 0;
    }
    HashSet<String> wordsSet = new HashSet<>(wordList);
    HashMap<String, Integer> treeDepthMap = new HashMap<>();
    Queue<String> queue = new LinkedList<>();

    // init
    treeDepthMap.put(beginWord, 1);
    queue.offer(beginWord);

    while (!queue.isEmpty()) {
      String currentFirstWord = queue.poll();
      int currentDepth = treeDepthMap.get(currentFirstWord);
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
            return currentDepth + 1;
          }
          if (wordsSet.contains(nearbyWord) && !treeDepthMap.containsKey(nearbyWord)) {
            treeDepthMap.put(nearbyWord, currentDepth + 1);
            queue.add(nearbyWord);
          }
        }
      }
    }
    return 0;
  }
  // --------------------------- 构建一棵树 -------------------------------------- //

  /**
   * 失败方案：
   * 暴力循环去找，缺点是 复杂度和 `wordlist` 的长度呈现正相关。变成n的n次方.
   */
  private static int dfsLadderLength(String beginWord, String endWord, List<String> wordList) {
    if (!wordList.contains(endWord)) {
      return -1;
    }
    if (beginWord.equals(endWord)) {
      return 1;
    }
    int minLen = Integer.MAX_VALUE;
    for (String word : wordList) {
      if (isNearbyWords(word, beginWord)) {
        List<String> wordListCopy = new ArrayList<>(wordList);
        wordListCopy.remove(beginWord);
        int length = dfsLadderLength(word, endWord, wordListCopy);
        if (length >= 0) {
          minLen = Math.min(minLen, length + 1);
        }
      }
    }
    if (minLen < Integer.MAX_VALUE) {
      return minLen;
    } else {
      return -1;
    }
  }

  private static boolean isNearbyWords(String beginWord, String endWord) {
    char[] beginChars = beginWord.toCharArray();
    char[] endChars = endWord.toCharArray();
    int diffCount = 0;
    for (int i = 0; i < beginChars.length; ++i) {
      if (beginChars[i] != endChars[i]) {
        diffCount++;
        if (diffCount > 1) {
          break;
        }
      }
    }
    return diffCount == 1;
  }

  private static HashSet stringToSet(String str) {
    List list = str.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
    return new HashSet<>(list);
  }
}
