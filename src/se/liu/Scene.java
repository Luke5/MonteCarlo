package se.liu;

import java.util.ArrayList;

public class Scene {
    private ArrayList<Object> objects = new ArrayList<>();
    private ArrayList<Object> lightSources = new ArrayList<>();
    private ArrayList<Photon> photons = new ArrayList<>();
    private Node photonMap;
    private double searchRadius = 0.025;
    private double probability = 0.7;
    private double filterConstant = 1;
    private double standartDeviation = 0.05;
    private double totalLightArea = 0;
    private double maxIterations = 100;
    private int errors = 0;
    private double xmin = -3.1;
    private double xmax = 13.1;
    private double ymin = -6.1;
    private double ymax = 6.1;
    private double zmin = -5.1;
    private double zmax = 5.1;

    public Scene(ArrayList<Object> objects) {
        this.objects = objects;
        System.out.println("Created new Scene with " + objects.size() + " Objects");
    }

    Scene() {
        System.out.println("Created new empty Scene");
    }

    void addTriangle(double radiance, ColorDbl color, double reflectionCoefficient, double x1, double y1, double z1, double x2, double y2, double z2, double x3, double y3, double z3) {
        Reflection reflection = new OrenNayarDiffuse(standartDeviation, reflectionCoefficient * color.getR() / 255.99, reflectionCoefficient * color.getG() / 255.99, reflectionCoefficient * color.getB() / 255.99);
        Triangle triangle = new Triangle(color, reflection, new Vertex(x1, y1, z1), new Vertex(x2, y2, z2), new Vertex(x3, y3, z3));
        triangle.setRadiance(radiance);
        objects.add(triangle);
        if (radiance > 0) {
            lightSources.add(triangle);
            totalLightArea += triangle.getArea();
        }
        objects.add(triangle);
        System.out.println("Added new Triangle with Radiance: " + radiance + " at P1( " + x1 + " ; " + y1 + " ; " + z1 + " ) P2( " + x2 + " ; " + y2 + " ; " + z2 + " ) P3( " + x3 + " ; " + y3 + " ; " + z3 + " )");
    }

    void addSpecularTriangle(ColorDbl color, double x1, double y1, double z1, double x2, double y2, double z2, double x3, double y3, double z3) {
        Reflection reflection = new Specular(1.5, color.getR() / 255.99, color.getG() / 255.99, color.getB() / 255.99);
        Triangle triangle = new Triangle(color, reflection, new Vertex(x1, y1, z1), new Vertex(x2, y2, z2), new Vertex(x3, y3, z3));
        objects.add(triangle);
        objects.add(triangle);
        System.out.println("Added new Specular Triangle at P1( " + x1 + " ; " + y1 + " ; " + z1 + " ) P2( " + x2 + " ; " + y2 + " ; " + z2 + " ) P3( " + x3 + " ; " + y3 + " ; " + z3 + " )");
    }

    void addRectangle(double radiance, ColorDbl color, double reflectionCoefficient, double x1, double y1, double z1, double x2, double y2, double z2, double x3, double y3, double z3, double x4, double y4, double z4) {
        Reflection reflection = new OrenNayarDiffuse(standartDeviation, reflectionCoefficient * color.getR() / 255.99, reflectionCoefficient * color.getG() / 255.99, reflectionCoefficient * color.getB() / 255.99);
        Triangle t1 = new Triangle(color, reflection, new Vertex(x1, y1, z1), new Vertex(x2, y2, z2), new Vertex(x3, y3, z3));
        Triangle t2 = new Triangle(color, reflection, new Vertex(x1, y1, z1), new Vertex(x3, y3, z3), new Vertex(x4, y4, z4));
        t1.setRadiance(radiance);
        t2.setRadiance(radiance);
        objects.add(t1);
        objects.add(t2);
        if (radiance > 0) {
            lightSources.add(t1);
            totalLightArea += t1.getArea();
            lightSources.add(t2);
            totalLightArea += t2.getArea();
        }
        System.out.println("Added new Rectangle with Radiance: " + radiance + " at P1( " + x1 + " ; " + y1 + " ; " + z1 + " ) P2( " + x2 + " ; " + y2 + " ; " + z2 + " ) P3( " + x3 + " ; " + y3 + " ; " + z3 + " ) P4( " + x4 + " ; " + y4 + " ; " + z4 + " )");
    }

