package nblott.org.scrolling.data;

import android.graphics.Canvas;

/**
 * Created by Nick on 03/10/2017.
 */

public interface Collidable {

    boolean isInPath(Player player, Canvas canvas);
    void collided(Player player, Canvas canvas);
}
