package models;

public class Point {
    public int id;
    public int x;
    public int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }
}
