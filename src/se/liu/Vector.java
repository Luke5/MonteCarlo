package se.liu;

public class Vector {
    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public double getX() {

        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    private double x, y, z;

    public Vector add(Vector addedVec){
        Vector sum = new Vector(this.x+addedVec.getX(),this.y+addedVec.getY(),this.z+addedVec.getZ());
        return sum;
    }

    public Vector invert(){
        Vector i = new Vector(-this.x,-this.y,-this.z);
        return i;
    }

    public Vector sub(Vector subVec){
        return this.add(subVec.invert());
    }

    public Vector scalarMult(double r){
        Vector product = new Vector(this.x*r,this.y*r,this.z*r);
        return product;
    }

    public double dotProduct( Vector vec){
        return (this.x*vec.getX()+this.y+vec.getY()+this.z*vec.getZ());
    }

    public Vector crossProduct(Vector vec){
        Vector product = new Vector(this.y*vec.getZ()-this.z*vec.getY(),this.z*vec.getX()-this.x*vec.getZ(),this.x*vec.getY()-this.y*vec.getX());
        return product;
    }

    public double length(){
        return Math.sqrt(this.x*this.x+this.y*this.y+this.z*this.z);
    }

    public Vector unitVector(){
        double length =this.length();
        Vector unit = new Vector(this.x/length,this.y/length,this.z/length);
        return unit;
    }
}
