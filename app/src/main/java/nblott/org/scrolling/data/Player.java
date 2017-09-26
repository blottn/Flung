package nblott.org.scrolling.data;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

/**
 * Created by Nick on 26/09/2017.
 */

public class Player {

    private Drawable drawable;

    public Player(Drawable drawable) {
        this.drawable = drawable;
    }

    public void draw(Canvas canvas) {
        drawable.setBounds(10,10,40,40);
        drawable.draw(canvas);




    }
}
