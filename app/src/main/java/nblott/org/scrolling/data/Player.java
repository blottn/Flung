package nblott.org.scrolling.data;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

/**
 * Created by Nick on 26/09/2017.
 */

public class Player {

    private Drawable drawable;

    private int x,y,w,h;
    private Vector vel;
    private Level parent;
    public Player(Level parent, Drawable drawable, int x, int y, int w, int h) {
        this.drawable = drawable;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.vel = new Vector(4,8);
        this.parent = parent;
    }

    public void physTick(Canvas canvas) {

        y = y + (int) vel.getY();
        x = x + (int) vel.getX();

        // check is on ground
        if (y < 0) {
            if (vel.getX() > 0) {
                vel.setX(vel.getX() - 1);
            }
            //below ground
            vel.setY(-vel.getY());
            vel.setY(vel.getY()/2);
            if (vel.getY() < 0.5 && vel.getY() > -0.5) {
                vel.setY(0);
            }
            y = 0;
        } else if (y > 0) {
            vel.setY(vel.getY() - 2);
            if (vel.getY() < 0.5 && vel.getY() > -0.5) {
                vel.setY(0);
            }
        } else {// y == 0 ie is on ground
            if (vel.getX() > 0) {
                vel.setX(vel.getX() - 1);
            }
        }
    }

    public void draw(Canvas canvas) {
        int left = x;
        int right = x + w;
        int top = canvas.getHeight() - y - h;
        int bottom = canvas.getHeight() - y;
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
