package nblott.org.scrolling.data;

import android.view.View;

/**
 * Created by Nick on 13/09/2017.
 */

public class Block {

    private int x, y ,w ,h;
    private View view;
    public Block(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public Block(int x, int w, int h) {
        this(x,0,w,h);
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
}
