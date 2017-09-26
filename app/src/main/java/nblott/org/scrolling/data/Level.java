package nblott.org.scrolling.data;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nick on 13/09/2017.
 */

public class Level {

    public List<Block> blockList;
    public List<Integer> ids;

    private int bgCol;

    public Level(int bgCol, Block... blocks) {
        blockList = new ArrayList<>();
        ids = new ArrayList<>();
        for (Block block : blocks) {
            blockList.add(block);
            ids.add(IdGen.getNext());
        }
        this.bgCol = bgCol;
    }

    //start drawing
    public void draw(Canvas canvas) {
//        Paint paint = new Paint();
//        paint.setARGB(255,0,255,0);
//        canvas.drawLine(10,10,100,100,paint);
        fillBackground(canvas);
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
