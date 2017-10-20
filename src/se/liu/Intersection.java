package se.liu;

public class Intersection {
    private int i;
    private double t;
    private Vector position;

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public double getT() {
        return t;
    }

    public void setT(double t) {
        this.t = t;
    }

    public Vector getPosition() {
        return position;
    }

    public void setPosition(Vector position) {
        this.position = position;
    }

    public Intersection(Ray ray, int i, double t) {
        position = ray.getPoint(t);
        this.i = i;
        this.t = t;
    }
}
