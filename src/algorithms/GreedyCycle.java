package algorithms;

import models.Point;
import utils.EuclideanDist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GreedyCycle extends DivisionAlgorithm {
    private Point actualPoint;
    private Point startPoint;

    public GreedyCycle(List<Point> points) {
        super(points);
        actualPoint = points.get(0);
        startPoint = points.get(0);
    }

    public Point nextPoint(Point actualPoint, Point startPoint) {
        Map<Point, Double> distanceLengths = new HashMap<>();
        for (Point point:this.points) {
            double summaryDistance = EuclideanDist.calc(actualPoint, point) + EuclideanDist.calc(startPoint, point);
            distanceLengths.put(point, summaryDistance);
        }
        Map.Entry<Point, Double> min = null;
        for (Map.Entry<Point, Double> entry : distanceLengths.entrySet()) {
            if (min == null || min.getValue() > entry.getValue()) {
                min = entry;
            }
        }
        return min.getKey();
    }

    @Override
    public List<Point> arrangePoints() {
        List<Point> arrangedPoints = new ArrayList<>();
        arrangedPoints.add(startPoint);
        points.remove(startPoint);
        int size = points.size();
        for (int i = 0; i < size; i++) {
            actualPoint = nextPoint(actualPoint, startPoint);
            System.out.println(actualPoint.x + " " + actualPoint.y);
            arrangedPoints.add(actualPoint);
            points.remove(actualPoint);
        }
        return arrangedPoints;
    }
}
