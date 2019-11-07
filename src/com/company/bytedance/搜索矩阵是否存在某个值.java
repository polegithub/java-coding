package com.company.bytedance;

import com.company.Main;

/**
 * @author Eric Cheng
 */
public class 搜索矩阵是否存在某个值 {
  public static void main(String[] args) {
    // write your code here
    Main main = new Main();
    int[][] intArray = new int[30][2];
    int value = 4;
    for (int i = 0; i < 30; i++) {
      for (int j = 0; j < 2; j++) {
        intArray[i][j] = value;
        System.out.println(value);
        value += 3;
      }
    }

//    boolean find = main.Find(166, intArray);
//    System.out.println(find);
  }

  public boolean searchMatrix(int[][] matrix, int target) {
    if (matrix.length == 0) {
      return false;
    }
    int rowCount = matrix.length;
    int columnCount = matrix[0].length;
    for (int i = 0; i < rowCount; i++) {
      for (int j = 0; j < columnCount; j++) {
        if (matrix[i][j] == target) {
          return true;
        }
      }
    }
    return false;
  }

  public boolean Find(int target, int[][] array) {
    if (array.length == 0) {
      return false;
    }
    int rowCount = array.length;
    int columnCount = array[0].length;
    return exist(target, array, 0, 0, rowCount - 1, columnCount - 1);
  }

  private boolean exist(int target, int[][] array, int startRowIndex, int startColumnIndex,
      int endRowIndex, int endColumnIndex) {
    if (array.length == 0 || array[0].length == 0) {
      return false;
    }
    if (array[startRowIndex][startColumnIndex] > target
        || array[endRowIndex][endColumnIndex] < target) {
      return false;
    }
    int columnCount = array[0].length;
    if (endColumnIndex <= startColumnIndex + 1 && endRowIndex <= startRowIndex + 1) {
      for (int j = 0; j < columnCount; j++) {
        if (array[startRowIndex][j] == target || array[endRowIndex][j] == target) {
          return true;
        }
      }

      for (int i = 0; i < array.length; i++) {
        if (array[i][startColumnIndex] == target || array[i][endColumnIndex] == target) {
          return true;
        }
      }
      return false;
    }
    int middleRow = (endRowIndex + startRowIndex) / 2;
    int middleColumn = (endColumnIndex + startColumnIndex) / 2;

    int middleValue =
        array[middleRow][middleColumn];
    if (target == middleValue) {
      return true;
    } else if (target > middleValue) {
      return exist(target, array, middleRow, middleColumn, endRowIndex, endColumnIndex);
    } else {
      return exist(target, array, startRowIndex, startColumnIndex, middleRow, middleColumn);
    }
  }
}
