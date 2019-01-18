import algorithms.GreedyCycle;
import algorithms.ILP;
import algorithms.LP;
import javafx.application.Application;
import javafx.stage.Stage;
import models.Point;
import utils.EuclideanDist;
import utils.TSPFileParser;
import utils.TSPVisualisation;

import java.util.ArrayList;
import java.util.List;

public class MainFX extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
//        List<Point> points = TSPFileParser.readData("data/kroA10.tsp");
//
//        Algorithm algoritm = new GreedyCycle(points);
//        List<Point> orderedCities = algoritm.arrangePoints();
//        points = TSPFileParser.readData("data/kroA10.tsp");
//        TSPVisualisation.show(points, orderedCities);

        List<Point> points2 = TSPFileParser.readData("data/kroA100.tsp");

        List<List<Point>> twoPath = (new GreedyCycle(points2)).arangeTwoPath();

        List<Point> orderedCitiesLP1 = (new LP(new ArrayList<>(twoPath.get(0))).arrangePoints());
        List<Point> orderedCitiesLP2 = (new LP(new ArrayList<>(twoPath.get(1))).arrangePoints());
        List<Point> orderedCitiesILP1 = (new ILP(new ArrayList<>(twoPath.get(0))).arrangePoints());
        List<Point> orderedCitiesILP2 = (new ILP(new ArrayList<>(twoPath.get(1))).arrangePoints());

        points2 = TSPFileParser.readData("data/kroA100.tsp");
        TSPVisualisation.show(points2, orderedCitiesLP1, "LP_1");
        TSPVisualisation.show(points2, orderedCitiesLP2, "LP_2");
        TSPVisualisation.show(points2, orderedCitiesILP1, "ILP_1");
        TSPVisualisation.show(points2, orderedCitiesILP2, "ILP_2");
        System.out.println(EuclideanDist.calcForPath(orderedCitiesLP1));
        System.out.println(EuclideanDist.calcForPath(orderedCitiesLP2));
        System.out.println(EuclideanDist.calcForPath(orderedCitiesILP1));
        System.out.println(EuclideanDist.calcForPath(orderedCitiesILP2));
    }


    public static void main(String[] args) throws Exception {
        launch(args);
    }
}