package nblott.org.flung.model;

import android.graphics.Rect;

/**
 * Created by Nick on 03/10/2017.
 * Interface to describe movable item on the screen
 */

public interface Mobile {

    //   Returns a Rect descrbing where this object will be in one tick
    Rect getFuture();

    // Gets the current velocity vector
    public Vector getVelocity();

    public Vector getCenter();

    public int getWidth();
    public int getHeight();

    public Rect getRect();

    public void translateX(int offset);
    public void translateY(int offset);

    // Implemented to be performed when collisions occur
    public void collideX();
    public void collideY();

}
