package se.liu;

import java.util.ArrayList;

public class Ray {
    private Vector start, end;
    private Intersection firstIntersection;
    private ArrayList<Intersection> intersections = new ArrayList<>();
    private ColorDbl color;

    Ray(Vector start, Vector end) {
        this.start = start;
        this.end = end;
        this.color = new ColorDbl("default");
    }

    public Intersection getFirstIntersection() {
        return firstIntersection;
    }

    void setFirstIntersection(int i, double t) {
        firstIntersection=new Intersection(this,i,t);
    }

    ArrayList<Intersection> getIntersections() {
        return intersections;
    }

    Vector getPoint(double t){
        return start.add(end.sub(start).unitVector().scalarMult(t));
    }

    double getLength(){
        return end.sub(start).length();
    }

    void addIntersection(int i, double t) {
        this.intersections.add(new Intersection(this,i,t));
    }

    ColorDbl getColor() {
        return color;
    }

    void setColor(ColorDbl color) {
        this.color = color;
    }

    Vector getStart() {
        return start;
    }

    public void setStart(Vertex start) {
        this.start = start;
    }

    public Vector getEnd() {
        return end;
    }

    public void setEnd(Vertex end) {
        this.end = end;
    }
}
