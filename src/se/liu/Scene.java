package se.liu;

import java.util.ArrayList;

public class Scene {
    private ArrayList<Object> objects = new ArrayList<>();
    private ArrayList<Object> lightSources = new ArrayList<>();
    private ArrayList<Photon> photons = new ArrayList<>();
    private Node photonMap;
    private double searchRadius=0.1;
    private double xmin = -3;
    private double xmax = 13;
    private double ymin = -6;
    private double ymax = 6;
    private double zmin = -5;
    private double zmax = 5;

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

    public void addLightSource(Object object) {
        object.setRadiance(1);
        objects.add(object);
        lightSources.add(object);
        System.out.println("Added Light Source to Scene");
    }

    void addTriangle(ColorDbl color, Reflection reflection, double x1, double y1, double z1, double x2, double y2, double z2, double x3, double y3, double z3) {
        Triangle triangle = new Triangle(color, reflection, new Vertex(x1, y1, z1), new Vertex( x2, y2, z2), new Vertex( x3, y3, z3));
        objects.add(triangle);
        System.out.println("Added new Triangle at P1( " + x1 + " ; " + y1 + " ; " + z1 + " ) P2( " + x2 + " ; " + y2 + " ; " + z2 + " ) P3( " + x3 + " ; " + y3 + " ; " + z3 + " )");
    }

    void addRectangle(ColorDbl color, Reflection reflection, double x1, double y1, double z1, double x2, double y2, double z2, double x3, double y3, double z3, double x4, double y4, double z4) {
        Triangle t1 = new Triangle(color, reflection, new Vertex(x1, y1, z1), new Vertex(x2, y2, z2), new Vertex(x3, y3, z3));
        Triangle t2 = new Triangle(color, reflection, new Vertex(x1, y1, z1), new Vertex(x3, y3, z3), new Vertex(x4, y4, z4));
        objects.add(t1);
        objects.add(t2);
        System.out.println("Added new Rectangle at P1( " + x1 + " ; " + y1 + " ; " + z1 + " ) P2( " + x2 + " ; " + y2 + " ; " + z2 + " ) P3( " + x3 + " ; " + y3 + " ; " + z3 + " ) P4( " + x4 + " ; " + y4 + " ; " + z4 + " )");
    }

    void addTetrahedron(ColorDbl color, Reflection reflection, double x1, double y1, double z1, double x2, double y2, double z2, double x3, double y3, double z3, double x4, double y4, double z4) {
        Triangle t1 = new Triangle(color, reflection, new Vertex(x1, y1, z1), new Vertex(x2, y2, z2), new Vertex(x3, y3, z3));
        Triangle t2 = new Triangle(color, reflection, new Vertex(x2, y2, z2), new Vertex(x3, y3, z3), new Vertex(x4, y4, z4));
        Triangle t3 = new Triangle(color, reflection, new Vertex(x1, y1, z1), new Vertex(x3, y3, z3), new Vertex(x4, y4, z4));
        Triangle t4 = new Triangle(color, reflection, new Vertex(x1, y1, z1), new Vertex(x2, y2, z2), new Vertex(x4, y4, z4));
        objects.add(t1);
        objects.add(t2);
        objects.add(t3);
        objects.add(t4);
        System.out.println("Added new Tetrahedron at P1( " + x1 + " ; " + y1 + " ; " + z1 + " ) P2( " + x2 + " ; " + y2 + " ; " + z2 + " ) P3( " + x3 + " ; " + y3 + " ; " + z3 + " ) P4( " + x4 + " ; " + y4 + " ; " + z4 + " )");
    }

    void addSphere(ColorDbl color, Reflection reflection, double x, double y, double z, double radius){
        Sphere s = new Sphere(color, reflection, new Vertex(x,y,z),radius);
        objects.add(s);
        System.out.println("Added new Sphere at P( " + x + " ; " + y + " ; " + z + " ) with Radius "+radius);
    }

    public Object getObject(int i) {
        return objects.get(i);
    }

    void constructPhotonMap(int numberOfPhotons){
        System.out.println("Creating Photon Map:");
        System.out.print("0%");
        int j=0;
        for (Object lightSource:lightSources) {
            for(int n=0;n<numberOfPhotons;n++){
                Ray ray = lightSource.getRandomRay();
                this.traceRay(ray);
                ArrayList<Photon> shadowPhotons = ray.getPhotons();
                Photon photon = ray.getFirstPhoton();
                shadowPhotons.remove(photon);
                photon.setFlux(lightSource.getFlux()/numberOfPhotons);
                photons.add(photon);
                photons.addAll(shadowPhotons);
                System.out.print("\r");
                System.out.print(((j*numberOfPhotons+n)*100.0/(numberOfPhotons*lightSources.size()))+"%");
            }
            j++;
        }
        photonMap= new Node(searchRadius,xmin,xmax,ymin,ymax,zmin,zmax,photons);
        System.out.print("\r");
        System.out.println("100%");
        System.out.println(photons.size()+" Photons saved in Scene");
    }

    void traceRay(Ray arg) {
        double min = -1;
        int i = 0;
        for (Object object : objects) {
            double t = object.rayIntersection(arg);
            if (t >= 0) {
                arg.addIntersection(i, t);
                if (min == -1 || min > t) {
                    min = t;
                    arg.setFirstIntersection(i, t);
                    arg.setColor(object.getColor());
                }
            }
            i++;
        }
    }

/*
    void traceRay(Ray arg) {
        double min = -1;
        int i = 0;
        for (Object object : objects) {
            double t = object.rayIntersection(arg);
            if (t >= 0) {
                arg.addIntersection(i, t);
                if(object.getRadiance()>0){
                    radiositySources.add(new Photon(arg,i,t));
                }
                if (min == -1 || min > t) {
                    min = t;
                    arg.setFirstIntersection(i, t);
                }
            }
            i++;
        }
        ArrayList<Photon> currentRadiositySources= new ArrayList<>();
        currentRadiositySources.addAll(radiositySources);
        for (Photon intersection:arg.getPhotons()) {
            for (Photon lightSource: currentRadiositySources) {
                Ray transfer = new Ray(intersection.getPosition(),lightSource.getPosition());
                min = transfer.getLength();
                for (Object object : objects) {
                    double t = object.rayIntersection(transfer);
                    if (t >= 0) {
                        min=Math.min(min,t);
                    }
                }
                if(min==transfer.getLength()){
                    Object object = this.getObject(intersection.getI());
                    Object light = this.getObject(lightSource.getI());
                    double brdf = object.getReflection().brdf(intersection.getPosition(),new Direction(arg),new Direction(transfer));
                    double r = object.getColor().getR()*light.getColor().getR()*brdf/256;
                    double g = object.getColor().getG()*light.getColor().getG()*brdf/256;
                    double b = object.getColor().getB()*light.getColor().getB()*brdf/256;
                    arg.setColor(new ColorDbl(r,g,b));
                    radiositySources.add(intersection);
                }
            }
        }
    }*/
}
