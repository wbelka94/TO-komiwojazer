package utils;

import models.Point;

import java.util.List;

public class EuclideanDist {
    public static double calc(Point a, Point b) {
        return Math.sqrt(Math.pow((a.x - b.x), 2) + Math.pow((a.y - b.y), 2));
    }

    public static double calcForPath(List<Point> path) {
        double distance = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            distance += calc(path.get(i), path.get(i + 1));
        }
        distance += calc(path.get(path.size() - 1), path.get(0));
        return distance;
    }
}
