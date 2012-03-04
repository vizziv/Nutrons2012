package edu.neu.nutrons.reboundrumble.subsystems;

import edu.neu.nutrons.lib.ComposedFilter;
import edu.neu.nutrons.lib.DerivativeTimed;
import edu.neu.nutrons.lib.MovingAverage;
import edu.neu.nutrons.lib.Utils;
import edu.neu.nutrons.reboundrumble.RobotMap;
import edu.neu.nutrons.reboundrumble.commands.shooter.ShooterMaintainPowerCmd;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 * Single-wheel shooter.
 *
 * @author Ziv
 */
public class Shooter extends PIDSubsystem {

    // Constants.
    // TODO: tune PID.
    private static final double kp = 0.0;
    private static final double ki = 0.0;
    private static final double kd = 0.0;
    public static final double MANUAL_INC = 0.02;
    public static final double FENDER_POWER = 0.28;
    public static final double LONG_POWER = 0.60;
    private final double ENC_SCALE = -1.0;
    private final int ENC_AVG_LENGTH = 20;
    private final int POWER_AVG_LENGTH = 20;
    private final double MAX_BACKWARD_POW = 0.2;

    // Actual robot parts.
    private final Jaguar mot1 = new Jaguar(RobotMap.SHOOTER_MOTOR_1);
    private final Jaguar mot2 = new Jaguar(RobotMap.SHOOTER_MOTOR_2);
    private final Encoder enc = new Encoder(RobotMap.SHOOTER_ENC_A, RobotMap.SHOOTER_ENC_B);

    // Other variables.
    private double power = 0;
    private boolean enabled = false;
    private MovingAverage powerAvg = new MovingAverage(POWER_AVG_LENGTH);
    private ComposedFilter dEncAvg = new ComposedFilter(new DerivativeTimed(),
                                                        new MovingAverage(ENC_AVG_LENGTH));
    private double TOLERANCE = 100;

    public Shooter() {
        super(kp, ki, kd);
        enc.start();
    }

    public void initDefaultCommand() {
        disable();
        setDefaultCommand(new ShooterMaintainPowerCmd());
    }

    public void processSensors() {
        dEncAvg.feed(enc.get());
    }

    public void setPower(double power) {
        // Moving average stops sudden changes in motor speed, which in turn
        // (hopefully) stops the shooter from breaking or catching fire.
        powerAvg.feed(Utils.limit(power, -MAX_BACKWARD_POW, 1.0));
        this.power = powerAvg.get();
        mot1.set(-this.power);
        mot2.set(-this.power);
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
