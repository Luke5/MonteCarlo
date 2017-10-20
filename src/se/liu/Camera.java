package se.liu;

import java.awt.image.BufferedImage;

public class Camera {
    private Vertex[] eyePoints;
    private int currentEyePoint = 0;
    private Pixel[][] pixelPlane;
    private BufferedImage image;

    Camera(Vertex[] eyePoints, Pixel[][] pixelPlane, int resX, int resY) {
        this.eyePoints = eyePoints;
        this.pixelPlane = pixelPlane;
        image = new BufferedImage(resX, resY, BufferedImage.TYPE_INT_RGB);
        System.out.println("Created new Camera with Resolution " + resX + "x" + resY + " and " + eyePoints.length + " EyePoints");
    }

    public Pixel getPixel(int y, int z) {
        return pixelPlane[z][y];
    }

    public Vertex getEyePoint(int i) {
        return eyePoints[i];
    }

    public void setEyePoints(Vertex[] eyePoints) {
        this.eyePoints = eyePoints;
    }

    public int getCurrentEyePoint() {
        return currentEyePoint;
    }

    void setCurrentEyePoint(int currentEyePoint) {
        this.currentEyePoint = currentEyePoint;
    }

    void render(Scene scene) {
        System.out.println("Rendering Scene:");
        System.out.print("0%");
        int j=0;
        for (Pixel[] pixelRow : pixelPlane) {
            for (Pixel pixel : pixelRow) {
                Ray ray = new Ray(this.eyePoints[this.currentEyePoint], pixel.getPosition());
                scene.traceRay(ray);
                pixel.addRay(ray);
                pixel.calculateColor();
            }
            j++;
            System.out.print("\r");
            System.out.print((j*100.0/(pixelPlane.length))+"%");
        }
        System.out.print("\r");
        System.out.println("100%");
        System.out.println("Rendered Scene successfully using EyePoint #" + (this.currentEyePoint + 1));
    }

    BufferedImage createImage() {
        int y = 0;
        for (Pixel[] pixelRow : pixelPlane) {
            int x = 0;
            for (Pixel pixel : pixelRow) {
                //System.out.println("Pixel X: "+x+" Y: "+y+" RGB-Value: "+pixel.getColor().toIntRGB());
                image.setRGB(x, y, pixel.getColor().toIntRGB());
                x++;
            }
            y++;
        }
        System.out.println("Created Image buffer to save as a file");
        return image;
    }
}
