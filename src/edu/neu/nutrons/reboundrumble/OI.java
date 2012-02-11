package edu.neu.nutrons.reboundrumble;

import edu.neu.nutrons.reboundrumble.commands.shooter.ShooterDeltaPowerCmd;
import edu.neu.nutrons.reboundrumble.commands.shooter.ShooterSetPowerCmd;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 *
 * @author Ziv
 */
public class OI {

    private final double SHOOTER_INC = .1;

    private Joystick pad = new Joystick(RobotMap.PAD);
    private JoystickButton shooterZero = new JoystickButton(pad, 1);
    private JoystickButton shooterPlus = new JoystickButton(pad, 4);
    private JoystickButton shooterMinus = new JoystickButton(pad, 2);

    public OI(){
        shooterZero.whenPressed(new ShooterSetPowerCmd(0));
        shooterPlus.whenPressed(new ShooterDeltaPowerCmd(SHOOTER_INC));
        shooterMinus.whenPressed(new ShooterDeltaPowerCmd(-SHOOTER_INC));
    }

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
