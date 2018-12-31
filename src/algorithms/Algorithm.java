package algorithms;

import models.Point;

import java.util.List;

public abstract class Algorithm {
    protected List<Point> points;

    public Algorithm(List<Point> points){
        this.points = points;
    }

    public abstract List<Point> arrangePoints();
}
