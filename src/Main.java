import algorithms.Algorithm;
import algorithms.GreedyCycle;
import algorithms.NearestNeighbor;
import models.Point;
import utils.TSPFileParser;

import java.util.List;

public class Main {
    public static void main(String [ ] args) {
        List<Point> points = TSPFileParser.readData("data/kroA100.tsp");

        Algorithm algoritm = new GreedyCycle(points);
        List<Point> firstPath = algoritm.arrangePoints();

        points = TSPFileParser.readData("data/kroA100.tsp");

        algoritm = new NearestNeighbor(points);
        List<Point> secondPath =algoritm.arrangePoints();

        //MainFX.main(TSPFileParser.readData("data/kroA100.tsp"), firstPath,null);

    }
}
