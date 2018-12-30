import algorithms.GreedyCycle;
import models.Point;
import utils.TSPFileParser;
import utils.EuclideanDist;

import java.util.List;

public class Main {
    public static void main(String [ ] args) {
        List<Point> points = TSPFileParser.readData("data/kroA100.tsp");
        GreedyCycle.arrangePoints(50);
//        for (Point point:GreedyCycle.arrangePoints(points.get(50))) {
//            System.out.println(point.x + " " + point.y);
//        }
    }
}
