package nblott.org.scrolling.data;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

/**
 * Created by Nick on 26/09/2017.
 */

public class Player {

    private Drawable drawable;

    private int x,y,w,h;

    public Player(Drawable drawable, int x, int y, int w, int h) {
        this.drawable = drawable;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public void draw(Canvas canvas) {
        int left = x;
        int right = x + w;
        int top = canvas.getHeight() - y - h;
        int bottom = canvas.getHeight() -y;
        drawable.setBounds(left,top,right,bottom);
        drawable.draw(canvas);
    }
}
