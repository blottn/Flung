package nblott.org.scrolling;

import android.media.Image;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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


        screen = (ConstraintLayout) findViewById(R.id.screen);


        Block[] blocks = new Block[] {
                new Block(20,10,70),
                new Block(1500,10,70),
                new Block(1000,10,70),
        };

        current = new Level(blocks);
        inflateLevel(current);

    }

    @Override
    protected void onResume() {
        super.onResume();
        final Handler mHandler = new Handler();
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                tick();
                mHandler.post(this);
            }
        });
    }

    private void inflateLevel(Level level) {
        for (int i = 0 ; i < level.blockList.size() ;i++) {
            Block block = level.blockList.get(i);
            View root = getLayoutInflater().inflate(R.layout.block_layout, null);
            root.setX(block.getX());
            int y = block.getY();
            int h = block.getH();
            root.setId(level.ids.get(i));
            screen.addView(root, 0);
            ConstraintSet set = new ConstraintSet();
            set.clone(screen);
            set.connect(level.ids.get(i), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM);
            set.connect(level.ids.get(i), ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT);
//            set.constrainHeight(level.ids.get(i), ConstraintSet.WRAP_CONTENT);
//            set.constrainWidth(level.ids.get(i), ConstraintSet.WRAP_CONTENT);
            set.applyTo(screen);

            block.setView(root);
        }
    }

    private void slideLeft(Level level) {
        for (Block block : level.blockList) {
            block.getView().setX(block.getView().getX() - 2);
        }
    }

    private void tick() {
        slideLeft(current);
    }
}
