package nblott.org.scrolling;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.Image;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;

import nblott.org.scrolling.data.Block;
import nblott.org.scrolling.data.Level;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout screen;
    Level current;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SurfaceView surface = (SurfaceView) findViewById(R.id.surface);
        //Hardcoded debugging level
        current = new Level(ContextCompat.getColor(this, R.color.levelBG),getResources().getDrawable(R.drawable.player, null), new Block[]{new Block(500,0,100,100)});

        surface.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(final SurfaceHolder holder) {
                // ready to start drawing. Launch ticker
                final Handler mHandler = new Handler();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        tick(holder);
                        mHandler.post(this);
                    }
                });
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
        current.draw(canvas);
        holder.unlockCanvasAndPost(canvas);
    }
}
