package edu.neu.nutrons.reboundrumble.subsystems;

import edu.neu.nutrons.lib.*;
import edu.neu.nutrons.reboundrumble.RobotMap;
import edu.neu.nutrons.reboundrumble.commands.shooter.ShooterMaintainCmd;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 * Single-wheel shooter.
 *
 * @author Ziv
 */
public class Shooter extends PIDSubsystem {

    // TODO: tune PID.
    private static final double kp = 2.75E-6;
    private static final double ki = 0.0;
    private static final double kd = 2.95E-5;
    public static final double MANUAL_POWER_INC = 0.02;
    public static final double MANUAL_RATE_INC = 300.0;
    public static final double FENDER_POWER = 0.56;
    public static final double KEY_POWER = 0.94;
    public static final double FENDER_RATE = 9000.125;
    public static final double KEY_RATE = 16000.0;
    private final double ENC_SCALE = -1.0;
    public static final double RATE_SETTLE_TIMEOUT = 2.0;
    private final int ENC_AVG_LENGTH = 10;
    private final int POWER_AVG_LENGTH = 15;
    private final int SETPOINT_DEBOUNCE_LENGTH = 20;
    private final double MAX_BACKWARD_POWER = 0.2;
    private final double TOLERANCE = 100;

    // Actual robot parts.
    private final SpeedController mot1 = new Jaguar(RobotMap.SHOOTER_MOTOR_1);
    private final SpeedController mot2 = new Jaguar(RobotMap.SHOOTER_MOTOR_2);
    private final Encoder enc = new Encoder(RobotMap.SHOOTER_ENC_A, RobotMap.SHOOTER_ENC_B);

    // Other variables.
    private double power = 0;
    private boolean enabled = false;
    private MovingAverage powerAvg = new MovingAverage(POWER_AVG_LENGTH);
    private ComposedFilter dEncAvg = new ComposedFilter(new MovingAverage(ENC_AVG_LENGTH),
                                                        new DerivativeTimed());
    private DebouncedBoolean dbAtSetpoint = new DebouncedBoolean(SETPOINT_DEBOUNCE_LENGTH);

    public Shooter() {
        super(kp, ki, kd);
        enc.start();
    }

    public void initDefaultCommand() {
        disable();
        setDefaultCommand(new ShooterMaintainCmd());
    }

    public void processSensors() {
        dEncAvg.feed(ENC_SCALE * enc.get());
        dbAtSetpoint.feed(Math.abs(getRate() - getSetpoint()) < TOLERANCE);
    }

    public void setPower(double power) {
        // Moving average stops sudden changes in motor speed, which in turn
        // (hopefully) stops the shooter from breaking or catching fire.
        this.power = Utils.limit(power, -MAX_BACKWARD_POWER, 1.0);
        powerAvg.feed(this.power);
        mot1.set(-powerAvg.get());
        mot2.set(-powerAvg.get());
    }

    public double getPower() {
        return power;
    }

    public double getRate() {
        return dEncAvg.get();
    }

    public boolean atSetpoint() {
        return dbAtSetpoint.get();
    }

    public void enable() {
        enabled = true;
        super.enable();
    }

    public void disable() {
        enabled = false;
        super.disable();
    }

    public boolean isEnabled() {
        return enabled;
    }

    protected double returnPIDInput() {
        return getRate();
    }

    protected void usePIDOutput(double output) {
        setPower(getPower() + output);
    }
}
