package utils;

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

import java.util.ArrayList;
import java.util.List;

public class TSPVisualisation {


    private static List<Circle> addPoints(List<Point> points){
        List<Circle> circles = new ArrayList<>();
//        cities = TSPFileParser.readData("data/kroA10.tsp");
        boolean first = true;
        for(Point  p:points){
//            Label label = new Label(Integer.toString(cities.indexOf(Point)));
            Circle circle = new Circle();
            circle.setCenterX(p.x);
            circle.setCenterY(p.y);
            circle.setRadius(15);
            if(first){
                circle.setFill(Color.RED);
                first = false;
            }
            else {
                circle.setFill(Color.GREEN);
            }
            //circle.radiusProperty().bind(label.widthProperty());
            circles.add(circle);

        }
        return circles;
    }

    private static List<Line> joinPoints(List<Point> path){

        List<Line> lines = new ArrayList<>();
        for(int i=0; i<path.size(); i++){
            int next = i+1;
            if(next == path.size()){
                next = 0;
            }
            Line line = new Line();
            Scale scale = new Scale();
            line.setStartX(path.get(i).x);
            line.setStartY(path.get(i).y);
            line.setEndX(path.get(next).x);
            line.setEndY(path.get(next).y);
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

    public static void show(List<Point> points, List<Point> path, String title) throws Exception{
        Stage primaryStage = new Stage();
        primaryStage.setWidth(1400);
        primaryStage.setHeight(800);
        primaryStage.setTitle(title);
        BorderPane mainRoot = new BorderPane();
        mainRoot.setPadding(new Insets(14));
        Pane root = new Pane();
        root.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        List<Circle> circles = addPoints(points);
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
        for(Line line1:joinPoints(path)){
            root.getChildren().add(line1);
        }
        Label label = new Label();
        label.setAlignment(Pos.CENTER);
        label.setPadding(new Insets(0, 100, 15, 100));
        mainRoot.setCenter(root);
        mainRoot.setBottom(label);
        Scene scene = new Scene(mainRoot, 3700, 3700);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

}