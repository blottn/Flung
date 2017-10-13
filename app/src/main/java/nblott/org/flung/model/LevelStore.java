package nblott.org.flung.model;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import java.util.HashMap;
import java.util.NoSuchElementException;

import nblott.org.flung.R;

/**
 * Created by Nick on 11/10/2017.
 */

public class LevelStore {

    private HashMap<String, Level> levels;

    private Level current;

    public LevelStore(Context context) {
        levels = new HashMap<>();
        init(context);
    }

    public void init(Context context) {
        //insert levels here.
        Level debugLevel = new Level(ContextCompat.getColor(context, R.color.levelBG),context.getResources().getDrawable(R.drawable.player, null), new Block[]{new Block(575,500,50, null)});
        levels.put("DEBUG",debugLevel);
    }

    public Level getLevel(String name) {
        if (levels.containsKey(name)) {
            return levels.get(name);
        }
        else {
            throw new NoSuchLevelException(name);
        }
    }


    public class NoSuchLevelException extends NoSuchElementException {
        String name;
        public NoSuchLevelException(String level) {
            name = level;
        }
    }

}
