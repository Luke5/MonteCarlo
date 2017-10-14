package se.liu;

import java.util.ArrayList;

public class Ray {
    private Vertex start, end;
    private double[] firstIntersectionTriangle = new double[2];
    private ArrayList<double[]> intersectionTriangles = new ArrayList<double[]>();
    private ColorDbl color;

    Ray(Vertex start, Vertex end) {
        this.start = start;
        this.end = end;
        this.color = new ColorDbl("default");
    }

    public double[] getFirstIntersectionTriangle() {
        return firstIntersectionTriangle;
    }

    void setFirstIntersectionTriangle(double i, double t) {
        this.firstIntersectionTriangle[0] = i;
        this.firstIntersectionTriangle[1] = t;
    }

    public ArrayList<double[]> getIntersectionTriangles() {
        return intersectionTriangles;
    }

    void addIntersectionTriangle(double i, double t) {
        double[] array = {i, t};
        this.intersectionTriangles.add(array);
    }

    ColorDbl getColor() {
        return color;
    }

    void setColor(ColorDbl color) {
        this.color = color;
    }

    Vertex getStart() {
        return start;
    }

    public void setStart(Vertex start) {
        this.start = start;
    }

    public Vertex getEnd() {
        return end;
    }

    public void setEnd(Vertex end) {
        this.end = end;
    }
}