    void addTetrahedron(double radiance, ColorDbl color, double reflectionCoefficient, double x1, double y1, double z1, double x2, double y2, double z2, double x3, double y3, double z3, double x4, double y4, double z4) {
        Reflection reflection = new OrenNayarDiffuse(standartDeviation, reflectionCoefficient * color.getR() / 255.99, reflectionCoefficient * color.getG() / 255.99, reflectionCoefficient * color.getB() / 255.99);
        Triangle t1 = new Triangle(color, reflection, new Vertex(x1, y1, z1), new Vertex(x2, y2, z2), new Vertex(x3, y3, z3));
        Triangle t2 = new Triangle(color, reflection, new Vertex(x2, y2, z2), new Vertex(x3, y3, z3), new Vertex(x4, y4, z4));
        Triangle t3 = new Triangle(color, reflection, new Vertex(x1, y1, z1), new Vertex(x3, y3, z3), new Vertex(x4, y4, z4));
        Triangle t4 = new Triangle(color, reflection, new Vertex(x1, y1, z1), new Vertex(x2, y2, z2), new Vertex(x4, y4, z4));
        t1.setRadiance(radiance);
        t2.setRadiance(radiance);
        t3.setRadiance(radiance);
        t4.setRadiance(radiance);
        objects.add(t1);
        objects.add(t2);
        objects.add(t3);
        objects.add(t4);
        if (radiance > 0) {
            lightSources.add(t1);
            totalLightArea += t1.getArea();
            lightSources.add(t2);
            totalLightArea += t2.getArea();
            lightSources.add(t3);
            totalLightArea += t3.getArea();
            lightSources.add(t4);
            totalLightArea += t4.getArea();
        }
        System.out.println("Added new Tetrahedron with Radiance: " + radiance + " at P1( " + x1 + " ; " + y1 + " ; " + z1 + " ) P2( " + x2 + " ; " + y2 + " ; " + z2 + " ) P3( " + x3 + " ; " + y3 + " ; " + z3 + " ) P4( " + x4 + " ; " + y4 + " ; " + z4 + " )");
    }

    void addSphere(double radiance, ColorDbl color, double reflectionCoefficient, double x, double y, double z, double radius) {
        Reflection reflection = new OrenNayarDiffuse(standartDeviation, reflectionCoefficient * color.getR() / 255.99, reflectionCoefficient * color.getG() / 255.99, reflectionCoefficient * color.getB() / 255.99);
        Sphere s = new Sphere(color, reflection, new Vertex(x, y, z), radius);
        s.setRadiance(radiance);
        objects.add(s);
        if (radiance > 0) {
            lightSources.add(s);
            totalLightArea += s.getArea();
        }
        System.out.println("Added new Sphere at P( " + x + " ; " + y + " ; " + z + " ) with Radius " + radius);
    }

    void addSpecularSphere(ColorDbl color, double x, double y, double z, double radius) {
        Reflection reflection = new Specular(1.5, color.getR() / 255.99, color.getG() / 255.99, color.getB() / 255.99);
        Sphere s = new Sphere(color, reflection, new Vertex(x, y, z), radius);
        objects.add(s);
        System.out.println("Added new specular transparent Sphere at P( " + x + " ; " + y + " ; " + z + " ) with Radius " + radius);
    }

    public Object getObject(int i) {
        return objects.get(i);
    }

    void constructPhotonMap(int numberOfPhotons) {
        System.out.println("Creating Photon Map:");
        System.out.print("0%");
        int j = 0;
        for (Object lightSource : lightSources) {
            for (int n = 0; n < numberOfPhotons; n++) {
                Ray ray = lightSource.getRandomRay();
                this.traceRay(ray, lightSource);
                ArrayList<Photon> shadowPhotons = ray.getPhotons();
                Photon photon = ray.getFirstPhoton();
                if (photon != null) {
                    Photon removeMe=null;
                    for (Photon p: shadowPhotons) {
                        if(p.getT()==photon.getT()){
                            removeMe = p;
                        }

                    }
                    shadowPhotons.remove(removeMe);
                    photon.setFlux(lightSource.getFlux()/numberOfPhotons);
                    photons.add(photon);
                    photons.addAll(shadowPhotons);
                }
                System.out.print("\r");
                System.out.print(((j * numberOfPhotons + n) * 100.0 / (numberOfPhotons * lightSources.size())) + "%");
            }
            j++;
        }
        photonMap = new Node(searchRadius, xmin, xmax, ymin, ymax, zmin, zmax, photons);
        System.out.print("\r");
        System.out.println("100%");
        System.out.println(photons.size() + " Photons saved in Scene");
    }

