package se.liu;

public class Vertex {
    public Vertex(float x, float y, float z, float w) {
        vec= new Vector(x,y,z);
        this.w = w;
    }
    public Vector vec;
    private float w;

    public float getW() {
        return w;
    }

    public void setW(float w) {
        this.w = w;
    }
}