package nblott.org.scrolling.data;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

/**
 * Created by Nick on 26/09/2017.
 */

public class Player implements Mobile {

    private Drawable drawable;

    private static final double BOUNCE_FACTOR_Y = 0.4;
    private static final double BOUNCE_FACTOR_X = 0.92;
    private static final double VEL_CUT_OFF = 0.3;

    private int x,y,r;
    private Vector vel;
    private Level parent;
    public Player(Level parent, Drawable drawable, int x, int y, int r) {
        this.drawable = drawable;
        this.x = x;
        this.y = y;
        this.r = r;
        this.vel = new Vector(0,0);
        this.parent = parent;
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
            if (Math.abs(vel.getX()) > 0) {
                vel.setX(vel.getX() * BOUNCE_FACTOR_X);
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

    public int getR() {
        return r;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setR(int r) {
        this.r = r;
    }

    @Override
    public Rect getFuture() {
        int futureLeft = this.getX() + (int) getVelocity().getX() - this.getR();
        int futureBottom = this.getY() + (int) getVelocity().getY() - this.getR();
        int futureRight = this.getX() + (int) getVelocity().getX() + this.getR();
        int futureTop = this.getY() + (int) getVelocity().getY() + this.getR();
        return new Rect(futureLeft, futureTop, futureRight, futureBottom);
    }

    @Override
    public Vector getVelocity() {
        return this.vel;
    }

    @Override
    public Vector getCenter() {
        return new Vector(x,y);
    }

    @Override
    public int getWidth() {
        return this.getR();
    }

    @Override
    public int getHeight() {
        return this.getR();
    }
}
