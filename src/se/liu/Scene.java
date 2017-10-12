package se.liu;

import java.util.ArrayList;

public class Scene {
    private ArrayList<Triangle> triangles = new ArrayList<Triangle>();

    public Scene(ArrayList<Triangle> triangles) {
        this.triangles = triangles;
    }

    public void addTriangle(Triangle triangle){
        triangles.add(triangle);
    }

    public Triangle getTriangle(int i){
        return triangles.get(i);
    }

    public void traceRay(Ray arg){
        double min=-1;
        int i=0;
        for(Triangle triangle:triangles){
            double t = triangle.rayIntersection(arg);
            if(t!=-1){
                arg.addIntersectionTriangle(i, t);
                if(min==-1 || min>t){
                    min = t;
                    arg.setFirstIntersectionTriangle(i,t);
                }
            }
            i++;
        }
    }
}
