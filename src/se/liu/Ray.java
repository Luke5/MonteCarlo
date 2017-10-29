package se.liu;

import java.util.ArrayList;

public class Ray {
    private Vector start, end;
    private Photon firstPhoton;
    private ArrayList<Photon> photons = new ArrayList<>();
    private ColorDbl color;

    Ray(Vector start, Vector end) {
        this.start = start;
        this.end = end;
        this.color = new ColorDbl("default");
    }

    public Photon getFirstPhoton() {
        return firstPhoton;
    }

    void setFirstIntersection(int i, double t, Vector normal) {
        firstPhoton = new Photon(this, i, t, normal);
    }

    ArrayList<Photon> getPhotons() {
        return photons;
    }

    Vector getPoint(double t) {
        return start.add(end.sub(start).unitVector().scalarMult(t));
    }

    double getLength() {
        return end.sub(start).length();
    }

    void addIntersection(int i, double t, Vector normal) {
        this.photons.add(new Photon(this, i, t, normal));
    }

    ColorDbl getColor() {
        return color;
    }

    void setColor(ColorDbl color) {
        this.color = color;
    }

    Vector getDirectionVector() {
        return end.sub(start);
    }

    Vector getStart() {
        return start;
    }

    public Vector getEnd() {
        return end;
    }

    public void setEnd(Vertex end) {
        this.end = end;
    }
}
