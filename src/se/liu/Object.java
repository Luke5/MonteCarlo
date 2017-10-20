package se.liu;

public abstract class Object {
    private ColorDbl color;
    private double radiance=0;

    public double getRadiance() {
        return radiance;
    }

    public void setRadiance(double radiance) {
        this.radiance = radiance;
    }

    private Reflection reflection;

    abstract double rayIntersection(Ray ray);

    ColorDbl getColor() {
        return color;
    }

    void setColor(ColorDbl color) {
        this.color = color;
    }

    void setReflection(Reflection reflection) {
        this.reflection = reflection;
    }

    public Reflection getReflection() {
        return reflection;
    }
}