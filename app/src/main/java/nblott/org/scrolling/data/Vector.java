package nblott.org.scrolling.data;

/**
 * Created by Nick on 27/09/2017.
 */

public class Vector {

    private double x;
    private double y;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector() {
        x = 0;
        y = 0;
    }

    public double getMag() {
        return Math.sqrt((x*x) + (y*y));
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
