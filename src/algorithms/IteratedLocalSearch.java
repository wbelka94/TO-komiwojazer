package algorithms;

import models.Point;
import utils.EuclideanDist;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IteratedLocalSearch extends LocalSearch {
    private List<Point> currentPath;
    private double currentTotalDistance;

    public IteratedLocalSearch(List<Point> path) {
        super(path);
        this.currentPath = path;
        this.currentTotalDistance = EuclideanDist.calcForPath(currentPath);
    }

    @Override
    public List<Point> arrangePoints() {
        boolean next;
        List<Point> copyOfCurrentPath = new ArrayList<>(this.currentPath);
        super.arrangePoints();
        int i = 0;
        do {
            next = false;
            this.currentPath = new ArrayList<>(perturbation(currentPath));
            super.arrangePoints();
            double oldDistance = EuclideanDist.calcForPath(copyOfCurrentPath);
            double newDistance = EuclideanDist.calcForPath(currentPath);
            if (oldDistance < newDistance) {
                this.currentTotalDistance = newDistance;
                this.currentPath = new ArrayList<>(copyOfCurrentPath);
                copyOfCurrentPath = new ArrayList<>(this.currentPath);
                i++;
            }
            else{
                i = 0;
            }
            if(i < 3){
                next = true;
            }
        } while (next);

        return this.currentPath;
    }

    private List<Point> perturbation(List<Point> path){
        Random r = new Random();
        int pathLength = path.size();
        List<Point> newPath = new ArrayList<>(path);
       // for(int i=0; i<= r.nextInt(2); i++){
            path = new ArrayList<>(newPath);
            newPath = new ArrayList<>();
            int r1 = r.nextInt(pathLength - 1);
            int r2 = r.nextInt(pathLength - r1 - 1) + r1 + 1;
            List<Point> l1 = new ArrayList<>(path.subList(0, r1));
            List<Point> l2 = new ArrayList<>(path.subList(r1, r2));
            Algorithm alg = new GreedyCycle(l2);
            l2 = alg.arrangePoints();
            List<Point> l3 = new ArrayList<>(path.subList(r2, pathLength));
            newPath.addAll(l1);
            newPath.addAll(l2);
            newPath.addAll(l3);
       // }
        return newPath;
    }


}
