package nblott.org.scrolling.data;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;

/**
 * Created by Nick on 13/09/2017.
 */

public class Block{

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

//    private Block(int x, int w, int h, Drawable image) {
//        this(x,0,w,h, image);
//    }

    public void draw(Canvas canvas) {
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

    public void physTick() {

    }

    public void setView(View view) {
        this.view = view;
    }

    public View getView() {
        return view;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getCenterX() {
        return new Rect(left,top,right,bottom).centerX();
    }

    public int getCenterY() {
        return new Rect(left,top,right,bottom).centerY();
    }


    public Level getParent() {
        return parent;
    }

    public boolean contains(Vector pos) {
        return new Rect(left,top,right,bottom).contains((int) pos.getX(),(int) pos.getY());
    }

    public boolean contains(Rect rect) {
        return new Rect(left,top,right,bottom).intersect(rect);
    }

    public void setParent(Level parent) {
        this.parent = parent;
    }
}
