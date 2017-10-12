package se.liu;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Scene scene = new Scene();
        // Floor
        scene.addTriangle(new ColorDbl("white"),-3,0,-5,0,6,-5,0,-6,-5);
        scene.addRectangle(new ColorDbl("white"),10,6,-5,0,6,-5,0,-6,-5,10,-6,-5);
        scene.addTriangle(new ColorDbl("white"),10,6,-5,10,-6,-5,13,0,-5);
        // Ceiling
        scene.addTriangle(new ColorDbl("white"),-3,0,5,0,6,5,0,-6,5);
        scene.addRectangle(new ColorDbl("white"),10,6,5,0,6,5,0,-6,5,10,-6,5);
        scene.addTriangle(new ColorDbl("white"),10,6,5,10,-6,5,13,0,5);
        // Walls
        scene.addRectangle(new ColorDbl("red"),-3,0,5,0,6,5,0,6,-5,-3,0,-5);
        scene.addRectangle(new ColorDbl("yellow"),-3,0,5,0,-6,5,0,-6,-5,-3,0,-5);
        scene.addRectangle(new ColorDbl("green"),10,-6,5,0,-6,5,0,-6,-5,10,-6,-5);
        scene.addRectangle(new ColorDbl("cyan"),10,-6,5,13,0,5,13,0,-5,10,-6,-5);
        scene.addRectangle(new ColorDbl("blue"),10,6,5,13,0,5,13,0,-5,10,6,-5);
        scene.addRectangle(new ColorDbl("pink"),10,6,5,0,6,5,0,6,-5,10,6,-5);
    }
}
