package algorithms;

import models.Point;
import utils.EuclideanDist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LP extends Algorithm {
    private List<Point> currentPath;
    private double currentTotalDistance;

    public LP(List<Point> path) {
        super(path);
        this.currentPath = path;
        this.currentTotalDistance = EuclideanDist.calcForPath(currentPath);
        System.out.println(this.currentTotalDistance);
    }

    @Override
    public List<Point> arrangePoints() {
        boolean next;
        do {
            next = false;
            List<List<Point>> newPaths = this.twoOpt();
            for (List<Point> path : newPaths) {
                double newDistance = EuclideanDist.calcForPath(path);
                if (newDistance < this.currentTotalDistance) {
                    this.currentTotalDistance = newDistance;
                    System.out.println(this.currentTotalDistance);
                    this.currentPath = path;
                    next = true;
                }
            }
        } while (next);

        return this.currentPath;
    }

    protected List<List<Point>> twoOpt() {
        List<List<Point>> N = new ArrayList<>();
        int pathLength = currentPath.size();
        for (int i = 0; i < pathLength - 1; i++) {
            for (int j = i + 1; j < pathLength - 1; j++) {
                List<Point> l1 = new ArrayList<>(currentPath.subList(0, i));
                List<Point> l2 = new ArrayList<>(currentPath.subList(i, j));
                Collections.reverse(l2);
                List<Point> l3 = new ArrayList<>(currentPath.subList(j, pathLength));
                List<Point> newPath = new ArrayList<>();
                newPath.addAll(l1);
                newPath.addAll(l2);
                newPath.addAll(l3);
                N.add(newPath);
            }
        }
        return N;
    }
}
