import algorithms.Algorithm;
import algorithms.GreedyCycle;
import javafx.application.Application;
import javafx.stage.Stage;
import models.Point;
import utils.TSPFileParser;
import utils.TSPVisualisation;

import java.util.List;

public class MainFX extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        List<Point> points = TSPFileParser.readData("data/kroA10.tsp");

        Algorithm algoritm = new GreedyCycle(points);
        List<Point> orderedCities = algoritm.arrangePoints();
        points = TSPFileParser.readData("data/kroA10.tsp");
        TSPVisualisation.show(points, orderedCities);

        List<Point> points2 = TSPFileParser.readData("data/kroA100.tsp");

        Algorithm algoritm2 = new GreedyCycle(points2);
        List<Point> orderedCities2 = algoritm2.arrangePoints();
        points2 = TSPFileParser.readData("data/kroA100.tsp");
        TSPVisualisation.show(points2, orderedCities2);
    }


    public static void main(String[] args) throws Exception {
        launch(args);
    }
}