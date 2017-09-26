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

    public int getH() {
        return h;
    }

    public int getW() {
        return w;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setH(int h) {
        this.h = h;
    }

    public void setW(int w) {
        this.w = w;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
