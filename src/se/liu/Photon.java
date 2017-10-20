package se.liu;

public class Photon {
    private int i;
    private double t;
    private Vector position;
    private Direction direction;
    private double flux = -1;

    public double getFlux() {
        return flux;
    }

    public void setFlux(double flux) {
        this.flux = flux;
    }

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

    public Direction getDirection() {
        return direction;
    }

    public Photon(Ray ray, int i, double t) {
        position = ray.getPoint(t);
        this.direction=new Direction(ray);

        this.i = i;
        this.t = t;
    }
}
