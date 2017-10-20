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

        // Define resolution of final image
        int resX = 1000;
        int resY = 1000;

        Scene scene = new Scene();
        //
        Reflection standard = new Lambertian(0.8);
        // Floor
        scene.addTriangle(new ColorDbl("white"), standard, -3, 0, -5, 0, 6, -5, 0, -6, -5);
        scene.addRectangle(new ColorDbl("white"), standard, 10, 6, -5, 0, 6, -5, 0, -6, -5, 10, -6, -5);
        scene.addTriangle(new ColorDbl("white"), standard, 10, 6, -5, 10, -6, -5, 13, 0, -5);
        // Ceiling
        scene.addTriangle(new ColorDbl("white"), standard, -3, 0, 5, 0, 6, 5, 0, -6, 5);
        scene.addRectangle(new ColorDbl("white"), standard, 10, 6, 5, 0, 6, 5, 0, -6, 5, 10, -6, 5);
        scene.addTriangle(new ColorDbl("white"), standard, 10, 6, 5, 10, -6, 5, 13, 0, 5);
        // Walls
        scene.addRectangle(new ColorDbl("red"), standard, -3, 0, 5, 0, 6, 5, 0, 6, -5, -3, 0, -5);
        scene.addRectangle(new ColorDbl("yellow"), standard, -3, 0, 5, 0, -6, 5, 0, -6, -5, -3, 0, -5);
        scene.addRectangle(new ColorDbl("green"), standard, 10, -6, 5, 0, -6, 5, 0, -6, -5, 10, -6, -5);
        scene.addRectangle(new ColorDbl("cyan"), standard, 10, -6, 5, 13, 0, 5, 13, 0, -5, 10, -6, -5);
        scene.addRectangle(new ColorDbl("blue"), standard, 10, 6, 5, 13, 0, 5, 13, 0, -5, 10, 6, -5);
        scene.addRectangle(new ColorDbl("pink"), standard, 10, 6, 5, 0, 6, 5, 0, 6, -5, 10, 6, -5);
        // Spheres
        scene.addSphere(new ColorDbl("lemon"), standard,10,6,0,2);
        scene.addSphere(new ColorDbl("orange"), standard,13,0,5,4);
        scene.addSphere(new ColorDbl("purple"), standard,10,-6,-5,3);
        // Light
        scene.addLightSource(new Sphere(new ColorDbl("white"),standard,5,0,5,0.5));
        // Tetrahedron
        scene.addTetrahedron(new ColorDbl("sky"), standard,5, 5, -5, 6, 5, -5, 5.5, 4.5, -3.5, 5.5, 4, -5);
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
        File outputfile = new File("image.jpg");
        ImageIO.write(image, "jpg", outputfile);
        System.out.println("Saved Image-File to directory");

    }
}
