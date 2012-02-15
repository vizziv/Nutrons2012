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

    private final double SHOOTER_INC = 0.1;

    private Joystick driverPad = new Joystick(RobotMap.PAD_DRIVER);
    private Joystick opPad = new Joystick(RobotMap.PAD_OPERATOR);
    private JoystickButton shooterZero = new JoystickButton(opPad, 1);
    private JoystickButton shooterPlus = new JoystickButton(opPad, 2);
    private JoystickButton shooterMinus = new JoystickButton(opPad, 4);

    public OI(){
        shooterZero.whenPressed(new ShooterSetPowerCmd(0));
        shooterPlus.whenPressed(new ShooterDeltaPowerCmd(SHOOTER_INC));
        shooterMinus.whenPressed(new ShooterDeltaPowerCmd(-SHOOTER_INC));
    }

    //driverPad
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

    //opPad
    public double getElevFront() {
        return -opPad.getRawAxis(2);
    }

    public double getElevBack() {
        return -opPad.getRawAxis(4);
    }

    public boolean getHoodUp() {
        return opPad.getRawButton(7);
    }

    public boolean getHoodDown() {
        return opPad.getRawButton(8);
    }

    public boolean getDebug1() {
        return opPad.getRawButton(9);
    }

    public boolean getDebug2() {
        return opPad.getRawButton(10);
    }

    public boolean getElevHopper(){
        return opPad.getRawButton(5);
    }

    public boolean getElevHopperRev(){
        return opPad.getRawButton(11);
    }
    
    public boolean getElevShooter(){
        return opPad.getRawButton(3);
    }

    public boolean getElevShooterRev(){
        return opPad.getRawButton(6);
    }
}