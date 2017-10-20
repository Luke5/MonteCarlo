package se.liu;

public class OrenNayarDiffuse implements Reflection {

    private double standardDeviation;
    private double reflectionCoefficient;

    public OrenNayarDiffuse(double standardDeviation, double reflectionCoefficient) {
        this.standardDeviation = standardDeviation;
        this.reflectionCoefficient = reflectionCoefficient;
    }

    @Override
    public double brdf(Vector x, Direction win, Direction wout) {
        double A,B,a,b;
        A=1-(standardDeviation*standardDeviation/2*(standardDeviation*standardDeviation+0.33));
        B=(0.45*standardDeviation*standardDeviation)/(standardDeviation*standardDeviation+0.09);
        a=Math.max(win.getInclination(),wout.getInclination());
        b=Math.min(win.getInclination(),wout.getInclination());
        return (reflectionCoefficient/Math.PI)*(A+B*Math.max(0,Math.cos(win.getAzimuth()-wout.getAzimuth()))*Math.sin(a)*Math.sin(b));
    }
}
