package edu.neu.nutrons.reboundrumble;

import edu.neu.nutrons.lib.ToggleButton;
import edu.neu.nutrons.lib.Utils;
import edu.neu.nutrons.reboundrumble.commands.camera.CamPointAtTargetCmd;
import edu.neu.nutrons.reboundrumble.commands.drivetrain.DTManualCreepToTargetCmd;
import edu.neu.nutrons.reboundrumble.commands.drivetrain.DTSpinToTargetWithCamServoCmd;
import edu.neu.nutrons.reboundrumble.commands.drivetrain.DTTurnToTargetCmd;
import edu.neu.nutrons.reboundrumble.commands.elevator.ElevatorHopperCmd;
import edu.neu.nutrons.reboundrumble.commands.elevator.ElevatorShooterCmd;
import edu.neu.nutrons.reboundrumble.commands.elevator.ElevatorSpitCmd;
import edu.neu.nutrons.reboundrumble.commands.group.PrepareHoodAndShooterCmd;
import edu.neu.nutrons.reboundrumble.commands.hood.HoodSetPowerCmd;
import edu.neu.nutrons.reboundrumble.commands.shifter.ShifterStaticCmd;
import edu.neu.nutrons.reboundrumble.commands.shooter.ShooterDeltaPowerCmd;
import edu.neu.nutrons.reboundrumble.commands.shooter.ShooterSetPowerCmd;
import edu.neu.nutrons.reboundrumble.commands.shooter.ShooterSetRateCmd;
import edu.neu.nutrons.reboundrumble.subsystems.Hood;
import edu.neu.nutrons.reboundrumble.subsystems.Shifter;
import edu.neu.nutrons.reboundrumble.subsystems.Shooter;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.StartCommand;

/**
 * Contains physical operator interface buttons and joysticks and links buttons
 * to commands.
 *
 * @author Ziv
 */
public class OI {

    // Constants.
    private final double CAM_JS_SCALE = 0.25;
    private final double PAD_DEADBAND = 0.1;

    // Driver.
    private Joystick driverPad = new Joystick(RobotMap.PAD_DRIVER);
    private Button shift = new JoystickButton(driverPad, 5);
    private Button dtToTargetLeft = new JoystickButton(driverPad, 7);
    private Button dtToTargetRight = new JoystickButton(driverPad, 8);

    // Operator.
    private Joystick opPad = new Joystick(RobotMap.PAD_OPERATOR);
    private Button shooterZero = new JoystickButton(opPad, 1);
    private Button shooterPlus = new JoystickButton(opPad, 4);
    private Button shooterMinus = new JoystickButton(opPad, 2);
    private Button shooterPID = new JoystickButton(opPad, 3);
    //private Button elevShooterUp = new JoystickButton(opPad, 3);
    private Button elevShooterUp = new JoystickButton(opPad, 3);
    //private Button shooterPID = new JoystickButton(opPad, 3);
    private Button elevSpitDown = new JoystickButton(opPad, 6);
    private Button elevHopperUp = new JoystickButton(opPad, 5);
    private Button hoodUp = new JoystickButton(opPad, 7);
    private Button hoodDown = new JoystickButton(opPad, 8);
    private Button prepareFender = new JoystickButton(opPad, 9);
    private Button prepareLong = new JoystickButton(opPad, 10);
    private Button cameraTrack = new ToggleButton(new JoystickButton(opPad, 11));
    private Button dtToTargetSpin = new JoystickButton(opPad, 12);

    public OI(){
        // When shift is held, go into the non-default gear.
        // (We don't know which it will be, yet.)
        shift.whileHeld(new ShifterStaticCmd(!Shifter.DEFAULT));
        shooterZero.whenPressed(new StartCommand(new ShooterSetPowerCmd(Shooter.MANUAL_INC)));
        shooterPlus.whenPressed(new StartCommand(new ShooterDeltaPowerCmd(Shooter.MANUAL_INC)));
        shooterMinus.whenPressed(new StartCommand(new ShooterDeltaPowerCmd(Shooter.MANUAL_INC)));
        shooterPID.whenPressed(new ShooterSetRateCmd(0));
        //elevShooterUp.whileHeld(new ElevatorShooterCmd(true));
        //hopForward.whenPressed(new DTDriveDistanceCmd(3));
        //hopBackward.whenPressed(new DTDriveDistanceCmd(-3));
        shooterZero.whenPressed(new ShooterSetPowerCmd(0));
        shooterPlus.whenPressed(new ShooterDeltaPowerCmd(Shooter.MANUAL_RATE_INC));
        shooterMinus.whenPressed(new ShooterDeltaPowerCmd(-Shooter.MANUAL_RATE_INC));
        elevShooterUp.whileHeld(new ElevatorShooterCmd(true));
        //shooterPID.whenPressed(new ShooterSetRateCmd(9000.125));
        elevHopperUp.whileHeld(new ElevatorHopperCmd(true));
        elevSpitDown.whileHeld(new ElevatorSpitCmd(false));
        hoodUp.whileHeld(new HoodSetPowerCmd(Hood.MANUAL_POWER));
        hoodDown.whileHeld(new HoodSetPowerCmd(-Hood.MANUAL_POWER));
        dtToTargetLeft.whileHeld(new DTTurnToTargetCmd(false));
        dtToTargetRight.whileHeld(new DTManualCreepToTargetCmd());
        prepareFender.whenPressed(new PrepareHoodAndShooterCmd(Shooter.FENDER_POWER, Hood.FENDER_POS));
        prepareLong.whenPressed(new PrepareHoodAndShooterCmd(Shooter.LONG_POWER, Hood.LONG_POS));
        cameraTrack.whileHeld(new CamPointAtTargetCmd());
        dtToTargetSpin.whileHeld(new DTSpinToTargetWithCamServoCmd());
    }

    // On driverPad.
    public double getDriveLeft() {
        return -Utils.deadband(driverPad.getRawAxis(2), PAD_DEADBAND, 0);
    }

    public double getDriveRight() {
        return -Utils.deadband(driverPad.getRawAxis(4), PAD_DEADBAND, 0);
    }

    public double getDriveThrottle() {
        return -Utils.deadband(driverPad.getRawAxis(2), PAD_DEADBAND, 0);
    }

    public double getDriveWheel() {
        return Utils.deadband(driverPad.getRawAxis(3), PAD_DEADBAND, 0);
    }

    public boolean getDriveQuickTurn() {
        return driverPad.getRawButton(6);
    }

    public boolean getDriveManual() {
        return driverPad.getRawButton(10);
    }

    // On opPad.
    public double getCamDelta() {
        return CAM_JS_SCALE * Utils.deadband(opPad.getRawAxis(1), PAD_DEADBAND, 0);
    }

    public boolean getHoodUp() {
        return opPad.getRawButton(7);
    }

    public boolean getHoodDown() {
        return opPad.getRawButton(8);
    }
}
