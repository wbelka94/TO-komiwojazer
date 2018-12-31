import algorithms.Algorithm;
import algorithms.GreedyCycle;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import models.Point;
import utils.TSPFileParser;

import java.util.ArrayList;
import java.util.List;

public class MainFX extends Application {
    Controller con = new Controller();
    List<Circle> circles = con.addPoint();
    public static Pane root = new Pane();
    static List<Point> orderedCities = new ArrayList<>();


    @Override
    public void start(Stage primaryStage) throws Exception{
        BorderPane mainRoot = new BorderPane();
        mainRoot.setPadding(new Insets(14));
        Pane root = new Pane();
        root.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        for(Circle circle:circles){
            Scale scale = new Scale();
            Label label = new Label(Integer.toString(circles.indexOf(circle)));
            final double MAX_FONT_SIZE = 10.0; // define max font size you need
            label.setFont(new Font(MAX_FONT_SIZE));
            scale.setX(0.3);
            scale.setY(0.3);
            scale.setPivotX(80);
            scale.setPivotY(40);
            label.setLayoutX(circle.getCenterX()*0.3 + 40 );
            label.setLayoutY(circle.getCenterY()*0.3 + 20 );
            circle.getTransforms().addAll(scale);
            //label.getTransforms().addAll(scale);
            root.getChildren().add(circle);
            root.getChildren().add(label);
        }
        addCities();
        for(Line line1:joinPoints(orderedCities)){
            root.getChildren().add(line1);
        }
        Label label = new Label();
        label.setAlignment(Pos.CENTER);
        label.setPadding(new Insets(0, 100, 15, 100));
        mainRoot.setCenter(root);
        mainRoot.setBottom(label);
        Scene scene = new Scene(mainRoot, 3700, 3700);
        primaryStage.setTitle("TSP Genetic");
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public static List<Line> joinPoints(List<Point> cities){

        List<Line> lines = new ArrayList<>();
        for(int i=0; i<cities.size(); i++){
            int next = i+1;
            if(next == 10){
                next = 0;
            }
            Line line = new Line();
            Scale scale = new Scale();
            line.setStartX(cities.get(i).x);
            line.setStartY(cities.get(i).y);
            line.setEndX(cities.get(next).x);
            line.setEndY(cities.get(next).y);
            line.setStroke(Color.BLUE);
            line.setStrokeWidth(5);
            line.setVisible(true);
            scale.setX(0.3);
            scale.setY(0.3);
            scale.setPivotX(80);
            scale.setPivotY(40);
            line.getTransforms().addAll(scale);
            lines.add(line);
        }
        return lines;
    }

    public static void addCities(){
        List<Point> points = TSPFileParser.readData("data/kroA10.tsp");

        Algorithm algoritm = new GreedyCycle(points);
        orderedCities = algoritm.arrangePoints();
    }


    public static void main(String[] args) {
        launch(args);


    }
}