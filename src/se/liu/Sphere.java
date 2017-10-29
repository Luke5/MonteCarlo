package se.liu;

public class Sphere extends Object {

    private Vertex center;
    private double radius;

    public Sphere(ColorDbl color, Reflection reflection, Vertex center, double radius) {
        this.setColor(color);
        this.setReflection(reflection);
        this.center = center;
        this.radius = radius;
    }

    public Sphere(ColorDbl color, Reflection reflection, double x, double y, double z, double radius) {
        this.setColor(color);
        this.setReflection(reflection);
        this.center = new Vertex(x, y, z);
        this.radius = radius;
    }

    @Override
    public double rayIntersection(Ray ray) {
        double a, b, c, d1, d2, w;
        Vector l;
        l = ray.getEnd().sub(ray.getStart()).unitVector();
        a = l.dotProduct(l);
        b = l.scalarMult(2).dotProduct(ray.getStart().sub(this.center));
        c = ray.getStart().sub(this.center).dotProduct(ray.getStart().sub(this.center)) - this.radius * this.radius;
        w = (b / 2) * (b / 2) - a * c;
        if (w >= 0) {
            d1 = -(b / 2) - Math.sqrt(w);
            d2 = -(b / 2) + Math.sqrt(w);
            if (d1 <= d2 && d1 >= 0) {
                return d1;
            }
            if (d2 >= 0) {
                return d2;
            }
        }
        return -1;
    }

    @Override
    public Vector getRandomPoint() {
        Vector vector = new Vector(Math.random() - 0.5, Math.random() - 0.5, Math.random() - 0.5).unitVector().scalarMult(radius);
        return this.center.add(vector);
    }

    @Override
    public Vector getNormal(Vector surfacePoint) {
        Vector normal = surfacePoint.sub(center).unitVector();
        return normal;
    }

    @Override
    public double getArea() {
        return 4 * Math.PI * this.radius;
    }

}
