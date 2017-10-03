package nblott.org.scrolling.data;

import android.graphics.Rect;

/**
 * Created by Nick on 03/10/2017.
 */

public interface Mobile {

    //   Returns a Rect descrbing where this object will be in one tick
    Rect getFuture();

    // Gets the current velocity vector
    Vector getVelocity();

    //implemented to be performed when collisions occur
    void collideX();
    void collideY();
}
