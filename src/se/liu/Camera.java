package se.liu;

import java.awt.Image;
import java.awt.image.BufferedImage;

public class Camera {
    private Vertex[] eyePoints;
    private int currentEyePoint=0;
    private Pixel[][] pixelPlane;
    private BufferedImage image;

    public Camera(Vertex[] eyePoints, Pixel[][] pixelPlane, int resX, int resY) {
        this.eyePoints = eyePoints;
        this.pixelPlane = pixelPlane;
        image= new BufferedImage(resX,resY,BufferedImage.TYPE_INT_RGB);
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

    public void setCurrentEyePoint(int currentEyePoint) {
        this.currentEyePoint = currentEyePoint;
    }

    public void render(Scene scene){
        for (Pixel[] pixelRow:pixelPlane) {
            for (Pixel pixel:pixelRow) {
                Ray ray = new Ray(this.getEyePoint(this.currentEyePoint),pixel.getPosition());
                scene.traceRay(ray);
                pixel.addRay(ray);
                pixel.calculateColor();
            }
        }
    }

    public BufferedImage createImage(){
        int y=0;
        for (Pixel[] pixelRow:pixelPlane) {
            int x=0;
            for (Pixel pixel:pixelRow) {
                //System.out.println("Pixel X: "+x+" Y: "+y+" RGB-Value: "+pixel.getColor().toIntRGB());
                image.setRGB(x,y,pixel.getColor().toIntRGB());
                x++;
            }
            y++;
        }
        return image;
    }
}
