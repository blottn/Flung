package nblott.org.scrolling.data;

import android.graphics.Canvas;

/**
 * Created by Nick on 03/10/2017.
 */

public interface Collidable {

    /*
    *   Should return true if the player object is about to impact on it.   //TODO add mobile interface to abstract this slightly
     */

    boolean isInPath(Mobile mobile, Canvas canvas);
    
    /*
    * Called when an object collides with this Collidable.
    * Generally called like this:
    * if (thing.isInPath()) {
    *       thing.onCollided();
    * }
     */
    
    void onCollided(Mobile mobile, Canvas canvas);
}
