package edu.neu.nutrons.reboundrumble.subsystems;

import edu.neu.nutrons.lib.*;
import edu.neu.nutrons.reboundrumble.RobotMap;
import edu.neu.nutrons.reboundrumble.commands.shooter.ShooterSetPowerCmd;
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

    public static final double MANUAL_INC = 0.02;
    public static final double RAMP_INC = 0.002;
    // TODO: tune PID.
    private static final double kp = 2.75E-6;
    private static final double ki = 0.0;
    private static final double kd = 2.95E-5;
    public static final double MANUAL_POWER_INC = 0.02;
    public static final double MANUAL_RATE_INC = 50.0;
    public static final double AUTO_INC = 0.0012;
    public static final double FENDER_POWER = 0.56;
    public static final double LONG_POWER = 0.94;
    private final double ENC_SCALE = -1.0;
    private final int MOVING_AVG_LENGTH = 20;
    public static final double RATE_SETTLE_TIME = 1.0;
    public static final double RATE_TOLERANCE = 1.0;
    private final int ENC_AVG_LENGTH = 10;
    private final int POWER_AVG_LENGTH = 15;
    private final double MAX_BACKWARD_POWER = 0.2;
    private final double GUESS_POWER_SCALE = .000064;

    // Actual robot parts.
    private final SpeedController mot1 = new Jaguar(RobotMap.SHOOTER_MOTOR_1);
    private final SpeedController mot2 = new Jaguar(RobotMap.SHOOTER_MOTOR_2);
    private final Encoder enc = new Encoder(RobotMap.SHOOTER_ENC_A, RobotMap.SHOOTER_ENC_B);

    // Other variables.
    private double power = 0;
    private boolean enabled = false;
    private Integral pidInt = new Integral();
    private MovingAverage powerAvg = new MovingAverage(POWER_AVG_LENGTH);
    private ComposedFilter dEncAvg = new ComposedFilter(new MovingAverage(ENC_AVG_LENGTH),
                                                        new DerivativeTimed());
    private final double TOLERANCE = 100;

    public Shooter() {
        super(kp, ki, kd);
        enc.start();
    }

    public void initDefaultCommand() {
        disable();
        setDefaultCommand(new ShooterSetPowerCmd(.1));
    }

    public void processSensors() {
        dEncAvg.feed(ENC_SCALE * enc.get());
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
        return Math.abs(getRate() - getSetpoint()) < TOLERANCE;
    }

    private double guessPower(double setpoint) {
        return GUESS_POWER_SCALE * setpoint;
    }

    public void enable() {
        enabled = true;
        super.enable();
    }

    public void disable() {
        enabled = false;
        pidInt.reset();
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
