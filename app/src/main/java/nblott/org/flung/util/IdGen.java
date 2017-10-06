package nblott.org.flung.util;

/**
 * Created by Nick on 25/09/2017.
 */

public class IdGen {
    private static int next = 0;
    public static int getNext() {
        return next++;
    }
}
