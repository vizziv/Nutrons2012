package edu.neu.nutrons.reboundrumble;

import edu.wpi.first.wpilibj.Joystick;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 *
 * @author Ziv
 */
public class OI {
    private Joystick pad = new Joystick(RobotMap.PAD); 
 
    public double getDriveLeft() {
        return -pad.getRawAxis(2);
    }

    public double getDriveRight() {     
        return -pad.getRawAxis(4);
    }

    public double getDriveThrottle() {
        return -pad.getRawAxis(2);
    }

    public double getDriveWheel() {
        return pad.getRawAxis(3);
    }

    public boolean getDriveQuickTurn() {
        return pad.getRawButton(6);
    }
}
