package edu.neu.nutrons.lib;

import edu.wpi.first.wpilibj.buttons.Button;

/**
 *
 * @author Ziv
 */
public class DualButton extends Button {

    private Button btn1;
    private Button btn2;
    public Button button1;
    public Button button2;

    public DualButton(Button btn1, Button btn2) {
        this.btn1 = btn1;
        this.btn2 = btn2;
        button1 = new HalfDualButton(btn1, btn2);
        button2 = new HalfDualButton(btn2, btn1);
    }

    public boolean get() {
        return btn1.get() && btn2.get();
    }

    public class HalfDualButton extends Button {
        private Button btn1;
        private Button btn2;
        public HalfDualButton(Button btn1, Button btn2) {
            this.btn1 = btn1;
            this.btn2 = btn2;
        }
        public boolean get() {
            return btn1.get() && !btn2.get();
        }
    }
}
