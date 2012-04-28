package edu.neu.nutrons.reboundrumble.subsystems;

import edu.neu.nutrons.lib.PulseTriggerBoolean;
import edu.neu.nutrons.reboundrumble.RobotMap;
import edu.neu.nutrons.reboundrumble.commands.elevator.ElevatorIdleCmd;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Lifts balls from intake to shooter and measures their squishiness.
 *
 * @author Ziv
 */
public class Elevator extends Subsystem {

    // Constants.
    private final double PRESSURE_SCALE = 1.0;
    public static final double F_SHOOTER_POWER = 0.45;
    public static final double B_SHOOTER_POWER = 0.925;
    public static final double F_HOPPER_POWER = 0.65;
    public static final double B_HOPPER_POWER = 0.925;
    public static final double F_SPIT_POWER = 0.925;
    public static final double B_SPIT_POWER = 0.65;
    public static final double F_SUPERINTAKE_POWER = 1.0;
    public static final double B_SUPERINTAKE_POWER = 0.925;
    public static final double MEASURING_POWER_SCALE = 0.2;
    public static final double SHOOTING_DELAY = 0.5;
    public static final double PRESSURE_THRESHOLD = 0.5;
    public static final double IDEAL_PRESSURE = 2.5;

    // Actual robot parts.
    private final Victor fMot = new Victor(RobotMap.F_ELEV_MOTOR);
    private final Victor bMot = new Victor(RobotMap.B_ELEV_MOTOR);
    private final AnalogChannel pressure = new AnalogChannel(RobotMap.SQUISHINESS);
    private final DigitalInput ballSwitch = new DigitalInput(RobotMap.BALL_SWITCH);
    private final PulseTriggerBoolean newBall = new PulseTriggerBoolean();

    // Other variables.
    private boolean squishEnabled = false;

    public void initDefaultCommand() {
        setDefaultCommand(new ElevatorIdleCmd());
    }

    public void processSensors() {
        newBall.feed(ballSwitch.get());
    }

    public double getPressure() {
        return PRESSURE_SCALE * pressure.getVoltage();
    }

    public boolean hasNewBall() {
        return newBall.get();
    }

    public void setPowerFB(double fPower, double bPower) {
        fMot.set(fPower);
        bMot.set(-bPower);
    }

    public void stop() {
        setPowerFB(0, 0);
    }

    public void setSquishEnabled(boolean squishEnabled) {
        this.squishEnabled = squishEnabled;
    }

    public boolean getSquishEnabled() {
        return squishEnabled;
    }
}