    void traceRay(Ray arg, Object self) {
        double min = -1;
        int i = 0;
        for (Object object : objects) {
            boolean isSphere = (self instanceof Sphere);
            if (!object.equals(self) || isSphere) {
                double minDist = 0;
                if (object.equals(self) && isSphere) {
                    minDist = 0.001;
                }
                double t = object.rayIntersection(arg);
                if (t >= minDist) {
                    arg.addIntersection(i, t, object.getNormal(arg.getPoint(t)));
                    if (min == -1 || min > t) {
                        min = t;
                        arg.setFirstIntersection(i, t, object.getNormal(arg.getPoint(t)));
                    }
                }
            }
            i++;
        }
        if (min == -1) {
            errors++;
            System.out.println(" - Couldn't trace ray! It might have escaped the scene. Error #" + errors);
            arg.getStart().printVector("---Start: ");
            arg.getEnd().printVector("-----End: ");
        }
    }

    public int getRandomLightSource() {
        double random = Math.random() * totalLightArea;
        int i = -1;
        while (random > 0) {
            i++;
            random -= lightSources.get(i).getArea();
        }
        return i;
    }

    public Vector directLight(Ray ray) {
        Photon intersection = ray.getFirstPhoton();
        Vector sum = new Vector(0, 0, 0);
        int i;
        Object object = objects.get(intersection.getI());
        for (i = 0; i < 15; i++) {
            int j = this.getRandomLightSource();
            Object lightSource = lightSources.get(j);
            Ray shadowRay = new Ray(intersection.getPosition(), lightSource.getRandomPoint());
            this.traceRay(shadowRay, objects.get(intersection.getI()));

            if (objects.get(shadowRay.getFirstPhoton().getI()).equals(lightSource)) {
                Vector objectNormal = object.getNormal(shadowRay.getStart());
                Direction shadowRayOut = new Direction(shadowRay.getDirectionVector(), objectNormal);
                Vector brdf = object.getReflection().brdf(intersection.getPosition(), shadowRayOut, new Direction(ray.getDirectionVector(), objectNormal));
                double a = shadowRayOut.getInclination();
                double b = new Direction(shadowRay.getDirectionVector(), lightSource.getNormal(shadowRay.getEnd())).getInclination();
                double d = shadowRay.getLength();
                sum = sum.add(brdf.mult(lightSource.getSelfRadiance().scalarMult(lightSource.getArea() * Math.cos(a) * Math.cos(b) / (d * d))));
            }
        }
        return sum.scalarMult(1.0 / i);
    }

