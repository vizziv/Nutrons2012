package edu.neu.nutrons.reboundrumble;

import edu.neu.nutrons.lib.DualButton;
import edu.neu.nutrons.lib.ReverseButton;
import edu.neu.nutrons.lib.Utils;
import edu.neu.nutrons.reboundrumble.commands.CommandBase;
import edu.neu.nutrons.reboundrumble.commands.drivetrain.DTManualCheesyCmd;
import edu.neu.nutrons.reboundrumble.commands.drivetrain.DTManualCreepToTargetCmd;
import edu.neu.nutrons.reboundrumble.commands.elevator.ElevatorHopperCmd;
import edu.neu.nutrons.reboundrumble.commands.elevator.ElevatorShooterCmd;
import edu.neu.nutrons.reboundrumble.commands.elevator.ElevatorShooterWhenReadyCmd;
import edu.neu.nutrons.reboundrumble.commands.elevator.ElevatorSpitCmd;
import edu.neu.nutrons.reboundrumble.commands.intake.IntakeSetCmd;
import edu.neu.nutrons.reboundrumble.commands.shifter.ShifterStaticCmd;
import edu.neu.nutrons.reboundrumble.commands.shooter.ShooterDeltaPowerCmd;
import edu.neu.nutrons.reboundrumble.commands.shooter.ShooterDeltaRateCmd;
import edu.neu.nutrons.reboundrumble.commands.shooter.ShooterSetPowerCmd;
import edu.neu.nutrons.reboundrumble.subsystems.Shifter;
import edu.neu.nutrons.reboundrumble.subsystems.Shooter;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStationEnhancedIO;
import edu.wpi.first.wpilibj.DriverStationEnhancedIO.EnhancedIOException;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.DigitalIOButton;
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
    private DriverStationEnhancedIO io = DriverStation.getInstance().getEnhancedIO();
    private Button shift = new ReverseButton(new DigitalIOButton(1));
    private Button manOverride = new ReverseButton(new DigitalIOButton(1));

    // Operator.
    private Joystick opPad = new Joystick(RobotMap.PAD_OPERATOR);
    private Button manualMode = new JoystickButton(opPad, 9);
    private Button prepareFender = new JoystickButton(opPad, 4);
    private DualButton prepareFenderMan = new DualButton(prepareFender, manualMode);
    private Button prepareFenderAuto = prepareFenderMan.button1;
    private Button prepareKey = new JoystickButton(opPad, 3);
    private DualButton prepareKeyMan = new DualButton(prepareKey, manualMode);
    private Button prepareKeyAuto = prepareKeyMan.button1;
    private Button shooterPlus = new JoystickButton(opPad, 1);
    private Button shooterMinus = new JoystickButton(opPad, 2);
    private DualButton shooterPlusMan = new DualButton(shooterPlus, manualMode);
    private DualButton shooterMinusMan = new DualButton(shooterMinus, manualMode);
    private Button shooterPlusAuto = shooterPlusMan.button1;
    private Button shooterMinusAuto = shooterMinusMan.button1;
    private Button shooterZero = new JoystickButton(opPad, 10);
    private Button elevHopper = new JoystickButton(opPad, 5);
    private DualButton intakeDrop = new DualButton(new JoystickButton(opPad, 7), elevHopper);
    private Button elevShoot = new JoystickButton(opPad, 6);
    private DualButton elevShootMan = new DualButton(elevShoot, manualMode);
    private Button elevShootAuto = elevShootMan.button1;
    private Button elevSpit = new JoystickButton(opPad, 8);
    public final AutoModeSelector ams = new AutoModeSelector(opPad);
    private Button autoAim = new JoystickButton(opPad, 11);

    public OI(){
        // Driver.
        // When shift is held, go into the non-default gear.
        shift.whileHeld(new ShifterStaticCmd(!Shifter.DEFAULT));
        //driverIntake.whileHeld(new IntakeSetCmd(true, true));
        manOverride.whenPressed(new StartCommand(new DTManualCheesyCmd()));

        // Operator.
        autoAim.whileHeld(new DTManualCreepToTargetCmd());
        prepareFenderAuto.whenPressed(CommandBase.prepareFenderCmd());
        prepareFenderMan.whenPressed(CommandBase.prepareFenderCmd(true));
        prepareFender.whileHeld(new ElevatorHopperCmd());
        prepareKeyAuto.whenPressed(CommandBase.prepareKeyCmd());
        prepareKeyMan.whenPressed(CommandBase.prepareKeyCmd(true));
        prepareKey.whileHeld(new ElevatorHopperCmd());
        shooterPlusAuto.whenPressed(new StartCommand(new ShooterDeltaRateCmd(Shooter.MANUAL_RATE_INC)));
        shooterMinusAuto.whenPressed(new StartCommand(new ShooterDeltaRateCmd(-Shooter.MANUAL_RATE_INC)));
        shooterPlusMan.whenPressed(new StartCommand(new ShooterDeltaPowerCmd(Shooter.MANUAL_POWER_INC)));
        shooterMinusMan.whenPressed(new StartCommand(new ShooterDeltaPowerCmd(-Shooter.MANUAL_POWER_INC)));
        shooterZero.whenPressed(new ShooterSetPowerCmd(0));
        elevHopper.whileHeld(new ElevatorHopperCmd());
        // While we suck balls into the hopper, run the front intake whenever we
        // drop it down.
        // Otherwise, don't run the front intake when we drop it down.
        intakeDrop.button1.whileHeld(new IntakeSetCmd(true, false));
        intakeDrop.whileHeld(new IntakeSetCmd(true, true));
        elevShootAuto.whileHeld(new ElevatorShooterWhenReadyCmd());
        elevShootMan.whileHeld(new ElevatorShooterCmd());
        elevSpit.whileHeld(new ElevatorSpitCmd());
    }

    private double capAndBand(double value) {
        value = Utils.deadband(value, .075, -1);
        value = Utils.deadband(value, .075, 0);
        value = Utils.deadband(value, .075, 1);
        return Utils.limit(value, -1, 1);
    }

    private double scaleAnalog(double voltageIn) {
        double normalized = (2 * voltageIn / 3.25) - 1;
        return normalized;
    }

    private double getIOAnalog(int port) {
        double in;
        try {
            in = io.getAnalogIn(port);
        }
        catch(EnhancedIOException ex) {
            return 0;
        }
        double refined = capAndBand(scaleAnalog(in));
        return refined;
    }

    private boolean getIODigital(int port) {
        boolean in = false;
        try {
            in = !io.getDigital(port); //active low
        }
        catch(EnhancedIOException ex) {
        }
        return in;
    }

    // On driverPad.
    public double getDriveLeft() {
        return -Utils.deadband(driverPad.getRawAxis(2), PAD_DEADBAND, 0);
    }

    public double getDriveRight() {
        return -Utils.deadband(driverPad.getRawAxis(4), PAD_DEADBAND, 0);
    }

    public double getDriveThrottle() {
        //return -Utils.deadband(driverPad.getRawAxis(2), PAD_DEADBAND, 0);
        return getIOAnalog(1);
    }

    public double getDriveWheel() {
        //return Utils.deadband(driverPad.getRawAxis(3), PAD_DEADBAND, 0);
        return getIOAnalog(5);
    }

    public boolean getDriveQuickTurn() {
        //return driverPad.getRawButton(6);
        return getIODigital(3);
    }

    // On opPad.
    public double getCamDelta() {
        return CAM_JS_SCALE * Utils.deadband(-opPad.getRawAxis(4), 2*PAD_DEADBAND, 0);
    }
}
