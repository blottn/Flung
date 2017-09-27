package nblott.org.scrolling.data;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;

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
        player = new Player(this, playerDrawable, 250,170,50,50);
    }

    public void physTick(Canvas canvas){
        player.physTick(canvas);
    }

    //start drawing
    public void draw(Canvas canvas) {
        fillBackground(canvas);
        player.draw(canvas);
        for (Block block : blockList) {
            block.draw(canvas);
        }
    }

    private void fillBackground(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(bgCol);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawPaint(paint);
    }

}
