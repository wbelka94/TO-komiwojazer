import models.Point;
import utils.TSPFileParser;

import java.util.List;

public class Main {public static void main(String [ ] args){
    List<Point> points = TSPFileParser.readData("data/kroA100.tsp");
    System.out.println(points.get(0).x);
}
}
