package se.liu;

public class ColorDbl {
    private double r, g, b;

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public double getG() {
        return g;
    }

    public void setG(double g) {
        this.g = g;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public ColorDbl(double r, double g, double b) {

        this.r = r;
        this.g = g;
        this.b = b;
    }
    public ColorDbl(String colorName){
        switch (colorName){
            case "white":
                this.r = 255;
                this.g = 255;
                this.b = 255;
            case "black":
                this.r = 0;
                this.g = 0;
                this.b = 0;
            case "red":
                this.r = 255;
                this.g = 0;
                this.b = 0;
            case "green":
                this.r = 0;
                this.g = 255;
                this.b = 0;
            case "blue":
                this.r = 0;
                this.g = 0;
                this.b = 255;
            case "yellow":
                this.r = 255;
                this.g = 255;
                this.b = 0;
            case "pink":
                this.r = 255;
                this.g = 0;
                this.b = 255;
            case "cyan":
                this.r = 0;
                this.g = 255;
                this.b = 255;
            case "orange":
                this.r = 255;
                this.g = 128;
                this.b = 0;
            case "lemon":
                this.r = 128;
                this.g = 255;
                this.b = 0;
            case "purple":
                this.r = 128;
                this.g = 0;
                this.b = 255;
            default:
                this.r = 128;
                this.g = 128;
                this.b = 128;
        }
    }
}
