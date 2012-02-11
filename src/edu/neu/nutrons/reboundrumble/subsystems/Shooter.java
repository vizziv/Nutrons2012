package edu.neu.nutrons.reboundrumble.subsystems;

import edu.neu.nutrons.reboundrumble.RobotMap;
import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 * Single-wheel shooter.
 *
 * @author Ziv
 */
public class Shooter extends PIDSubsystem {

    // TODO: tune PID.
    private static final double kp = 0;

    private Jaguar mot = new Jaguar(RobotMap.SHOOTER_MOTOR);
    private Encoder enc = new Encoder(RobotMap.SHOOTER_ENC_A, RobotMap.SHOOTER_ENC_B,
                                      false, CounterBase.EncodingType.k1X);

    public Shooter() {
        super(kp, 0, 0);
    }

    public void initDefaultCommand() {
    }

    public void setPower(double power) {
        mot.set(power);
    }

    public double getRate() {
        // TODO: smooth if needed.
        return enc.getRate();
    }

    private double guessPower(double setpoint) {
        // TODO: make a reasonable guess, preferably based on data.
        return 0;
    }

    protected double returnPIDInput() {
        return getRate();
    }

    protected void usePIDOutput(double output) {
        // Start with a somewhat accurate guess and fine tune with PID.
        mot.set(guessPower(getSetpoint()) + output);
    }
}
