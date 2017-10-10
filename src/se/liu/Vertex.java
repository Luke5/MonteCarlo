package se.liu;

public class Vertex extends Vector{
    private double w;

    public Vertex(double x, double y, double z, double w) {
        super(x, y, z);
        this.w = w;
    }
    public Vertex(double x, double y, double z) {
        super(x, y, z);
        this.w = 1;
    }
}