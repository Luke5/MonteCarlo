package se.liu;

public class ColorDbl {
    private double r, g, b;

    public double getR() {
        return r;
    }

    public int[] toRGBArray(){ return new int[]{(int) this.r, (int) this.g, (int) this.b}; }

    public int toIntRGB() {
        int[] rgbArray =this.toRGBArray();
        rgbArray[0] = (rgbArray[0] < 0) ? 0 : rgbArray[0];
        rgbArray[0] = (rgbArray[0] > 255) ? 255 : rgbArray[0];
        rgbArray[1] = (rgbArray[1] < 0) ? 0 : rgbArray[1];
        rgbArray[1] = (rgbArray[1] > 255) ? 255 : rgbArray[1];
        rgbArray[2] = (rgbArray[2] < 0) ? 0 : rgbArray[2];
        rgbArray[2] = (rgbArray[2] > 255) ? 255 : rgbArray[2];

        return ((int) (rgbArray[0] << 16) & 0x00ff0000)
                | ((int) (rgbArray[1] << 8) & 0x0000ff00)
                | ((int) (rgbArray[2]) & 0x000000ff);

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
                break;
            case "black":
                this.r = 0;
                this.g = 0;
                this.b = 0;
                break;
            case "red":
                this.r = 255;
                this.g = 0;
                this.b = 0;
                break;
            case "green":
                this.r = 0;
                this.g = 255;
                this.b = 0;
                break;
            case "blue":
                this.r = 0;
                this.g = 0;
                this.b = 255;
                break;
            case "yellow":
                this.r = 255;
                this.g = 255;
                this.b = 0;
                break;
            case "pink":
                this.r = 255;
                this.g = 0;
                this.b = 255;
                break;
            case "cyan":
                this.r = 0;
                this.g = 255;
                this.b = 255;
                break;
            case "orange":
                this.r = 255;
                this.g = 128;
                this.b = 0;
                break;
            case "lemon":
                this.r = 128;
                this.g = 255;
                this.b = 0;
                break;
            case "purple":
                this.r = 128;
                this.g = 0;
                this.b = 255;
                break;
            case "sky":
                this.r = 0;
                this.g = 128;
                this.b = 255;
                break;
            default:
                this.r = 128;
                this.g = 128;
                this.b = 128;
                break;
        }
    }
}
