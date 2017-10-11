package nblott.org.flung.view;

import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import nblott.org.flung.R;
import nblott.org.flung.model.Block;
import nblott.org.flung.model.Level;

/**
 * Created by Nick on 04/10/2017.
 */

public class GameActivity extends AppCompatActivity {

    public static final double FLING_SCALE_FACTOR = 0.005;

    private GestureDetectorCompat mDetector;

    final Handler mHandler = new Handler();
    SurfaceView surface;
    ConstraintLayout screen;
    Level current;

    Runnable ticker;

    private static long DEBUG_TIME = 0;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        mDetector = new GestureDetectorCompat(this, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }

            @Override
            public void onShowPress(MotionEvent e) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {

            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                velocityY = -velocityY;
                velocityY *= FLING_SCALE_FACTOR;
                velocityX *= FLING_SCALE_FACTOR;
                current.fling(velocityX, velocityY);
                return true;
            }
        });
        surface = (SurfaceView) findViewById(R.id.surface);
        current = new Level(ContextCompat.getColor(this, R.color.levelBG),getResources().getDrawable(R.drawable.player, null), new Block[]{new Block(375,0,50, null)});
        surface.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mDetector.onTouchEvent(event);
                return true;
            }
        });
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
                        mHandler.postDelayed(ticker, DEBUG_TIME);
                    }
                };
                mHandler.postDelayed(ticker, DEBUG_TIME);
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
