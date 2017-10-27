package se.liu;

import se.liu.test.VectorTest;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.Object;

public class Main {

    public static void main(String[] args) throws IOException {
        // Run jUnit Tests
        //System.out.println("Starting jUnit-Tests...");
        //junit.swingui.TestRunner.run(VectorTest.class);

        System.out.println("Starting process...");

        // Define the number of photons
        int numberOfPhotons = 10000;

        // Define resolution of final image
        int resX = 1000;
        int resY = 1000;

        Scene scene = new Scene();
        
        double standard = 0.7;
        double high = 0.9;
        double low = 0.5;

        // Avoid escaping Rays by adding a giant sphere around the entire scene
        scene.addSphere(0, new ColorDbl("white"), standard,5,0,0,20);

        // Floor
        scene.addTriangle(0, new ColorDbl("white"), standard, -3, 0, -5, 0, 6, -5, 0, -6, -5);
        scene.addRectangle(0, new ColorDbl("white"), standard, 10, 6, -5, 0, 6, -5, 0, -6, -5, 10, -6, -5);
        scene.addTriangle(0, new ColorDbl("white"), standard, 10, 6, -5, 10, -6, -5, 13, 0, -5);
        // Ceiling
        scene.addTriangle(0, new ColorDbl("white"), standard, -3, 0, 5, 0, 6, 5, 0, -6, 5);
        scene.addRectangle(0, new ColorDbl("white"), standard, 10, 6, 5, 0, 6, 5, 0, -6, 5, 10, -6, 5);
        scene.addTriangle(0, new ColorDbl("white"), standard, 10, 6, 5, 10, -6, 5, 13, 0, 5);
        // Walls
        scene.addRectangle(0, new ColorDbl("red"), standard, -3, 0, 5, 0, 6, 5, 0, 6, -5, -3, 0, -5);
        scene.addRectangle(0, new ColorDbl("yellow"), standard, -3, 0, 5, 0, -6, 5, 0, -6, -5, -3, 0, -5);
        scene.addRectangle(0, new ColorDbl("green"), standard, 10, -6, 5, 0, -6, 5, 0, -6, -5, 10, -6, -5);
        scene.addRectangle(0, new ColorDbl("cyan"), standard, 10, -6, 5, 13, 0, 5, 13, 0, -5, 10, -6, -5);
        scene.addRectangle(0, new ColorDbl("blue"), standard, 10, 6, 5, 13, 0, 5, 13, 0, -5, 10, 6, -5);
        scene.addRectangle(0, new ColorDbl("pink"), standard, 10, 6, 5, 0, 6, 5, 0, 6, -5, 10, 6, -5);
        // Spheres
        scene.addSphere(0, new ColorDbl("lemon"), high,10,6,0,2);
        scene.addSphere(0, new ColorDbl("orange"), low,13,0,5,4);
        scene.addSphere(0, new ColorDbl("purple"), standard,10,-6,-4,3);
        // Tetrahedron
        scene.addTetrahedron(0, new ColorDbl("sky"), high,5, 5, -5, 6, 5, -5, 5.5, 4.5, -3.5, 5.5, 4, -5);

        // Mirror Triangle
        //scene.addSpecularTriangle(new ColorDbl("white"),7,0,-1,8,0.5,-3.5,9,-3.5,-3.5);

        // Transparent Sphere
        scene.addSpecularSphere(new ColorDbl("white"),6,2,-3,2);

        //Light Tetrahedron -Daft Punk Alike-
        /*scene.addTriangle(2000, new ColorDbl("white"),standard,7,0,2,5.5,1.5,2,5.5,-1.5,2);
        scene.addTriangle(1500, new ColorDbl("green"),standard,7,0,2,5.5,-1.5,2,6.25,0,3.5);
        scene.addTriangle(1500, new ColorDbl("red"),standard,7,0,2,6.25,0,3.5,5.5,1.5,2);
        scene.addTriangle(1500, new ColorDbl("blue"),standard,6.25,0,3.5,5.5,-1.5,2,5.5,1.5,2);
*/

        // Light
        //scene.addSphere(16, new ColorDbl("white"),standard,5,0,5,0.7);
        //scene.addTriangle(1000, new ColorDbl("white"),standard,3,0,4.99,4.5,-1.5,4.99,4.5,1.5,4.99);
        //scene.addTriangle(1000, new ColorDbl("white"),standard,6,0,-4.99,7.5,-1.5,-4.99,7.5,1.5,-4.99);
        scene.addTriangle(1500, new ColorDbl("white"),standard, -1.05,0,-1,-1.05,1.5,1.5,-1.05,-1.5,1.5);
        //scene.addTriangle(15, new ColorDbl("white"),standard, 8,-1,2,8,0.5,3.5,8,-2.5,3.5);
        // 1st Pass: Create Photon Map
        //scene.constructPhotonMap(numberOfPhotons);

        // Initialize two Eye Positions
        Vertex[] eyePoints = new Vertex[2];
        eyePoints[0] = new Vertex(-2, 0, 0);
        eyePoints[1] = new Vertex(-1, 0, 0);
        // Initialize the Camera Plane
        System.out.println("Building Pixel Plane:");
        Pixel[][] pixelPlane = new Pixel[resY][resX];
        double xPos = 0;
        System.out.print("0%");
        for (int y = 0; y < resY; y++) {
            double zPos = -(((y / (((double) resY) / 2.0)) - 1.0) * (((double) resY) / ((double) resX)));
            for (int x = 0; x < resX; x++) {
                double yPos = -((x / (((double) resX) / 2.0)) - 1.0);
                pixelPlane[y][x] = new Pixel(new Vertex(xPos, yPos, zPos));
            }
            System.out.print("\r");
            System.out.print((y*100.0/resY)+"%");
        }
        System.out.print("\r");
        System.out.println("100%");
        // Initialize the Camera
        Camera camera = new Camera(eyePoints, pixelPlane, resX, resY);
        camera.setCurrentEyePoint(1);
        // Render the Scene
        camera.render(scene);
        // Writing all Pixels into a BufferedImage and writing into jpg-file
        BufferedImage image = camera.createImage();
        int name = (int) System.currentTimeMillis();
        File outputfile = new File("image"+name+".jpg");
        ImageIO.write(image, "jpg", outputfile);
        System.out.println("Saved Image-File to directory");

    }
}
