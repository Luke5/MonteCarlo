package se.liu;

public class Specular implements Reflection {

    private double kd = 0.01;
    private double reflectionCoefficientR;
    private double reflectionCoefficientG;
    private double reflectionCoefficientB;
    private double specularIndex;

    public Specular(double specularIndex) {
        this.specularIndex = specularIndex;
    }


    public Specular(double specularIndex, double reflectionCoefficientR, double reflectionCoefficientG, double reflectionCoefficientB) {
        this.reflectionCoefficientR = reflectionCoefficientR;
        this.reflectionCoefficientG = reflectionCoefficientG;
        this.reflectionCoefficientB = reflectionCoefficientB;
        this.specularIndex = specularIndex;
    }

    @Override
    public Vector brdf(Vector x, Direction win, Direction wout) {
        double l = kd * Math.cos(wout.getInclination());
        return new Vector(l * reflectionCoefficientR, l * reflectionCoefficientG, l * reflectionCoefficientB);
    }

    public double getSpecularIndex() {
        return specularIndex;
    }
}
