package edu.neu.nutrons.lib;

/**
 * Boolean substitute that returns true only when going from false to true.
 *
 * @author Nutrons Pros
 */
public class PulseTriggerBoolean {

    private boolean state = false;
    private boolean oldIn = false;

    public void feed(boolean in) {
        if(oldIn == false && in == true) {
            state = true;
        }
        else {
            state = false;
        }
        oldIn = in;
    }

    public boolean get() {
        return state;
    }
}
