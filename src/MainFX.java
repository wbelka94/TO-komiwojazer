import algorithms.GreedyCycle;
import algorithms.IteratedLocalSearch;
import algorithms.LocalSearch;
import experimnents.MeanExperiment;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainFX extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
//        (new MeanExperiment(NearestNeighbor.class.getName(), GreedyCycle.class.getName(), 10)).run();
//        (new MeanExperiment(GreedyCycle.class.getName(), GreedyCycle.class.getName(), 10)).run();
        (new MeanExperiment(LocalSearch.class.getName(), GreedyCycle.class.getName(), 10)).run();
        (new MeanExperiment(IteratedLocalSearch.class.getName(), GreedyCycle.class.getName(), 10)).run();
//        (new LocalSearchExperiment("data/kroA150.tsp")).run();
//        (new LocalSearchExperiment("data/kroA100.tsp")).run();
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }
}