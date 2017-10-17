package se.liu;

public class Sphere implements Object {

    private ColorDbl color;
    private Vertex center;
    private double radius;

    public Sphere(ColorDbl color, Vertex center, double radius) {
        this.color = color;
        this.center = center;
        this.radius = radius;
    }
    public Sphere(ColorDbl color, double x, double y, double z, double radius) {
        this.color = color;
        Vertex center = new Vertex(x,y,z);
        this.center = center;
        this.radius = radius;
    }

    public Vertex getCenter() {
        return center;
    }

    public double getRadius() {
        return radius;
    }

    @Override

    public double rayIntersection(Ray ray) {
        double a,b,c,d1,d2,w;
        Vector l;
        l=ray.getEnd().sub(ray.getStart()).unitVector();
        a=l.dotProduct(l);
        b=l.scalarMult(2).dotProduct(ray.getStart().sub(this.center));
        c=ray.getStart().sub(this.center).dotProduct(ray.getStart().sub(this.center))-this.radius*this.radius;
        w=(b/2)*(b/2)-a*c;
        if(w>=0){
            d1=-(b/2)-Math.sqrt(w);
            d2=-(b/2)+Math.sqrt(w);
            if(d1<=d2 && d1>=0){
                return d1;
            }
            if(d2>=0){
                return d2;
            }
        }
        return -1;
    }

    @Override
    public ColorDbl getColor() {
        return color;
    }
}
