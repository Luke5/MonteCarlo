package se.liu;

public class Triangle {

    //vertex 3,  color, direction of normal (all private)
    private ColorDbl color;
    private Vector normal;
    private Vertex[] vertices = new Vertex[3];

    public Triangle(ColorDbl color, Vertex v1, Vertex v2, Vertex v3) {
        this.color = color;
        this.vertices[0]=v1;
        this.vertices[1]=v2;
        this.vertices[2]=v3;
        this.calculateNormal();
    }

    public Triangle(ColorDbl color, double x1, double y1, double z1, double x2, double y2, double z2, double x3, double y3, double z3){
        this.color = color;
        Vertex v1 = new Vertex(x1,y1,z1);
        Vertex v2 = new Vertex(x2,y2,z2);
        Vertex v3 = new Vertex(x3,y3,z3);
        this.vertices[0]=v1;
        this.vertices[1]=v2;
        this.vertices[2]=v3;
        this.calculateNormal();

    }
    public ColorDbl getColor() {
        return color;
    }

    public void setColor(ColorDbl color) {
        this.color = color;
    }

    public Vector getNormal() {
        return normal;
    }

    public void calculateNormal() { this.normal = this.vertices[0].sub(this.vertices[1]).crossProduct(this.vertices[0].sub(this.vertices[2])); }

    public double rayIntersection(Ray arg){
        Vector T, E1, E2, D, P, Q;
        double dividend, t, u, v;
        T=arg.getStart().sub(this.vertices[0]);
        E1=this.vertices[1].sub(this.vertices[0]);
        E2=this.vertices[2].sub(this.vertices[0]);
        D=arg.getEnd().sub(arg.getStart());
        P=D.crossProduct(E2);
        Q=T.crossProduct(E1);
        dividend=P.dotProduct(E1);
        t=Q.dotProduct(E2)/dividend;
        u=P.dotProduct(T)/dividend;
        v=Q.dotProduct(D)/dividend;
        if(u+v>1||u<0||v<0){
            t=-1;
        }
        return t;
    }
}
