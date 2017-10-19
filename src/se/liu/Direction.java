package se.liu;

public class Direction {
    private double inclination, azimuth;
    private Vector cartesian;
    public Direction(double inclination, double azimuth, double r) {
        this.inclination = inclination%(Math.PI/2);
        this.azimuth = azimuth%Math.PI;
        double x,y,z;
        x= r*Math.cos(azimuth)*Math.sin(inclination);
        y= r*Math.sin(azimuth)*Math.cos(inclination);
        z=r*Math.cos(inclination);
        cartesian.setX(x);
        cartesian.setY(y);
        cartesian.setZ(z);
    }

    public double getInclination() {
        return inclination;
    }

    public double getAzimuth() {
        return azimuth;
    }

    public Vector getCartesian() {
        return cartesian;
    }

    public Direction(Vector cartesian) {
        this.cartesian=cartesian;
        double r = cartesian.length();
        double x = cartesian.getX();
        double y = cartesian.getY();
        double z = cartesian.getZ();

        this.azimuth= Math.atan(y/x);
        this.inclination= Math.atan(Math.sqrt(x*x+y*y)/z);
    }
}
