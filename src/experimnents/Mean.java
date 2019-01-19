package experimnents;

import algorithms.*;
import models.Point;
import utils.EuclideanDist;
import utils.TSPFileParser;
import utils.TSPVisualisation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Mean {
    public static void of(String algorithmClass, int iterations) throws Exception {

        List<Point> points100 = TSPFileParser.readData("data/kroA100.tsp");
        List<Point> points150 = TSPFileParser.readData("data/kroA150.tsp");
        calcForPoints(algorithmClass, points100, iterations);
        calcForPoints(algorithmClass, points150, iterations);
    }

    private static void calcForPoints(String algorithmClass, List<Point> points, int iterations) throws Exception {
        int min = 0;
        int max = 0;
        List<Point> maxPath = new ArrayList<>();
        List<Point> minPath = new ArrayList<>();
        int sumDist = 0;
        for (int i = 0; i < iterations; i++) {
            Algorithm alg = getAlgorithm(algorithmClass, points);
            List<Point> r = alg.arrangePoints();
            int totalDist = (int) EuclideanDist.calcForPath(r);
            if (min == 0 || totalDist < min) {
                min = totalDist;
                maxPath = r;
            }
            if (max == 0 || totalDist > max) {
                max = totalDist;
                minPath = r;
            }
            sumDist += totalDist;
        }
        System.out.println(algorithmClass + " " + points.size());
        System.out.println("Max dist: " + max);
        System.out.println("Min dist: " + min);
        System.out.println("Mean dist: " + sumDist / iterations);

        String title = algorithmClass + "[" + points.size() + "]" + " [MIN] " + min;
        List<Point> empty = new ArrayList<>();
        TSPVisualisation.show(points, minPath, empty, title);
        title = algorithmClass + "[" + points.size() + "]" + " [MAX] " + max;
        TSPVisualisation.show(points, maxPath, empty, title);
    }

    private static Algorithm getAlgorithm(String algorithmClass, List<Point> points) {
        Algorithm alg = null;
        if (Objects.equals(algorithmClass, GreedyCycle.class.getName())) {
            alg = new GreedyCycle(new ArrayList<>(points));
        }
        if (Objects.equals(algorithmClass, NearestNeighbor.class.getName())) {
            alg = new NearestNeighbor(new ArrayList<>(points));
        }
        if (Objects.equals(algorithmClass, LP.class.getName())) {
            alg = new LP(new ArrayList<>(points));
        }
        if (Objects.equals(algorithmClass, ILP.class.getName())) {
            alg = new ILP(new ArrayList<>(points));
        }
        return alg;
    }
}
