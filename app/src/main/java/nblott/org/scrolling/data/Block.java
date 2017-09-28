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
    private View view;
    private Level parent;
    private Drawable image;
    public Block(int x, int y, int w, int h, Drawable image) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.image = image;
    }

    public Block(int x, int w, int h, Drawable image) {
        this(x,0,w,h, image);
    }

    public void draw(Canvas canvas) {
        if (image == null) {
            Paint paint = new Paint();
            paint.setColor(Color.GREEN);
            int left = x;
            int right = x + w;
            int top = canvas.getHeight() - y - h;
            int bottom = canvas.getHeight() - y;
            canvas.drawRect(left, top, right, bottom, paint);
        }
        else {
            int left = x;
            int right = x + w;
            int top = canvas.getHeight() - y - h;
            int bottom = canvas.getHeight() - y;
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

    public Level getParent() {
        return parent;
    }

    public void setParent(Level parent) {
        this.parent = parent;
    }
}
