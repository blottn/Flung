package nblott.org.scrolling.data;

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
        //TODO fix this
        int futureLeft = (int) center.getX() + (int) playerVel.getX() - mobile.getWidth();
        int futureBottom = (int) center.getY() + (int) playerVel.getY() - mobile.getHeight();
        int futureRight = (int) center.getX() + (int) playerVel.getX() + mobile.getWidth();
        int futureTop = (int) center.getY() + (int) playerVel.getY() + mobile.getHeight();

        Rect future = new Rect(futureLeft, canvas.getHeight() - futureTop,futureRight, canvas.getHeight() - futureBottom);
        return new Rect(left,top,right,bottom).intersect(future);
    }

    @Override
    public void onCollided(Mobile player, Canvas canvas) {
        int relX = x - this.getCenterX();
        int relY = y - (canvas.getHeight() - this.getCenterY());
        if (Math.abs(relX) > Math.abs(relY)) {
            player.collideX();
        } else {
            player.collideY();
        }
    }
}
