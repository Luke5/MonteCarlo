package se.liu;

public class Vertex extends Vector {
    private Direction w;

    Vertex(double x, double y, double z) {
        super(x, y, z);
    }

    public Direction getDirection() {
        return w;
    }

    public void setDirection(Direction w) {
        this.w = w;
    }
}