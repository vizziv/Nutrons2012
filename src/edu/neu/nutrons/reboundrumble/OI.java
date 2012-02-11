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
    private final double HOOD_POWER = .5;

    private Joystick driverPad = new Joystick(RobotMap.PAD_DRIVER);
    private Joystick opPad = new Joystick(RobotMap.PAD_OPERATOR);
    private JoystickButton shooterZero = new JoystickButton(driverPad, 1);
    private JoystickButton shooterPlus = new JoystickButton(driverPad, 4);
    private JoystickButton shooterMinus = new JoystickButton(driverPad, 2);

    public OI(){
        shooterZero.whenPressed(new ShooterSetPowerCmd(0));
        shooterPlus.whenPressed(new ShooterDeltaPowerCmd(SHOOTER_INC));
        shooterMinus.whenPressed(new ShooterDeltaPowerCmd(-SHOOTER_INC));
    }

    public double getDriveLeft() {
        return -driverPad.getRawAxis(2);
    }

    public double getDriveRight() {
        return -driverPad.getRawAxis(4);
    }

    public double getDriveThrottle() {
        return -driverPad.getRawAxis(2);
    }

    public double getDriveWheel() {
        return driverPad.getRawAxis(3);
    }

    public boolean getDriveQuickTurn() {
        return driverPad.getRawButton(6);
    }

    public boolean getDriveShift() {
        return driverPad.getRawButton(5);
    }

    public double getElevFront() {
        return -opPad.getRawAxis(2);
    }

    public double getElevBack() {
        return -opPad.getRawAxis(4);
    }

    public double getHoodPower(){
        double up = opPad.getRawButton(8) ? HOOD_POWER : 0;
        double down = opPad.getRawButton(7) ? -HOOD_POWER : 0;
        return up + down;
    }
}
