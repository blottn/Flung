package nblott.org.flung.model;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;

import nblott.org.flung.util.IdGen;

/**
 * Created by Nick on 13/09/2017.
 */

public class Level {

    public List<Block> blockList;
    public List<Integer> ids;
    private Player player;
    private int bgCol;

    public Level(int bgCol, Drawable playerDrawable, Block... blocks) {
        blockList = new ArrayList<>();
        ids = new ArrayList<>();
        for (Block block : blocks) {
            block.setParent(this);
            blockList.add(block);
            ids.add(IdGen.getNext());
        }
        this.bgCol = bgCol;
        player = new Player(this, playerDrawable, 500,500,50,50, new Vector(5,5));
    }

    public void physTick(Canvas canvas){
        player.physTick(canvas);
    }

    //start drawing
    public void draw(Canvas canvas) {
        fillBackground(canvas);
        for (Block block : blockList) {
            block.draw(canvas);
        }
        player.draw(canvas);
    }

    private void fillBackground(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(bgCol);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawPaint(paint);
    }

    public void fling(float velocityX, float velocityY) {
        player.getVelocity().setX(velocityX);
        player.getVelocity().setY(velocityY);
    }
}
