package edu.neu.nutrons.lib;

/**
 * Boolean that returns true only after a string of consecutive trues.
 *
 * @author Ziv
 */
public class DebouncedBoolean {

    private int count = 0;
    private int length = 0;
    private boolean state = false;

    public DebouncedBoolean(int length) {
        this.length = length;
    }

    public void feed(boolean in) {
        if(state) {
            state = in && state;
        }
        else if(in) {
            count += 1;
            if(count >= length) {
                state = true;
            }
        }
        if(!in) {
            count = 0;
        }
    }

    public boolean get() {
        return state;
    }
}
