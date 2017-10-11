package nblott.org.flung.model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;

/**
 * Created by Nick on 13/09/2017.
 * Class describing a block which can be drawn and impacted on by the player/ other objects
 */

public class Block implements Collidable {

    private int x, y ,w ,h;
    private int left,right,top,bottom;
    private View view;
    private Level parent;
    private Drawable image;

    public Block(int x,int y,int dim,Drawable image) {
        this(x,y,dim,dim,image);
    }


    private Block(int x, int y, int w, int h, Drawable image) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.image = image;
    }

    void draw(Canvas canvas) {
        left = x;
        right = x + w;
        top = canvas.getHeight() - y - h;
        bottom = canvas.getHeight() - y;
        if (image == null) {
            Paint paint = new Paint();
            paint.setColor(Color.GREEN);
            canvas.drawRect(left, top, right, bottom, paint);
        }
        else {
            image.setBounds(left, top, right, bottom);
            image.draw(canvas);
        }
    }

    private int getCenterX() {
        return new Rect(left,top,right,bottom).centerX();
    }

    private int getCenterY() {
        return new Rect(left,top,right,bottom).centerY();
    }

    void setParent(Level parent) {
        this.parent = parent;
    }

    @Override
    public boolean isInPath(Mobile mobile, Canvas canvas) {
        Vector playerVel = mobile.getVelocity();

        Vector center = mobile.getCenter();
        Rect box = mobile.getRect();
        //TODO fix this
        int futureLeft = box.left + (int) playerVel.getX();
        int futureBottom = box.bottom + (int) playerVel.getY();
        int futureRight = box.right + (int) playerVel.getX();
        int futureTop = box.top + (int) playerVel.getY();

        Rect future = new Rect(futureLeft, canvas.getHeight() - futureTop,futureRight, canvas.getHeight() - futureBottom);
        return new Rect(left,top,right,bottom).intersect(future);
    }

    @Override
    public void onCollided(Mobile mobile, Canvas canvas) {
        int relX = this.getCenterX() - (int) mobile.getCenter().getX();
        int relY = - ((canvas.getHeight() - this.getCenterY()) - (int) mobile.getCenter().getY());
        System.out.println("relY = " + relY);
        Rect rect = mobile.getRect();
        if (Math.abs(relX) > Math.abs(relY)) {
            mobile.collideX();
            if (relX < 0) {
                //right
                mobile.translateX(rect.left - right);
            }
            else {
                //left
                mobile.translateX(left - rect.right );
            }
        } else {
            mobile.collideY();
            if (relY > 0) {
                mobile.translateY(-(rect.bottom - (canvas.getHeight() - top)));
            }
            else {
                mobile.translateY(-(rect.top - (canvas.getHeight() - bottom)));
            }
        }
    }

    @Override
    public Rect getRect() {
        return new Rect(left,top,right,bottom);
    }
}
