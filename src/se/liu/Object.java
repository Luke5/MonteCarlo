package se.liu;

public abstract class Object {
    private ColorDbl color;
    private double radiance = 0;
    private Reflection reflection;

    public double getRadiance() {
        return radiance;
    }

    public void setRadiance(double radiance) {
        this.radiance = radiance;
    }

    public Vector getSelfRadiance() {
        return new Vector(radiance * color.getR() / 255.99, radiance * color.getG() / 255.99, radiance * color.getB() / 255.99);
    }

    abstract double rayIntersection(Ray ray);

    ColorDbl getColor() {
        return color;
    }

    void setColor(ColorDbl color) {
        this.color = color;
    }

    public Reflection getReflection() {
        return reflection;
    }

    void setReflection(Reflection reflection) {
        this.reflection = reflection;
    }

    public Ray getRandomRay() {
        Vector point = this.getRandomPoint();
        Direction direction = new Direction(Math.random() * Math.PI / 2, Math.random() * 2 * Math.PI, 1, this.getNormal(point));
        return new Ray(point, point.add(direction.getAbsoluteCartesian()));
    }

    ;

    public double getFlux() {
        return this.getRadiance() * Math.PI * this.getArea();
    }

    public abstract Vector getRandomPoint();

    public abstract Vector getNormal(Vector surfacePoint);

    public abstract double getArea();
}