package utils;

import models.Point;

import java.util.List;

public class PointListHelper {
    public static String toString(List<Point> pointList){
        StringBuilder result= new StringBuilder();
        for(Point p:pointList){
            result.append(p.id);
            if(pointList.indexOf(p) < pointList.size() - 1){
                result.append(" -> ");
            }
        }
        return result.toString();
    }
}
