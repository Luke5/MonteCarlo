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

    public abstract Ray getRandomRay();

    public Direction getRandomDirection(){
        return new Direction(Math.random()*Math.PI/2,Math.random()*2*Math.PI,1);
    }

    public double getFlux(){
        return this.getRadiance()*Math.PI*this.getArea();
    }

    public abstract double getArea();
}