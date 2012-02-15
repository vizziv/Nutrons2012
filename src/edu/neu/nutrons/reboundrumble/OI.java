package edu.neu.nutrons.reboundrumble;

import edu.neu.nutrons.reboundrumble.commands.elevator.ElevatorShooterCmd;
import edu.neu.nutrons.reboundrumble.commands.shooter.ShooterDeltaPowerCmd;
import edu.neu.nutrons.reboundrumble.commands.shooter.ShooterSetPowerCmd;
import edu.neu.nutrons.reboundrumble.subsystems.Shooter;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * Contains physical operator interface buttons and joysticks and links buttons
 * to commands.
 *
 * @author Ziv
 */
public class OI {

    private Joystick driverPad = new Joystick(RobotMap.PAD_DRIVER);
    private Joystick opPad = new Joystick(RobotMap.PAD_OPERATOR);
    private JoystickButton shooterZero = new JoystickButton(opPad, 1);
    private JoystickButton shooterPlus = new JoystickButton(opPad, 2);
    private JoystickButton shooterMinus = new JoystickButton(opPad, 4);
    private JoystickButton elevShooterUp = new JoystickButton(opPad, 3);
    private JoystickButton elevShooterDown = new JoystickButton(opPad, 6);
    private JoystickButton elevHopperUp = new JoystickButton(opPad, 5);
    private JoystickButton elevHopperDown = new JoystickButton(opPad, 11);
    // TODO: add buttons for hood commands.

    public OI(){
        shooterZero.whenPressed(new ShooterSetPowerCmd(0));
        shooterPlus.whenPressed(new ShooterDeltaPowerCmd(Shooter.MANUAL_INC));
        shooterMinus.whenPressed(new ShooterDeltaPowerCmd(-Shooter.MANUAL_INC));
        elevShooterUp.whileHeld(new ElevatorShooterCmd(true));
        elevShooterDown.whileHeld(new ElevatorShooterCmd(false));
        elevHopperUp.whileHeld(new ElevatorShooterCmd(true));
        elevHopperDown.whileHeld(new ElevatorShooterCmd(false));
    }

    // On driverPad.
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

    // On opPad.
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

    // These get assigned to various functions when we need to debug them.
    public boolean getDebug1() {
        return opPad.getRawButton(9);
    }

    public boolean getDebug2() {
        return opPad.getRawButton(10);
    }

    // We should never need these. They'll probably get removed at some point.
    public boolean getElevHopper() {
        return opPad.getRawButton(5);
    }

    public boolean getElevHopperRev() {
        return opPad.getRawButton(11);
    }

    public boolean getElevShooter() {
        return opPad.getRawButton(3);
    }

    public boolean getElevShooterRev() {
        return opPad.getRawButton(6);
    }
}
