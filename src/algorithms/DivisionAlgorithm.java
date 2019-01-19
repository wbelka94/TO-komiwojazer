package algorithms;

import models.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class DivisionAlgorithm extends Algorithm{

    public DivisionAlgorithm(List<Point> points) {
        super(points);
    }

    public List<List<Point>> arangeTwoPath(){
        List<Point> arrangedPoints1 = new ArrayList<>();
        List<Point> arrangedPoints2 = new ArrayList<>();
        Random r = new Random();
        int random1 = r.nextInt(points.size());
        int random2 = r.nextInt(points.size());
        while(random1 == random2){
            random2 = r.nextInt(points.size());
        }
        Point startPoint1 = points.get(random1);
        Point startPoint2 = points.get(random2);
        arrangedPoints1.add(startPoint1);
        arrangedPoints2.add(startPoint2);
        points.remove(startPoint1);
        points.remove(startPoint2);

        Point actualPoint1 = startPoint1;
        Point actualPoint2 = startPoint2;
//        int size = points.size();
        while (points.size() >= 2) {
            if(points.size() == 3){
                System.out.println("???????????????");
            }
            actualPoint1 = nextPoint(actualPoint1, startPoint1);
            arrangedPoints1.add(actualPoint1);
            points.remove(actualPoint1);
            actualPoint2 = nextPoint(actualPoint2, startPoint2);
            arrangedPoints2.add(actualPoint2);
            points.remove(actualPoint2);
        }
        List<List<Point>> result = new ArrayList<>();
        result.add(arrangedPoints1);
        result.add(arrangedPoints2);
        return result;
    }

    public abstract Point nextPoint(Point actualPoint, Point startPoint);
}
