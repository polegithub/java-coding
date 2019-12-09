package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    // write your code here
    List<Integer> l1 = new ArrayList<>();
    List<Integer> l2 = new ArrayList<>();
    List<Integer> l3 = new ArrayList<>();
    List<Integer> l4 = new ArrayList<>();
    List<Integer> l5 = Arrays
        .asList(2, 3, 4);
    System.out.println(l5);

    List<Integer> sorted = Arrays.asList(4, 6, 8, 7);
    List<Integer> droped = new ArrayList<>();
    droped.addAll(sorted.subList(3, sorted.size()));
    droped.addAll(sorted.subList(0, 3));
    sorted = droped;
    System.out.println(sorted);
  }
}
