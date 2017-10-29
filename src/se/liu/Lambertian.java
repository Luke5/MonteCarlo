package se.liu;

public class Lambertian implements Reflection {
    private double reflectionCoefficientR;
    private double reflectionCoefficientG;
    private double reflectionCoefficientB;

    public Lambertian(double reflectionCoefficientR, double reflectionCoefficientG, double reflectionCoefficientB) {
        this.reflectionCoefficientR = reflectionCoefficientR;
        this.reflectionCoefficientG = reflectionCoefficientG;
        this.reflectionCoefficientB = reflectionCoefficientB;
    }

    @Override
    public Vector brdf(Vector x, Direction win, Direction wout) {
        return new Vector(reflectionCoefficientR / Math.PI, reflectionCoefficientG / Math.PI, reflectionCoefficientB / Math.PI);
    }
}