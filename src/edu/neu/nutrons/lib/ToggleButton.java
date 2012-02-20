/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.nutrons.lib;

import edu.wpi.first.wpilibj.buttons.Button;

/**
 *
 * @author Ziv
 */
public class ToggleButton extends Button {

    private Button btn;
    private PulseTriggerBoolean filter = new PulseTriggerBoolean();
    private boolean state;

    public ToggleButton(Button button) {
        btn = button;
    }
    public boolean get() {
        filter.feed(btn.get());
        if(filter.get()) {
            state = !state;
        }
        return state;
    }
}
