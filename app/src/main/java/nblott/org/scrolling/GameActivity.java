package nblott.org.scrolling;

import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import nblott.org.scrolling.R;
import nblott.org.scrolling.data.Block;
import nblott.org.scrolling.data.Level;

/**
 * Created by Nick on 04/10/2017.
 */

public class GameActivity extends AppCompatActivity {

    final Handler mHandler = new Handler();
    SurfaceView surface;
    ConstraintLayout screen;
    Level current;

    Runnable ticker;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        surface = (SurfaceView) findViewById(R.id.surface);
        current = new Level(ContextCompat.getColor(this, R.color.levelBG),getResources().getDrawable(R.drawable.player, null), new Block[]{new Block(375,0,50, null)});
    }



    @Override
    protected void onResume() {
        super.onResume();

        surface.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(final SurfaceHolder holder) {
                // ready to start drawing. Launch ticker
                ticker = new Runnable() {
                    @Override
                    public void run() {
                        tick(holder);
                        mHandler.post(ticker);
                    }
                };
                mHandler.post(ticker);
            }
            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
            }
        });
    }

    private void tick(SurfaceHolder holder) {
        Canvas canvas = holder.lockCanvas();
        current.physTick(canvas);
        current.draw(canvas);
        holder.unlockCanvasAndPost(canvas);
    }

    @Override
    public void onPause() {
        super.onPause();
        mHandler.removeCallbacks(ticker);
    }

}
