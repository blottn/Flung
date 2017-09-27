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
    private int vx, vy;
    private Level parent;
    public Player(Level parent, Drawable drawable, int x, int y, int w, int h) {
        this.drawable = drawable;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.vx = 4;
        this.vy = 8;
        this.parent = parent;
    }

    public void physTick(Canvas canvas) {

//        for (Block block : parent.blockList) {
//            if (block.getX() <= x + w && x + w <= block.getX() + block.getW() || x >= block.getX() && x <= block.getX() + block.getW()) {
//                // if bottom is below top but above bottom push up
//            }
//        }

        y = y + vy;
        x = x + vx;

        // check is on ground
        if (y < 0) {
            //below ground
            vy = -vy;
            vy  /=2;
            y = 0;
        } else if (y > 0) {
            vy -= 2;
        } else {// y == 0 ie is on ground
            if (vx > 0) {
                vx -= 1;
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
