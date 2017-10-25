package se.liu;

public class Direction {
    private double inclination, azimuth;
    private Vector cartesian;
    private Vector absoluteCartesian;
    Vector normalSystem = new Vector(0, 0, 1);

    public Direction(double inclination, double azimuth, double r,  Vector normal) {
        this.inclination = inclination;
        this.azimuth = azimuth;
        double x,y,z;
        x= r*Math.cos(azimuth)*Math.sin(inclination);
        y= r*Math.sin(azimuth)*Math.cos(inclination);
        z= r*Math.cos(inclination);
        absoluteCartesian = new Vector(x,y,z);
        cartesian=absoluteCartesian.rotateAroundAxis(normal.crossProduct(normalSystem),normal.angleTo(normalSystem)).unitVector();

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

/*    public Direction(Vector cartesian) {
        double r = cartesian.length();
        double x = cartesian.getX();
        double y = cartesian.getY();
        double z = cartesian.getZ();

        this.azimuth= Math.atan(y/x);
        this.inclination= Math.atan(Math.sqrt(x*x+y*y)/z);
*//*        x= r*Math.cos(azimuth)*Math.sin(inclination);
        y= r*Math.sin(azimuth)*Math.cos(inclination);
        z= r*Math.cos(inclination);*//*
        this.cartesian = new Vector(x,y,z);
    }*/

/*    public Direction(Ray ray){
        Vector cartesian=ray.getEnd().sub(ray.getStart());
        double r = cartesian.length();
        double x = cartesian.getX();
        double y = cartesian.getY();
        double z = cartesian.getZ();

        this.azimuth= Math.atan(y/x);
        this.inclination= Math.atan(Math.sqrt(x*x+y*y)/z);
*//*        x= r*Math.cos(azimuth)*Math.sin(inclination);
        y= r*Math.sin(azimuth)*Math.cos(inclination);
        z= r*Math.cos(inclination);*//*
        this.cartesian = new Vector(x,y,z);

    }*/

    public Vector getAbsoluteCartesian() {
        return absoluteCartesian;
    }

    public Direction(Vector cartesian, Vector normal) {
        absoluteCartesian=cartesian;
        cartesian=cartesian.rotateAroundAxis(normal.crossProduct(normalSystem),normal.angleTo(normalSystem)).unitVector();
        double r = cartesian.length();
        double x = cartesian.getX();
        double y = cartesian.getY();
        double z = cartesian.getZ();

        this.azimuth= Math.atan(y/x)%(Math.PI*2);
        this.inclination= Math.atan(Math.sqrt(x*x+y*y)/z)%(Math.PI/2);
/*        x= r*Math.cos(azimuth)*Math.sin(inclination);
        y= r*Math.sin(azimuth)*Math.cos(inclination);
        z= r*Math.cos(inclination);*/
        this.cartesian = new Vector(x,y,z);
    }

    public Direction invert() {
        return new Direction(this.cartesian.invert(), new Vector(0,0,1));
    }
}
