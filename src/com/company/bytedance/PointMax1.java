package com.company.bytedance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author Eric Cheng
 */
public class PointMax1 {

  static class Point {

    int x;
    int y;

    public int getX() {
      return x;
    }

    public int getY() {
      return y;
    }

    public Point(int x, int y) {
      this.x = x;
      this.y = y;
    }

    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }
      if (obj instanceof Point) {
        Point objPoint = (Point) obj;
        return this.x == objPoint.x && this.y == objPoint.y;
      }
      return false;
    }

    public int compareTo(Point o) {
      // TODO Auto-generated method stub
      if (this.x == o.x) {
        return this.y - o.y;
      }
      return this.x - o.x;
    }
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    List<Point> points = new ArrayList<>();
    while (in.hasNext()) {
      String line = in.nextLine();
      if (line != null && !line.isEmpty()) {
        String[] pointStr = line.split(" ");
        if (pointStr.length > 1) {
          Point point = new Point(Integer.parseInt(pointStr[0]), Integer.parseInt(pointStr[1]));
          if (points.size() == 0 || isBorderPoint(points, point)) {
            points.add(point);
          }
        }
      }
    }

    points.sort((p1, p2) -> {
      return p1.x == p2.x ? 0 : (p1.x > p2.x) ? 1 : -1;
    });
    for (Point point : points) {
      System.out.println(String.valueOf(point.x) + " " + String.valueOf(point.y));
    }
  }

  public static void insertPointByOrder(List<Point> points, Point targetPoint) {
    //假设points已经是按顺序的
    for (int i = 0; i < points.size(); i++) {
      Point pointI = points.get(i);
      if (i < points.size() - 1 && pointI.x <= targetPoint.x
          && points.get(i + 1).x >= targetPoint.x) {
        points.add(i + 1, targetPoint);
        return;
      } else if (i == points.size() - 1) {
        points.add(targetPoint);
      }
    }
  }

  public static boolean isBorderPoint(List<Point> points, Point targetPoint) {
    List<Point> minPoints = new ArrayList<>();
    for (int i = 0; i < points.size(); i++) {
      Point pointI = points.get(i);
      if (targetPoint.x < pointI.x && targetPoint.y < pointI.y) {
        return false;
      } else if (targetPoint.x > pointI.x && targetPoint.y > pointI.y) {
        minPoints.add(pointI);
      }
    }
    if (minPoints.size() > 0) {
      points.removeAll(minPoints);
    }
    return true;
  }

  public static void findPoint(List<Point> points) {
    List<Point> minPoints = new ArrayList<>();
    for (int i = 0; i < points.size(); i++) {
      Point pointI = points.get(i);
      if (minPoints.contains(pointI)) {
        continue;
      }
      for (int j = i + 1; j < points.size(); j++) {
        Point pointJ = points.get(j);
        if (minPoints.contains(pointJ)) {
          continue;
        }
        Point minPoint = minPoint(pointI, pointJ);
        if (minPoint != null) {
          minPoints.add(minPoint);
        }
      }
    }
    points.removeAll(minPoints);
    points.sort((p1, p2) -> {
      return p1.x == p2.x ? 0 : (p1.x > p2.x) ? 1 : -1;
    });
    for (Point point : points) {
      System.out.println(String.valueOf(point.x) + " " + String.valueOf(point.y));
    }
  }

  public static Point minPoint(Point p1, Point p2) {
    if (p1.x < p2.x && p1.y < p2.y) {
      return p1;
    }
    if (p1.x > p2.x && p1.y > p2.y) {
      return p2;
    }
    return null;
  }

  // --------------------------------- PointMax 3 --------------------------------------//
  public static void pointMax3(String[] args) {
    Scanner scan = new Scanner(System.in);
    int n = scan.nextInt();
    Point[] ps = new Point[n];
    for (int i = 0; i < n; i++) {
      ps[i] = new Point(scan.nextInt(),scan.nextInt());
    }
    Arrays.sort(ps);
    int maxy = ps[n - 1].y;
    ArrayList<Integer> v1 = new ArrayList();
    ArrayList<Integer> v2 = new ArrayList();

    for (int i = n - 1; i >= 0; i--) {
      //x轴上x最大的肯定是最大点
      if (i == n - 1) {
        v1.add(ps[i].x);
        v2.add(ps[i].y);
      }
      //如果当前点的y小于最大的y，那么肯定不是最大点
      else if (ps[i].y < maxy)
        continue;
        //当前点的大于等于最大点，这种也是最大点
      else if (ps[i].y >= maxy) {
        maxy = ps[i].y;
        v1.add(ps[i].x);
        v2.add(ps[i].y);
      }
    }
    for (int i = v1.size() - 1; i >= 0; i--) {
      System.out.println(v1.get(i) + " " + v2.get(i));
    }
    //6 1 2 2 4 4 6 4 3 7 5 9 0
    scan.close();
  }
  // --------------------------------- PointMax 3 --------------------------------------//

}
