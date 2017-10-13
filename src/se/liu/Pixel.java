package se.liu;

import java.util.ArrayList;

public class Pixel {
    private ColorDbl color;
    private ArrayList<Ray> rays= new ArrayList<Ray>();
    private Vertex position;

    public Pixel(Vertex position) {
        this.position = position;
    }

    public Ray getRay(int i) {
        return rays.get(i);
    }

    public void addRay(Ray ray){
        rays.add(ray);
    }

    public ColorDbl getColor() {
        return color;
    }

    public void calculateColor() {
        int r=0,g=0,b=0,i=0;
        for (Ray ray:rays) {
            r+=ray.getColor().getR();
            g+=ray.getColor().getG();
            b+=ray.getColor().getB();
            i++;
        }
        if(i!=0){
            r/=i;
            g/=i;
            b/=i;
        }
        this.color = new ColorDbl(r,g,b);
    }

    public Vertex getPosition() {
        return position;
    }
}
