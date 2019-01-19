import experimnents.LocalSearchExperiment;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainFX extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
//        (new Mean(NearestNeighbor.class.getName(), GreedyCycle.class.getName(), 10)).run();
//        (new Mean(GreedyCycle.class.getName(), NearestNeighbor.class.getName(), 10)).run();
//        (new Mean(LocalSearch.class.getName(), GreedyCycle.class.getName(), 10)).run();
//        (new Mean(IteratedLocalSearch.class.getName(), GreedyCycle.class.getName(), 10)).run();
//        (new LocalSearchExperiment("data/kroA150.tsp")).run();
        (new LocalSearchExperiment("data/kroA100.tsp")).run();
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }
}