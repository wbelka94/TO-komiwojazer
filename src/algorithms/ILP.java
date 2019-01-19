package algorithms;

import models.Point;
import utils.EuclideanDist;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ILP extends LP {
    private List<Point> currentPath;
    private double currentTotalDistance;

    public ILP(List<Point> path) {
        super(path);
        this.currentPath = path;
        this.currentTotalDistance = EuclideanDist.calcForPath(currentPath);
    }

    @Override
    public List<Point> arrangePoints() {
        boolean next;
        ArrayList<Point> newPath = new ArrayList<>(super.arrangePoints());
        int i = 0;
        do {
            next = false;
            newPath = new ArrayList<>(perturbation(newPath));
            super.arrangePoints();
            double newDistance = EuclideanDist.calcForPath(newPath);
            if (newDistance < this.currentTotalDistance) {
                this.currentTotalDistance = newDistance;
                System.out.println(newPath);
                this.currentPath = new ArrayList<>(newPath);
                next = true;
                i = 0;
            }
            i++;
            if(i < 10){
                next = true;
            }
        } while (next);

        return this.currentPath;
    }

    private List<Point> perturbation(ArrayList<Point> path){
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
