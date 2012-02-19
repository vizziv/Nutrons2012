package edu.neu.nutrons.reboundrumble;

import edu.neu.nutrons.reboundrumble.commands.elevator.ElevatorHopperCmd;
import edu.neu.nutrons.reboundrumble.commands.elevator.ElevatorShooterCmd;
import edu.neu.nutrons.reboundrumble.commands.elevator.ElevatorSpitCmd;
import edu.neu.nutrons.reboundrumble.commands.group.PrepareHoodAndShooterCmd;
import edu.neu.nutrons.reboundrumble.commands.hood.HoodSetPowerCmd;
import edu.neu.nutrons.reboundrumble.commands.shifter.ShifterStaticCmd;
import edu.neu.nutrons.reboundrumble.commands.shooter.ShooterDeltaPowerCmd;
import edu.neu.nutrons.reboundrumble.commands.shooter.ShooterSetPowerCmd;
import edu.neu.nutrons.reboundrumble.subsystems.Hood;
import edu.neu.nutrons.reboundrumble.subsystems.Shifter;
import edu.neu.nutrons.reboundrumble.subsystems.Shooter;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * Contains physical operator interface buttons and joysticks and links buttons
 * to commands.
 *
 * @author Ziv
 */
public class OI {

    // Constants.
    private final double CAM_JS_SCALE = .25;

    // Driver.
    private Joystick driverPad = new Joystick(RobotMap.PAD_DRIVER);
    private Button shift = new JoystickButton(driverPad, 5);

    // Operator.
    private Joystick opPad = new Joystick(RobotMap.PAD_OPERATOR);
    private Button shooterZero = new JoystickButton(opPad, 1);
    private Button shooterPlus = new JoystickButton(opPad, 4);
    private Button shooterMinus = new JoystickButton(opPad, 2);
    private Button elevShooterUp = new JoystickButton(opPad, 3);
    private Button elevSpitDown = new JoystickButton(opPad, 6);
    private Button elevHopperUp = new JoystickButton(opPad, 5);
    private Button hoodUp = new JoystickButton(opPad, 7);
    private Button hoodDown = new JoystickButton(opPad, 8);
    private Button prepareFender = new JoystickButton(opPad, 9);
    private Button prepareLong = new JoystickButton(opPad, 10);

    public OI(){
        // When shift is held, go into the non-default gear.
        // (We don't know which it will be, yet.)
        shift.whileHeld(new ShifterStaticCmd(!Shifter.DEFAULT));
        shooterZero.whenPressed(new ShooterSetPowerCmd(0));
        shooterPlus.whenPressed(new ShooterDeltaPowerCmd(Shooter.MANUAL_INC));
        shooterMinus.whenPressed(new ShooterDeltaPowerCmd(-Shooter.MANUAL_INC));
        elevShooterUp.whileHeld(new ElevatorShooterCmd(true));
        elevHopperUp.whileHeld(new ElevatorHopperCmd(true));
        elevSpitDown.whileHeld(new ElevatorSpitCmd(false));
        prepareFender.whenPressed(new PrepareHoodAndShooterCmd(Shooter.FENDER_POWER, Hood.FENDER_POS));
        prepareLong.whenPressed(new PrepareHoodAndShooterCmd(Shooter.LONG_POWER, Hood.LONG_POS));
        hoodUp.whileHeld(new HoodSetPowerCmd(Hood.MANUAL_POWER));
        hoodDown.whileHeld(new HoodSetPowerCmd(-Hood.MANUAL_POWER));
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

    // On opPad.

    public double getCamDelta() {
        return CAM_JS_SCALE * opPad.getRawAxis(3);
    }

    public boolean getHoodUp() {
        return opPad.getRawButton(7);
    }

    public boolean getHoodDown() {
        return opPad.getRawButton(8);
    }
}
