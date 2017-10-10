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

    public void traceRay(Ray arg){
        for(Triangle triangle:triangles){
            double  = triangle.rayIntersection(arg);
        }
    }
}
