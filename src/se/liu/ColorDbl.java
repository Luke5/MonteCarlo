package se.liu;

public class ColorDbl {
    private double r, g, b;

    ColorDbl(double r, double g, double b) {

        this.r = r;
        this.g = g;
        this.b = b;
    }

    ColorDbl(String colorName) {
        switch (colorName) {
            case "random":
                this.r = Math.random() * 255;
                this.g = Math.random() * 255;
                this.b = Math.random() * 255;
            case "white":
                this.r = 255;
                this.g = 255;
                this.b = 255;
                break;
            case "black":
                this.r = 1;
                this.g = 1;
                this.b = 1;
                break;
            case "red":
                this.r = 255;
                this.g = 5;
                this.b = 5;
                break;
            case "green":
                this.r = 5;
                this.g = 255;
                this.b = 5;
                break;
            case "blue":
                this.r = 5;
                this.g = 5;
                this.b = 255;
                break;
            case "yellow":
                this.r = 255;
                this.g = 255;
                this.b = 5;
                break;
            case "pink":
                this.r = 255;
                this.g = 5;
                this.b = 255;
                break;
            case "cyan":
                this.r = 5;
                this.g = 255;
                this.b = 255;
                break;
            case "orange":
                this.r = 255;
                this.g = 128;
                this.b = 5;
                break;
            case "lemon":
                this.r = 128;
                this.g = 255;
                this.b = 5;
                break;
            case "purple":
                this.r = 128;
                this.g = 5;
                this.b = 255;
                break;
            case "sky":
                this.r = 5;
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

    public double getR() {
        return r;
    }

    public double getG() {
        return g;
    }

    public double getB() {
        return b;
    }

    private int[] toRGBArray() {
        return new int[]{(int) this.r, (int) this.g, (int) this.b};
    }

    int toIntRGB() {
        int[] rgbArray = this.toRGBArray();
        rgbArray[0] = (rgbArray[0] < 0) ? 0 : rgbArray[0];
        rgbArray[0] = (rgbArray[0] > 255) ? 255 : rgbArray[0];
        rgbArray[1] = (rgbArray[1] < 0) ? 0 : rgbArray[1];
        rgbArray[1] = (rgbArray[1] > 255) ? 255 : rgbArray[1];
        rgbArray[2] = (rgbArray[2] < 0) ? 0 : rgbArray[2];
        rgbArray[2] = (rgbArray[2] > 255) ? 255 : rgbArray[2];

        return (rgbArray[0] << 16 & 0x00ff0000)
                | (rgbArray[1] << 8 & 0x0000ff00)
                | (rgbArray[2] & 0x000000ff);

    }
}
