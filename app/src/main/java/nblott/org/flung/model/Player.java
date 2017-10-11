package nblott.org.flung.model;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

/**
 * Created by Nick on 26/09/2017.
 */

public class Player implements Mobile {

    private Drawable drawable;

    private static final double BOUNCE_FACTOR_Y = 0.7;
    private static final double BOUNCE_FACTOR_X = 0.92;
    private static final double VEL_CUT_OFF = 1.3;
    public static final double GRAVITY = 1.5;

    private int left,top,right,bottom;
    private int height, width;
    private Vector vel;
    private Level parent;

    public Player(Level parent, Drawable drawable, int x, int y, int h, int w, Vector velocity) {
        this.drawable = drawable;
        this.vel = velocity;
        this.parent = parent;

        this.left = x;
        this.bottom = y;
        this.right = x + w;
        this.top = y + h;
        this.height = h;
        this.width = w;
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

    @Override
    public Rect getRect() {
        return new Rect(left,top,right,bottom);
    }

    @Override
    public void translateX(int offset) {
        this.left += offset;
        this.right += offset;
    }

    @Override
    public void translateY(int offset) {
        this.top += offset;
        this.bottom += offset;
    }

    public void physTick(Canvas canvas) {
        adv(canvas);
        move();
        ground(canvas);
    }

    private void adv(Canvas canvas) {
        for (Block block : parent.blockList) {
            if (block.isInPath(this, canvas)) {
                block.onCollided(this,canvas);
            }
        }
        if (floating(canvas)) {
            vel.setY(vel.getY() - GRAVITY);
        }
        else {
            vel.setX(vel.getX() * BOUNCE_FACTOR_X);
            if (Math.abs(vel.getX()) < VEL_CUT_OFF) {
                vel.setX(0);
            }
        }
    }

    private boolean floating(Canvas canvas) {
        for (Block block : parent.blockList) {
            if (this.bottom == canvas.getHeight() - block.getRect().top) {
                return false;
            }
        }
        return true;
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

        // check is on ground
        if (bottom < 0) {
            if (vel.getX() > 0) {
                vel.setX(vel.getX() * BOUNCE_FACTOR_X);
                if (Math.abs(vel.getX()) < VEL_CUT_OFF) {
                    vel.setX(0);
                }
            }
            // below ground
            vel.setY(-vel.getY());
            vel.setY(vel.getY()/2);
            if (vel.getY() < 0.5 && vel.getY() > -0.5) {
                vel.setY(0);
            }
            // move to ground
            top = height;
            bottom = 0;
        } else if (bottom > 0) {
            //needs a check to ensure not above anything
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


    private void move() {
        top += vel.getY();
        bottom += vel.getY();
        right += vel.getX();
        left += vel.getX();
    }
}