    public Vector whittedRayTrace(Ray ray, double iteration) {
        Photon intersection = ray.getFirstPhoton();
        Vector sum = new Vector(0, 0, 0);
        if (objects.get(intersection.getI()).getRadiance() == 0 && iteration < maxIterations) {
            Object object = objects.get(intersection.getI());


            double n1 = 1.0;
            Vector objectNormal = object.getNormal(intersection.getPosition());
            if (Math.abs(ray.getDirectionVector().angleTo(objectNormal)) < (Math.PI / 2)) {
                n1 = 2.25;
                objectNormal = objectNormal.invert();
            }

            Reflection reflectionModel = object.getReflection();

            // Specular Reflection
            if (reflectionModel instanceof Specular) {
                Specular specularReflection = (Specular) reflectionModel;
                Vector I = ray.getDirectionVector().invert().unitVector();
                Direction incoming = new Direction(I, objectNormal);
                double inclinationIncoming = incoming.getInclination();
                Direction outgoing = new Direction(inclinationIncoming, Math.PI + incoming.getAzimuth(), 1, objectNormal);
                Ray reflection = new Ray(intersection.getPosition(), intersection.getPosition().add(outgoing.getAbsoluteCartesian()));
                this.traceRay(reflection, object);
                double n2 = specularReflection.getSpecularIndex();

                if (inclinationIncoming > Math.asin(n2 / n1)) {
                    sum = whittedRayTrace(reflection, iteration + 1);
                } else {
                    Vector N = objectNormal.unitVector();
                    double sqrt = 1.0 - ((n1 / n2) * (n1 / n2) * (1 - Math.pow(N.dotProduct(I), 2)));
                    Vector refractedDirection = I.scalarMult(n1 / n2).add(N.scalarMult(-(n1 / n2) * N.dotProduct(I) - Math.sqrt(sqrt)));

                    Ray refraction = new Ray(intersection.getPosition(), intersection.getPosition().add(refractedDirection));
                    this.traceRay(refraction, object);

                    double rs = Math.pow((n1 * Math.cos(inclinationIncoming) - n2 * Math.sqrt(1 - Math.pow((n1 / n2) * Math.sin(inclinationIncoming), 2))) / (n1 * Math.cos(inclinationIncoming) + n2 * Math.sqrt(1 - Math.pow((n1 / n2) * Math.sin(inclinationIncoming), 2))), 2);
                    double rp = Math.pow((n1 * Math.sqrt(1 - Math.pow((n1 / n2) * Math.sin(inclinationIncoming), 2)) - 2 * Math.cos(inclinationIncoming)) / (n1 * Math.sqrt(1 - Math.pow((n1 / n2) * Math.sin(inclinationIncoming), 2)) + 2 * Math.cos(inclinationIncoming)), 2);
                    double r = (rs + rp) / 2.0;
                    sum = (whittedRayTrace(reflection, iteration + 1).scalarMult(r).add(whittedRayTrace(refraction, iteration + 1).scalarMult(1.0 - r)));

                }
            }

            // Diffuse Reflection (Oren-Nayar or Lambertian)
            else {
                double u1 = Math.random();
                double azimuth = 2 * Math.PI * u1 / probability;
                if (azimuth < 2 * Math.PI) {
                    double u2 = Math.random();
                    double elevation = Math.acos(Math.sqrt(u2));
                    Direction reflectionDirection = new Direction(elevation, azimuth, 1, objectNormal);
                    Ray reflection;

                    reflection = new Ray(intersection.getPosition(), intersection.getPosition().add(reflectionDirection.getAbsoluteCartesian()));

                    traceRay(reflection, object);

                    if (reflection.getFirstPhoton() == null) {
                        System.out.println(reflection.getFirstPhoton() != null);
                        System.out.println("Angle to normal: " + Math.abs(ray.getDirectionVector().angleTo(objectNormal)));
                        objectNormal.printVector("Object-Normal: ");
                        System.out.print("Incl: " + reflectionDirection.getInclination() + " Azim: " + reflectionDirection.getAzimuth());
                        reflectionDirection.getAbsoluteCartesian().printVector(" Reflection Absolute-Vec: ");
                        reflectionDirection.getCartesian().printVector(" Reflection Local-Vec: ");
                        reflection.getStart().printVector("Start: ");
                        reflection.getEnd().printVector("End: ");
                    } else {
                        Vector brdf = reflectionModel.brdf(intersection.getPosition(), reflectionDirection, new Direction(ray.getDirectionVector().invert(), objectNormal));
                        sum = whittedRayTrace(reflection, iteration + 1).mult(brdf).scalarMult(Math.PI / probability);

                    }
                }
            }
        } else {
            sum = objects.get(intersection.getI()).getSelfRadiance();
        }
        return sum.add(directLight(ray));
    }

    public Vector estimateRadiance(Ray ray) {
        this.traceRay(ray, null);
        Photon intersection = ray.getFirstPhoton();
        boolean mc = false;
        Vector sum = new Vector(0, 0, 0);
        ArrayList<Photon> localPhotons = photonMap.getPhotons(intersection.getPosition());
        Object object = objects.get(intersection.getI());
        // remove the "false &&" to enable the photon mapping
        if (false && localPhotons.size() > 10 && !(object.getReflection() instanceof Specular)) {
            for (Photon photon : localPhotons) {
                if (!mc && photon.getFlux() >= 0) {
                    if(photon.getPosition().sub(intersection.getPosition()).length()<=searchRadius){
                        //double d = photon.getPosition().sub(intersection.getPosition()).length();
                        //double wp=Math.max(0,1-d/(filterConstant*searchRadius));
                        Vector brdf = object.getReflection().brdf(intersection.getPosition(), photon.getDirection(), new Direction(ray.getDirectionVector(), object.getNormal(intersection.getPosition())));
                        //sum = sum.add(brdf.scalarMult(photon.getFlux()*wp/(Math.PI*searchRadius*searchRadius*(1- 2/ (3*filterConstant)))));
                        sum = sum.add(brdf.scalarMult(photon.getFlux() / (Math.PI * searchRadius * searchRadius)));
                    }
                }
                else {
                    mc = true;
                }
            }
            sum = sum.add(objects.get(intersection.getI()).getSelfRadiance());
        } else {
            mc = true;
        }
        if (mc) {
            //System.out.println("--MC");
            return whittedRayTrace(ray, 0);
        } else {
            //System.out.println("PM--");
        }
        return sum;
    }

    public int getErrors() {
        return errors;
    }
}
