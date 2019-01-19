package experimnents;

import algorithms.GreedyCycle;
import algorithms.LocalSearch;
import models.Point;
import utils.EuclideanDist;
import utils.TSPFileParser;

import java.util.ArrayList;
import java.util.List;

public class LocalSearchExperiment implements Experiment {
    private final String pointsFilePath;

    public LocalSearchExperiment(String pointsFilePath) {
        this.pointsFilePath = pointsFilePath;
    }

    public void run() {
        List<List<Point>> paths = new ArrayList<>();
        List<Point> points = TSPFileParser.readData(pointsFilePath);
        for (int i = 0; i < 1000; i++) {
            List<List<Point>> twoPath = (new GreedyCycle(new ArrayList<>(points))).arangeTwoPath();
            paths.add(new LocalSearch(new ArrayList<>(twoPath.get(0))).arrangePoints());
            paths.add(new LocalSearch(new ArrayList<>(twoPath.get(1))).arrangePoints());
        }
        List<Integer> result = new ArrayList<>();
        List<Integer> distances = new ArrayList<>();
        for (int i = 0; i < 2000 - 1; i += 2) {
            System.out.println(i + " / " + paths.size());
            List<List<Point>> pathsCopy = new ArrayList<>(paths);
            List<List<Point>> currentSolutionPairs = new ArrayList<>();
            List<List<Point>> otherSolutionsPairs = new ArrayList<>();
            List<Point> tmp = pathsCopy.get(i);
            List<Point> tmp2 = pathsCopy.get(i + 1);
            currentSolutionPairs.addAll(getPairs(tmp));
            currentSolutionPairs.addAll(getPairs(tmp2));
            pathsCopy.remove(tmp);
            pathsCopy.remove(tmp2);
            for (List<Point> path : pathsCopy) {
                otherSolutionsPairs.addAll(getPairs(path));
            }
            int count = 0;
            for (List<Point> csp : currentSolutionPairs) {
                for (List<Point> lp : otherSolutionsPairs) {
                    if (csp.get(0).equals(lp.get(0)) && csp.get(1).equals(lp.get(1))) {
                        count++;
                    }
                }
            }
            result.add(count / 999);
            distances.add((int) (EuclideanDist.calcForPath(tmp) + EuclideanDist.calcForPath(tmp2)));
        }
        System.out.println(result);
        System.out.println(distances);
    }

    public List<List<Point>> getPairs(List<Point> path) {
        List<List<Point>> result = new ArrayList<>();
        for (int i = 0; i < path.size() - 1; i++) {
            List<Point> pair = new ArrayList<>();
            pair.add(path.get(i));
            pair.add(path.get(i + 1));
            result.add(pair);
        }
        return result;
    }
}
