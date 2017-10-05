package nblott.org.scrolling.data;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

/**
 * Created by Nick on 26/09/2017.
 */
/*
    TODO change implementation to use left right top bottom, this will make it possible to use an egg instead of a circle
 */


public class Player implements Mobile {

    private Drawable drawable;

    private static final double BOUNCE_FACTOR_Y = 0.4;
    private static final double BOUNCE_FACTOR_X = 0.92;
    private static final double VEL_CUT_OFF = 0.3;

    private int left,top,right,bottom;

    private Vector vel;
    private Level parent;

    public Player(Level parent, Drawable drawable, int x, int y, int h, int w) {
        this.drawable = drawable;
        this.vel = new Vector(0,0);
        this.parent = parent;

        this.left = x;
        this.bottom = y;
        this.right = x + w;
        this.top = y + h;
    }



    public void physTick(Canvas canvas) {
        adv(canvas);
        ground(canvas);
    }

    private void adv(Canvas canvas) {
        for (Block block : parent.blockList) {
            if (block.isInPath(this, canvas)) {
                block.onCollided(this,canvas);
            }
        }
    }

    public void collideY() {
        if (Math.abs(vel.getY()) < VEL_CUT_OFF) {
            vel.setY(0);
        }
        else {
            vel.setY((-vel.getY()) * BOUNCE_FACTOR_Y);
        }
    }

    public void collideX() {
        if (Math.abs(vel.getX()) < VEL_CUT_OFF) {
            vel.setX(0);
        }
        else {
            vel.setX((-vel.getX()) * BOUNCE_FACTOR_X);
        }
    }


    private void ground(Canvas canvas) {
        //move forward
        top = top + (int) vel.getY();
        bottom = bottom + (int) vel.getY();
        left = left + (int) vel.getX();
        right = right + (int) vel.getX();

        // check is on ground
        if (bottom < 0) {
            if (vel.getX() > 0) {
                vel.setX(vel.getX() - 1);
            }
            // below ground
            vel.setY(-vel.getY());
            vel.setY(vel.getY()/2);
            if (vel.getY() < 0.5 && vel.getY() > -0.5) {
                vel.setY(0);
            }
            // move to ground
            top = top - bottom;
            bottom = 0;
        } else if (bottom > 0) {
            vel.setY(vel.getY() - 2);
            if (vel.getY() < 0.5 && vel.getY() > -0.5) {
                vel.setY(0);
            }
        } else {// y == 0 ie is on ground
            if (Math.abs(vel.getX()) > 0) {
                vel.setX(vel.getX() * BOUNCE_FACTOR_X);
            }
        }
    }

    public void draw(Canvas canvas) {
        drawable.setBounds(left, canvas.getHeight() - top, right, canvas.getHeight() - bottom);
        drawable.draw(canvas);
    }

    @Override
    public Rect getFuture() {
        return null;
    }

    @Override
    public Vector getVelocity() {
        return this.vel;
    }

    @Override
    public Vector getCenter() {
        return new Vector(left + (right - left) / 2, bottom + (top - bottom) / 2);
    }

    @Override
    public int getWidth() {
        return right - left;
    }

    @Override
    public int getHeight() {
        return top - bottom;
    }
}
