package nblott.org.scrolling.data;

/**
 * Created by Nick on 27/09/2017.
 */

public class Vector {

    private final double MAX_X;
    private final double MAX_Y;

    private double x;
    private double y;

    public Vector() {
        this(0,0);
    }

    public Vector(double x, double y) {
        this(x,y, Double.MAX_VALUE, Double.MAX_VALUE);
    }

    public Vector(double x, double y, double maxX, double maxY) {
        this.x = x;
        this.y = y;
        this.MAX_X = maxX;
        this.MAX_Y = maxY;
    }

    public double getMag() {
        return Math.sqrt((x*x) + (y*y));
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        if (x > MAX_X) {
            this.x = MAX_X;
        }
        else {
            this.x = x;
        }
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        if (y > MAX_Y) {
            this.y = MAX_Y;
        }
        else {
            this.y = y;
        }
    }

    public String toString() {
        return ("X: " + x + " Y: " + y);
    }
}
