package utils;

import models.Point;

public class EuclideanDist {
    public static double calc(Point a, Point b) {
        return Math.sqrt(Math.pow((a.x - b.x), 2));
    }
}
