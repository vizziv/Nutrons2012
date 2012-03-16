package edu.neu.nutrons.reboundrumble;

import edu.neu.nutrons.lib.DualButton;
import edu.neu.nutrons.lib.JoystickDPadButton;
import edu.neu.nutrons.lib.JoystickDPadButton.Direction;
import edu.neu.nutrons.lib.StartThenWaitCmd;
import edu.neu.nutrons.lib.Utils;
import edu.neu.nutrons.reboundrumble.commands.CommandBase;
import edu.neu.nutrons.reboundrumble.commands.drivetrain.DTManualCreepToTargetCmd;
import edu.neu.nutrons.reboundrumble.commands.elevator.ElevatorHopperCmd;
import edu.neu.nutrons.reboundrumble.commands.elevator.ElevatorShootOneBallCmd;
import edu.neu.nutrons.reboundrumble.commands.elevator.ElevatorSpitCmd;
import edu.neu.nutrons.reboundrumble.commands.hood.HoodSetPosCmd;
import edu.neu.nutrons.reboundrumble.commands.intake.IntakeSetCmd;
import edu.neu.nutrons.reboundrumble.commands.shifter.ShifterStaticCmd;
import edu.neu.nutrons.reboundrumble.commands.shooter.ShooterDeltaRateCmd;
import edu.neu.nutrons.reboundrumble.commands.shooter.ShooterSetPowerCmd;
import edu.neu.nutrons.reboundrumble.subsystems.Elevator;
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
    private final double CAM_JS_SCALE = 0.125;
    private final double PAD_DEADBAND = 0.1;

    // Driver.
    private Joystick driverPad = new Joystick(RobotMap.PAD_DRIVER);
    private Button shift = new JoystickButton(driverPad, 5);
    private Button driverIntake = new JoystickButton(driverPad, 7);
    private Button autoAim = new JoystickButton(driverPad, 8);

    // Operator.
    private Joystick opPad = new Joystick(RobotMap.PAD_OPERATOR);
    private Button prepareFender = new JoystickButton(opPad, 4);
    private Button prepareKey = new JoystickButton(opPad, 3);
    private Button shooterPlus = new JoystickButton(opPad, 1);
    private Button shooterMinus = new JoystickButton(opPad, 2);
    private Button shooterZero = new JoystickButton(opPad, 10);
    private Button shooterManPlus = new JoystickDPadButton(opPad, Direction.N);
    private Button shooterManMinus = new JoystickDPadButton(opPad, Direction.S);
    private Button elevHopper = new JoystickButton(opPad, 6);
    private DualButton intakeDrop = new DualButton(new JoystickButton(opPad, 5), elevHopper);
    private Button elevShoot = new JoystickButton(opPad, 8);
    private Button elevSpit = new JoystickButton(opPad, 7);
    private Button hoodUp = new JoystickDPadButton(opPad, 1, 2, Direction.N);
    private Button hoodDown = new JoystickDPadButton(opPad, 1, 2, Direction.S);
    public final AutoModeSelector ams = new AutoModeSelector(opPad);

    public OI(){

        // Driver.
        // When shift is held, go into the non-default gear.
        shift.whileHeld(new ShifterStaticCmd(!Shifter.DEFAULT));
        driverIntake.whileHeld(new IntakeSetCmd(true, true));
        autoAim.whileHeld(new DTManualCreepToTargetCmd());

        // Operator.
        prepareFender.whenPressed(CommandBase.prepareFender);
        prepareKey.whenPressed(CommandBase.prepareKey);
        shooterPlus.whenPressed(new StartCommand(new ShooterDeltaRateCmd(Shooter.MANUAL_RATE_INC)));
        shooterMinus.whenPressed(new StartCommand(new ShooterDeltaRateCmd(-Shooter.MANUAL_RATE_INC)));
        shooterZero.whenPressed(new StartCommand(new ShooterSetPowerCmd(0)));
        elevHopper.whileHeld(new ElevatorHopperCmd());
        // While we suck balls into the hopper, run the front intake whenever we
        // drop it down.
        // Otherwise, don't run the front intake when we drop it down.
        intakeDrop.button1.whileHeld(new IntakeSetCmd(true, false));
        intakeDrop.whileHeld(new IntakeSetCmd(true, true));
        elevShoot.whileHeld(new StartThenWaitCmd(new ElevatorShootOneBallCmd(), Elevator.SHOOTING_DELAY));
        elevSpit.whileHeld(new ElevatorSpitCmd());
        hoodUp.whenPressed(new HoodSetPosCmd(true));
        hoodDown.whenPressed(new HoodSetPosCmd(false));
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
        return CAM_JS_SCALE * Utils.deadband(-opPad.getRawAxis(3), 3*PAD_DEADBAND, 0);
    }
}
