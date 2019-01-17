import algorithms.Algorithm;
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

        Algorithm algoritm2 = new GreedyCycle(points2);
        List<Point> arrangedPoints = algoritm2.arrangePoints();
        List<Point> orderedCities2 = (new LP(new ArrayList<>(arrangedPoints)).arrangePoints());
        List<Point> orderedCities3 = (new ILP(new ArrayList<>(arrangedPoints)).arrangePoints());
        points2 = TSPFileParser.readData("data/kroA100.tsp");
        TSPVisualisation.show(points2, arrangedPoints, "GreedyCycle");
        TSPVisualisation.show(points2, orderedCities2, "LP");
        TSPVisualisation.show(points2, orderedCities3, "ILP");
        System.out.println(EuclideanDist.calcForPath(arrangedPoints));
        System.out.println(EuclideanDist.calcForPath(orderedCities2));
        System.out.println(EuclideanDist.calcForPath(orderedCities3));
    }


    public static void main(String[] args) throws Exception {
        launch(args);
    }
}