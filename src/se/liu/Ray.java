package se.liu;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Ray {
    private Vertex start, end;
    private double[] firstIntersectionTriangle= new double[2];
    private ArrayList<double[]> intersectionTriangles = new ArrayList<double[]>();
    private ColorDbl color;

    public Ray(Vertex start, Vertex end) {
        this.start = start;
        this.end = end;
        this.color=new ColorDbl("default");
    }

    public double[] getFirstIntersectionTriangle() {
        return firstIntersectionTriangle;
    }

    public void setFirstIntersectionTriangle(double i, double t) {
        this.firstIntersectionTriangle[0] = i;
        this.firstIntersectionTriangle[1] = t;
    }

    public ArrayList<double[]> getIntersectionTriangles() {
        return intersectionTriangles;
    }

    public void addIntersectionTriangle(double i, double t) {
        double[] array = {i, t};
        this.intersectionTriangles.add(array);
    }

    public ColorDbl getColor() {
        return color;
    }

    public void setColor(ColorDbl color) {
        this.color = color;
    }

    public Vertex getStart() {
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
