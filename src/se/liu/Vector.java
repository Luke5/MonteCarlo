package se.liu;

import static java.lang.Double.NaN;

public class Vector {
    private double x, y, z;

    public Vector(double x, double y, double z) {
        this.x = x / 1.0;
        this.y = y / 1.0;
        this.z = z / 1.0;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x / 1.0;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y / 1.0;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z / 1.0;
    }

    public Vector add(Vector addedVec) {
        return new Vector(this.x + addedVec.getX(), this.y + addedVec.getY(), this.z + addedVec.getZ());
    }

    public Vector invert() {
        return new Vector(-this.x, -this.y, -this.z);
    }

    public Vector sub(Vector subVec) {
        return this.add(subVec.invert());
    }

    public Vector mult(Vector vector) { return new Vector(this.x*vector.getX(),this.y*vector.getY(),this.z*vector.getZ()); }

    public Vector scalarMult(double r) {
        return new Vector(this.x * r, this.y * r, this.z * r);
    }

    public double dotProduct(Vector vec) {
        return (this.x * vec.getX() + this.y * vec.getY() + this.z * vec.getZ());
    }

    public Vector crossProduct(Vector vec) {
        return new Vector(this.y * vec.getZ() - this.z * vec.getY(), this.z * vec.getX() - this.x * vec.getZ(), this.x * vec.getY() - this.y * vec.getX());
    }

    public double length() {
        return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
    }

    public Vector unitVector() {
        double length = this.length();
        if (length==0){
            System.out.println("Fatal Error: Can not create Unit Vector from a Zero Vector!");
        }
        return new Vector(this.x / length, this.y / length, this.z / length);
    }

    public double angleTo(Vector vector){
        return Math.acos(this.unitVector().dotProduct(vector.unitVector()));
    }

    public Vector rotateAroundAxis(Vector axis, double angle){
        if(angle%(2*Math.PI)==0){ return this; }
        if(axis.length()==0){ return this; }
        axis=axis.unitVector();
        double u,v,w;
        u=axis.getX();
        v=axis.getY();
        w=axis.getZ();
        double xPrime = u*(u*x + v*y + w*z)*(1.0 - Math.cos(angle))+ x*Math.cos(angle)+ (-w*y + v*z)*Math.sin(angle);
        double yPrime = v*(u*x + v*y + w*z)*(1.0 - Math.cos(angle))+ y*Math.cos(angle)+ (w*x - u*z)*Math.sin(angle);
        double zPrime = w*(u*x + v*y + w*z)*(1.0 - Math.cos(angle))+ z*Math.cos(angle)+ (-v*x + u*y)*Math.sin(angle);
        return new Vector(xPrime,yPrime,zPrime);
    }

    public void printVector(String message) {
        System.out.println(message+x+" "+y+" "+z);
    }
}
