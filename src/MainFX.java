import algorithms.GreedyCycle;
import algorithms.LP;
import algorithms.NearestNeighbor;
import experimnents.Mean;
import javafx.application.Application;
import javafx.stage.Stage;
import models.Point;
import utils.TSPFileParser;

import java.util.ArrayList;
import java.util.List;

public class MainFX extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        (new Mean(NearestNeighbor.class.getName(), GreedyCycle.class.getName(), 10)).calc();
        (new Mean(GreedyCycle.class.getName(), NearestNeighbor.class.getName(), 10)).calc();
//        Mean.of(LP.class.getName(),10);
//        Mean.of(ILP.class.getName(),10);
//        List<Point> points = TSPFileParser.readData("data/kroA100.tsp");
//
//        List<List<Point>> twoPath = (new GreedyCycle(points)).arangeTwoPath();
//
//        List<Point> orderedCitiesLP1 = (new LP(new ArrayList<>(twoPath.get(0))).arrangePoints());
//        List<Point> orderedCitiesLP2 = (new LP(new ArrayList<>(twoPath.get(1))).arrangePoints());
//        List<Point> orderedCitiesILP1 = (new ILP(new ArrayList<>(twoPath.get(0))).arrangePoints());
//        List<Point> orderedCitiesILP2 = (new ILP(new ArrayList<>(twoPath.get(1))).arrangePoints());
//
//        points = TSPFileParser.readData("data/kroA100.tsp");
//        String title = "LP " + "length: [RED]" + (int)EuclideanDist.calcForPath(orderedCitiesLP1) + "; [GREEN]" + (int)EuclideanDist.calcForPath(orderedCitiesLP2);
//        TSPVisualisation.show(points, orderedCitiesLP1, orderedCitiesLP2, title);
//        title = "LP " + "length: [RED]" + (int)EuclideanDist.calcForPath(orderedCitiesILP1) + "; [GREEN]" + (int)EuclideanDist.calcForPath(orderedCitiesILP2);
//        TSPVisualisation.show(points, orderedCitiesILP1, orderedCitiesILP2,title);
//        experiment();
    }


    public void experiment() {
        List<List<Point>> paths = new ArrayList<>();
        List<Point> points = TSPFileParser.readData("data/kroA150.tsp");
        for (int i = 0; i < 1000; i++) {
            List<List<Point>> twoPath = (new GreedyCycle(new ArrayList<>(points))).arangeTwoPath();
            paths.add(new LP(new ArrayList<>(twoPath.get(0))).arrangePoints());
            paths.add(new LP(new ArrayList<>(twoPath.get(1))).arrangePoints());
        }
        List<Integer> result = new ArrayList<>();
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
            for(List<Point> path:pathsCopy){
                otherSolutionsPairs.addAll(getPairs(path));
            }
            int count = (int) currentSolutionPairs.stream().filter(otherSolutionsPairs::contains).count();
            result.add(count);
        }
        System.out.println(result);
    }

    public List<List<Point>> getPairs(List<Point> path){
        List<List<Point>> result = new ArrayList<>();
        for (int i = 0; i < path.size() - 1; i++) {
            List<Point> pair = new ArrayList<>();
            pair.add(path.get(i));
            pair.add(path.get(i + 1));
            result.add(pair);
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }
}