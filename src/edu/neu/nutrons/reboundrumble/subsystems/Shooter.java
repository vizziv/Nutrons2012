package edu.neu.nutrons.reboundrumble.subsystems;

import edu.neu.nutrons.lib.Derivative;
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
    public static final double MANUAL_INC = 0.02;
    // TODO: tune PID.
    private static final double kp = 0.0;
    private static final double ki = 0.0;
    private static final double kd = 0.0;
    private final double ENC_SCALE = -1.0;
    private final int MOVING_AVG_LENGTH = 20;

    // Actual robot parts.
    private final Jaguar mot1 = new Jaguar(RobotMap.SHOOTER_MOTOR_1);
    private final Jaguar mot2 = new Jaguar(RobotMap.SHOOTER_MOTOR_2);
    private final Encoder enc = new Encoder(RobotMap.SHOOTER_ENC_A, RobotMap.SHOOTER_ENC_B);

    // Other variables.
    private double power = 0;
    private boolean enabled = false;
    private MovingAverage encFilter = new MovingAverage(MOVING_AVG_LENGTH);
    private Derivative dEnc = new Derivative();

    public Shooter() {
        super(kp, ki, kd);
        enc.start();
    }

    public void initDefaultCommand() {
        disable();
        setDefaultCommand(new ShooterMaintainPowerCmd());
    }

    public void setPower(double power) {
        this.power = Utils.limit(-power, -1.0, 1.0);
        mot1.set(this.power);
        mot2.set(this.power);
    }

    public double getPower() {
        return power;
    }

    public double getRate(boolean refresh) {
        // We want to control how frequently we refresh, so it's optional.
        if(refresh) {
            dEnc.feed(enc.get());
            encFilter.feed(dEnc.get());
        }
        return ENC_SCALE * encFilter.get();
    }

    public double getRate() {
        return getRate(true);
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
