package se.liu;

public class Vertex {
    public Vertex(double x, double y, double z, double w) {
        vec= new Vector(x,y,z);
        this.w = w;
    }
    public Vector vec;
    private double w;

    public double getW() {
        return w;
    }

    public void setW(double w) {
        this.w = w;
    }
}