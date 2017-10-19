package se.liu;

public class Lambertian implements Reflection {
    private double reflectionCoefficient;

    public Lambertian(double reflectionCoefficient) {
        this.reflectionCoefficient = reflectionCoefficient;
    }

    @Override
    public double brdf(Vertex x, Direction win, Direction wout) {
        return reflectionCoefficient/Math.PI;
    }
}
