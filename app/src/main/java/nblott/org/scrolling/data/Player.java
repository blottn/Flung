package nblott.org.scrolling.data;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

/**
 * Created by Nick on 26/09/2017.
 */

public class Player {

    private Drawable drawable;

    private static final double BOUNCE_FACTOR = 0.4;

    private int x,y,r;
    private Vector vel;
    private Level parent;
    public Player(Level parent, Drawable drawable, int x, int y, int r) {
        this.drawable = drawable;
        this.x = x;
        this.y = y;
        this.r = r;
        this.vel = new Vector(6,14);
        this.parent = parent;
    }

    public void physTick(Canvas canvas) {
        adv(canvas);
        standard(canvas);
    }

    private void adv(Canvas canvas) {
        int futureLeft = x + (int) vel.getX() - r;
        int futureBottom = y + (int) vel.getY() - r;
        int futureRight = x + (int) vel.getX() + r;
        int futureTop = y + (int) vel.getY() + r;

        for (Block block : parent.blockList) {
            //check for all corners if any overlap.
            Rect future = new Rect(futureLeft,canvas.getHeight() - futureTop,futureRight, canvas.getHeight() - futureBottom);
//            System.out.println(future);
            Paint paint = new Paint();
            paint.setColor(Color.BLACK);
            canvas.drawRect(future,paint);
            if (block.contains(future)) {
                int relX = future.centerX() - block.getCenterX();
                int relY = future.centerY() - block.getCenterY();
                if ((relX < -relY && relX > relY) || (relX > -relY && relX < relY)) {
                    vel.setY((-vel.getY()) * BOUNCE_FACTOR);
                }
                else {
                    vel.setX((-vel.getX()) * BOUNCE_FACTOR);
                }
            }
            else {
            }
        }
    }


    private void standard(Canvas canvas) {
        y = y + (int) vel.getY();
        x = x + (int) vel.getX();

        // check is on ground
        if (y - r < 0) {
            if (vel.getX() > 0) {
                vel.setX(vel.getX() - 1);
            }
            //below ground
            vel.setY(-vel.getY());
            vel.setY(vel.getY()/2);
            if (vel.getY() < 0.5 && vel.getY() > -0.5) {
                vel.setY(0);
            }
            y = r;
        } else if (y - r > 0) {
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
        int left = x - r;
        int right = x + r;
        int top = canvas.getHeight() - y - r;
        int bottom = canvas.getHeight() - y + r;
        drawable.setBounds(left,top,right,bottom);
        drawable.draw(canvas);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
