package se.liu;

public class Vertex extends Vector {
    private Direction w;

    Vertex(double x, double y, double z) {
        super(x, y, z);
        this.w = new Direction(this);
    }
}