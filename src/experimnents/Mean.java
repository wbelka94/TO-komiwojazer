package experimnents;

import algorithms.*;
import models.Point;
import utils.EuclideanDist;
import utils.TSPFileParser;
import utils.TSPVisualisation;

import java.util.*;

public class Mean {
    private final String algorithmClass;
    private final String divisionAlgorithmClass;
    private final int iterations;
    private int maxValue;
    private int minValue;
    private Map<Integer, List<Point>> max = new HashMap<>();
    private Map<Integer, List<Point>> min = new HashMap<>();
    public Mean(String algorithmClass, String divisionAlgorithmClass, int iterations){
        this.algorithmClass = algorithmClass;
        this.divisionAlgorithmClass = divisionAlgorithmClass;
        this.iterations = iterations;
    }

    public void calc() throws Exception {
        List<Point> points100 = TSPFileParser.readData("data/kroA100.tsp");
        List<Point> points150 = TSPFileParser.readData("data/kroA150.tsp");
        calcForPoints(points100);
        calcForPoints(points150);
    }

    private void calcForPoints(List<Point> points) throws Exception {
        maxValue = 0;
        minValue = 0;
        int sumDist = 0;
        for (int i = 0; i < iterations; i++) {
            DivisionAlgorithm dAlg = (DivisionAlgorithm) getAlgorithm(divisionAlgorithmClass,points);
            List<List<Point>> twoPath = dAlg.arangeTwoPath();
            Algorithm alg = getAlgorithm(algorithmClass, twoPath.get(0));
            Algorithm alg2 = getAlgorithm(algorithmClass, twoPath.get(1));
            List<Point> r = alg.arrangePoints();
            List<Point> r2 = alg2.arrangePoints();
            int totalDist = (int) EuclideanDist.calcForPath(r);
            int totalDist2 = (int) EuclideanDist.calcForPath(r2);
            if(checkMax(totalDist + totalDist2,3)){
                max.put(0, r);
                max.put(1, r2);
            }
            if(checkMin(totalDist + totalDist2,3)){
                min.put(0, r);
                min.put(1, r2);
            }
            sumDist += totalDist + totalDist2;
        }
        System.out.println(algorithmClass + " " + points.size());
        System.out.println("Max dist: " + (int) (EuclideanDist.calcForPath(max.get(0)) + EuclideanDist.calcForPath(max.get(1))));
        System.out.println("Min dist: " + (int) (EuclideanDist.calcForPath(min.get(0)) + EuclideanDist.calcForPath(min.get(1))));
        System.out.println("Mean dist: " + sumDist / iterations);

        String title = algorithmClass + "[" + points.size() + "]" + " [MIN] " + (int) (EuclideanDist.calcForPath(min.get(0)) + EuclideanDist.calcForPath(min.get(1)));
        TSPVisualisation.show(points, min.get(0), min.get(1), title);
        title = algorithmClass + "[" + points.size() + "]" + " [MAX] " + (int) (EuclideanDist.calcForPath(max.get(0)) + EuclideanDist.calcForPath(max.get(1)));
        TSPVisualisation.show(points, max.get(0), max.get(1), title);
    }

    private boolean checkMax(int distance, int index){
        if (maxValue == 0 || distance > maxValue) {
            maxValue = distance;
            return true;
        }
        return false;
    }

    private boolean checkMin(int distance, int index){
        if (minValue == 0 || distance < minValue) {
            minValue = distance;
            return true;
        }
        return false;
    }

    private Algorithm getAlgorithm(String algorithmClass, List<Point> points) {
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
