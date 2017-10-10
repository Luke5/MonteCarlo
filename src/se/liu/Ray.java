package se.liu;
import java.util.*;

public class Ray {
    private Vertex start, end;
    private ColorDbl color;

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

    public Ray(Vertex start, Vertex end) {

        this.start = start;
        this.end = end;
    }
}
