package edu.neu.nutrons.lib;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;

/**
 * Easy access to directional pad on a joystick. Note that the direction N will
 * trigger on directions NW, N and NE; the same is true of E, S and W.
 *
 * @author Ziv
 */
public class JoystickDPadButton extends Button {

    private final double THRESHOLD = .5;

    private Joystick js;
    private Direction dir;
    private double axisLR = 5;
    private double axisUD = 6;

    public JoystickDPadButton(Joystick js, int axisLR, int axisUD, Direction dir) {
        this.js = js;
        this.axisLR = axisLR;
        this.axisUD = axisUD;
        this.dir = dir;
    }

    public JoystickDPadButton(Joystick js, Direction dir) {
        this(js, 5, 6, dir);
    }

    public boolean get() {
        boolean result = true;
        if(dir.leftRight != 0) {
            result = result && dir.leftRight * js.getRawAxis(5) > THRESHOLD;
        }
        if(dir.upDown != 0) {
            result = result && -dir.upDown * js.getRawAxis(6) > THRESHOLD;
        }
        return result;
    }

    public static final class Direction {
        public final int leftRight;
        public final int upDown;
        private Direction(int leftRight, int upDown) {
            this.leftRight = leftRight;
            this.upDown = upDown;
        }
        public static final Direction E = new Direction(1,0);
        public static final Direction NE = new Direction(1,1);
        public static final Direction N = new Direction(0,1);
        public static final Direction NW = new Direction(-1,1);
        public static final Direction W = new Direction(-1,0);
        public static final Direction SW = new Direction(-1,-1);
        public static final Direction S = new Direction(0,-1);
        public static final Direction SE = new Direction(1,-1);
    }
}
