package nblott.org.scrolling.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nick on 13/09/2017.
 */

public class Level {

    public List<Block> blockList;
    public List<Integer> ids;

    public Level(Block... blocks) {
        blockList = new ArrayList<>();
        ids = new ArrayList<>();
        for (Block block : blocks) {
            blockList.add(block);
            ids.add(IdGen.getNext());
        }
    }

}
