package se.liu;

public class Triangle {

    //vertex 3,  color, direction of normal (all private)
    private ColorDbl color;
    private Vertex normal;
    private Vertex[] vertices = new Vertex[3];

    public Triangle(ColorDbl color, Vertex normal, Vertex v1, Vertex v2, Vertex v3) {
        this.color = color;
        this.normal = normal;
        this.vertices[0]=v1;
        this.vertices[1]=v2;
        this.vertices[2]=v3;
    }

    public ColorDbl getColor() {
        return color;
    }

    public void setColor(ColorDbl color) {
        this.color = color;
    }

    public Vertex getNormal() {
        return normal;
    }

    public void setNormal(Vertex normal) {
        this.normal = normal;
    }
}
