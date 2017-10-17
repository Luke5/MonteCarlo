package se.liu;

import java.util.ArrayList;

public class Scene {
    private ArrayList<Object> objects = new ArrayList<>();

    public Scene(ArrayList<Object> objects) {
        this.objects = objects;
        System.out.println("Created new Scene with " + objects.size() + " Objects");
    }

    Scene() {
        System.out.println("Created new empty Scene");
    }

    public void addObject(Object object) {
        objects.add(object);
    }

    void addTriangle(ColorDbl color, double x1, double y1, double z1, double x2, double y2, double z2, double x3, double y3, double z3) {
        Triangle triangle = new Triangle(color, new Vertex(x1, y1, z1), new Vertex( x2, y2, z2), new Vertex( x3, y3, z3));
        objects.add(triangle);
        System.out.println("Added new Triangle at P1( " + x1 + " ; " + y1 + " ; " + z1 + " ) P2( " + x2 + " ; " + y2 + " ; " + z2 + " ) P3( " + x3 + " ; " + y3 + " ; " + z3 + " )");
    }

    void addRectangle(ColorDbl color, double x1, double y1, double z1, double x2, double y2, double z2, double x3, double y3, double z3, double x4, double y4, double z4) {
        Triangle t1 = new Triangle(color, new Vertex(x1, y1, z1), new Vertex(x2, y2, z2), new Vertex(x3, y3, z3));
        Triangle t2 = new Triangle(color, new Vertex(x1, y1, z1), new Vertex(x3, y3, z3), new Vertex(x4, y4, z4));
        objects.add(t1);
        objects.add(t2);
        System.out.println("Added new Rectangle at P1( " + x1 + " ; " + y1 + " ; " + z1 + " ) P2( " + x2 + " ; " + y2 + " ; " + z2 + " ) P3( " + x3 + " ; " + y3 + " ; " + z3 + " ) P4( " + x4 + " ; " + y4 + " ; " + z4 + " )");
    }

    void addTetrahedron(ColorDbl color, double x1, double y1, double z1, double x2, double y2, double z2, double x3, double y3, double z3, double x4, double y4, double z4) {
        Triangle t1 = new Triangle(color, new Vertex(x1, y1, z1), new Vertex(x2, y2, z2), new Vertex(x3, y3, z3));
        Triangle t2 = new Triangle(color, new Vertex(x2, y2, z2), new Vertex(x3, y3, z3), new Vertex(x4, y4, z4));
        Triangle t3 = new Triangle(color, new Vertex(x1, y1, z1), new Vertex(x3, y3, z3), new Vertex(x4, y4, z4));
        Triangle t4 = new Triangle(color, new Vertex(x1, y1, z1), new Vertex(x2, y2, z2), new Vertex(x4, y4, z4));
        objects.add(t1);
        objects.add(t2);
        objects.add(t3);
        objects.add(t4);
        System.out.println("Added new Tetrahedron at P1( " + x1 + " ; " + y1 + " ; " + z1 + " ) P2( " + x2 + " ; " + y2 + " ; " + z2 + " ) P3( " + x3 + " ; " + y3 + " ; " + z3 + " ) P4( " + x4 + " ; " + y4 + " ; " + z4 + " )");
    }

    void addSphere(ColorDbl color, double x, double y, double z, double radius){
        Sphere s = new Sphere(color,new Vertex(x,y,z),radius);
        objects.add(s);
        System.out.println("Added new Sphere at P( " + x + " ; " + y + " ; " + z + " ) with Radius "+radius);
    }

    public Object getObject(int i) {
        return objects.get(i);
    }

    void traceRay(Ray arg) {
        double min = -1;
        int i = 0;
        for (Object object : objects) {
            double t = object.rayIntersection(arg);
            if (t >= 0) {
                arg.addIntersectionTriangle(i, t);
                if (min == -1 || min > t) {
                    min = t;
                    arg.setFirstIntersectionTriangle(i, t);
                    arg.setColor(object.getColor());
                }
            }
            i++;
        }
    }
}
