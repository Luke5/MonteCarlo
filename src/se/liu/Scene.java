package se.liu;

import java.util.ArrayList;

public class Scene {
    private ArrayList<Triangle> triangles = new ArrayList<Triangle>();

    public Scene(ArrayList<Triangle> triangles) {
        this.triangles = triangles;
        System.out.println("Created new Scene with " + triangles.size() + " Objects");
    }

    Scene() {
        System.out.println("Created new empty Scene");
    }

    public void addTriangle(Triangle triangle) {
        triangles.add(triangle);
    }

    void addTriangle(ColorDbl color, double x1, double y1, double z1, double x2, double y2, double z2, double x3, double y3, double z3) {
        Triangle triangle = new Triangle(color, x1, y1, z1, x2, y2, z2, x3, y3, z3);
        triangles.add(triangle);
        System.out.println("Added new Triangle at P1( " + x1 + " ; " + y1 + " ; " + z1 + " ) P2( " + x2 + " ; " + y2 + " ; " + z2 + " ) P3( " + x3 + " ; " + y3 + " ; " + z3 + " )");
    }

    void addRectangle(ColorDbl color, double x1, double y1, double z1, double x2, double y2, double z2, double x3, double y3, double z3, double x4, double y4, double z4) {
        Triangle t1 = new Triangle(color, x1, y1, z1, x2, y2, z2, x3, y3, z3);
        Triangle t2 = new Triangle(color, x1, y1, z1, x3, y3, z3, x4, y4, z4);
        triangles.add(t1);
        triangles.add(t2);
        System.out.println("Added new Rectangle at P1( " + x1 + " ; " + y1 + " ; " + z1 + " ) P2( " + x2 + " ; " + y2 + " ; " + z2 + " ) P3( " + x3 + " ; " + y3 + " ; " + z3 + " ) P4( " + x4 + " ; " + y4 + " ; " + z4 + " )");
    }

    public Triangle getTriangle(int i) {
        return triangles.get(i);
    }

    void traceRay(Ray arg) {
        double min = -1;
        int i = 0;
        for (Triangle triangle : triangles) {
            double t = triangle.rayIntersection(arg);
            if (t >= 0) {
                arg.addIntersectionTriangle(i, t);
                if (min == -1 || min > t) {
                    min = t;
                    arg.setFirstIntersectionTriangle(i, t);
                    arg.setColor(triangle.getColor());
                }
            }
            i++;
        }
    }
}
