package algorithms;

import models.Point;
import utils.EuclideanDist;
import utils.TSPFileParser;

import java.util.*;

public class GreedyCycle_D {
    public static List<Point> loadedPoints;
    public static List<Point> arrangedPoints = new LinkedList<>();

    public static void loadPoints() {
        loadedPoints = TSPFileParser.readData("data/kroA100.tsp");
    }

    public static Point nextPoint(Point startingPoint) {
        Map<Point, Double> distanceLengths = new HashMap<>();
        for (Point point:loadedPoints) {
            distanceLengths.put(point, EuclideanDist.calc(startingPoint, point));
        }
        Map.Entry<Point, Double> min = null;
        for (Map.Entry<Point, Double> entry : distanceLengths.entrySet()) {
            if (min == null || min.getValue() > entry.getValue()) {
                min = entry;
            }
        }
        return min.getKey();
    }

    public static List<Point> arrangePoints(int startingPoint) {
        loadPoints();

        arrangedPoints.add(loadedPoints.get(startingPoint));
//        loadedPoints.remove(startingPoint);
        loadedPoints.remove(loadedPoints.get(startingPoint));



        for (int i = 0; i < 99; i++) {
            Point tempPoint = nextPoint(arrangedPoints.get(arrangedPoints.size()-1));
            System.out.println(tempPoint.x + " " + tempPoint.y);
            arrangedPoints.add(tempPoint);
            loadedPoints.remove(tempPoint);

        }
        return arrangedPoints;
    }
}