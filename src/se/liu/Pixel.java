package se.liu;

import java.util.ArrayList;

public class Pixel {

    private ColorDbl color;
    private ArrayList<Ray> rays = new ArrayList<>();
    private Vertex position;

    Pixel(Vertex position) {
        this.position = position;
    }

    public Ray getRay(int i) {
        return rays.get(i);
    }

    void addRay(Ray ray) {
        rays.add(ray);
    }

    ColorDbl getColor() {
        return color;
    }

    public void clearRays(){
        rays.clear();
    }

    public void setColor(ColorDbl color) {
        this.color = color;
    }

    void calculateColor() {
        int r = 0, g = 0, b = 0, i = 0;
        for (Ray ray : rays) {
            r += ray.getColor().getR();
            g += ray.getColor().getG();
            b += ray.getColor().getB();
            i++;
        }
        if (i != 0) {
            r /= i;
            g /= i;
            b /= i;
        }
        this.color = new ColorDbl(r, g, b);
    }

    Vertex getPosition() {
        return position;
    }
}
