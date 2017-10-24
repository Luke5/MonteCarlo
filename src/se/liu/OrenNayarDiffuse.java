package se.liu;

public class OrenNayarDiffuse implements Reflection {

    private double standardDeviation;
    private double reflectionCoefficientR;
    private double reflectionCoefficientG;
    private double reflectionCoefficientB;

    public OrenNayarDiffuse(double standardDeviation, double reflectionCoefficientR, double reflectionCoefficientG, double reflectionCoefficientB) {
        this.standardDeviation = standardDeviation;
        this.reflectionCoefficientR = reflectionCoefficientR;
        this.reflectionCoefficientG = reflectionCoefficientG;
        this.reflectionCoefficientB = reflectionCoefficientB;
    }

    @Override
    public Vector brdf(Vector x, Direction win, Direction wout) {
        double A,B,a,r,g,b;
        A=1-(standardDeviation*standardDeviation/2*(standardDeviation*standardDeviation+0.33));
        B=(0.45*standardDeviation*standardDeviation)/(standardDeviation*standardDeviation+0.09);
        a=Math.max(win.getInclination(),wout.getInclination());
        b=Math.min(win.getInclination(),wout.getInclination());
        r=(reflectionCoefficientR/Math.PI)*(A+B*Math.max(0,Math.cos(win.getAzimuth()-wout.getAzimuth()))*Math.sin(a)*Math.sin(b));
        g=(reflectionCoefficientG/Math.PI)*(A+B*Math.max(0,Math.cos(win.getAzimuth()-wout.getAzimuth()))*Math.sin(a)*Math.sin(b));
        b=(reflectionCoefficientB/Math.PI)*(A+B*Math.max(0,Math.cos(win.getAzimuth()-wout.getAzimuth()))*Math.sin(a)*Math.sin(b));
        return new Vector(r,g,b);
    }
}
