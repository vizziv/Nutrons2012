package edu.neu.nutrons.lib.fpop.sources;

import edu.neu.nutrons.lib.fpop.Block;

/**
 * Keeps track of how many times handle has been called.
 *
 * @author Ziv
 */
public class Counter extends Block {

    private int i = 0;

    public Counter(int delay) {
        i = -delay;
    }

    public void handle() {
        i++;
    }

    public double get() {
        return Math.max(i, 0);
    }
}
