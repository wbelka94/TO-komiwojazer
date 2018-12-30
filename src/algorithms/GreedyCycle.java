package algorithms;

import models.Point;
import utils.EuclideanDist;

import java.util.*;

public class GreedyCycle {
    public static Map<Point, Double> distanceLengths = new HashMap<>();

    public static Point nextPoint(List<Point> points, Point startingPoint) {
        points.remove(50);
        for (Point point:points) {
            distanceLengths.put(point, EuclideanDist.calc(startingPoint, point));
        }

//        System.out.println(points.get(50).x);

        Map.Entry<Point, Double> min = null;
        for (Map.Entry<Point, Double> entry : distanceLengths.entrySet()) {
            if (min == null || min.getValue() > entry.getValue()) {
                min = entry;
            }
        }
        System.out.println(min.getValue());
        return min.getKey();
    }
}
