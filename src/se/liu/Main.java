package se.liu;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        // Define resolution of final image
        int resX=1000;
        int resY=700;

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
        // Initialize two Eye Positions
        Vertex[] eyePoints = new Vertex[2];
        eyePoints[0]=new Vertex(-2,0,0);
        eyePoints[1]=new Vertex(-1,0,0);
        // Initialize the Camera Plane
        Pixel[][] pixelPlane= new Pixel[resY][resX];
        for(int y=0; y<resY; y++){
            for(int x=0; x<resX; x++){
                pixelPlane[y][x]=new Pixel(new Vertex(0,(x/(resX/2))-1,((y/(resY/2))-1)*(resY/resY)));
            }
        }
        // Initialize the Camera
        Camera camera = new Camera(eyePoints,pixelPlane,resX,resY);

        camera.render(scene);
        BufferedImage image = camera.createImage();
        File outputfile = new File("image.jpg");
        ImageIO.write(image, "jpg", outputfile);


    }
}
