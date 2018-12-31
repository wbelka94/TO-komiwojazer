import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import models.Point;
import utils.TSPFileParser;

import java.util.ArrayList;
import java.util.List;

public class Controller {

    public static List<Point> cities = new ArrayList<>();

    public static List<Circle> addPoint(){
        List<Circle> circles = new ArrayList<>();
        cities = TSPFileParser.readData("data/kroA10.tsp");
        boolean first = true;
        for(Point  p:cities){
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

}