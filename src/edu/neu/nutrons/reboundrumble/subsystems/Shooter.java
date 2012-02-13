package edu.neu.nutrons.reboundrumble.subsystems;

import edu.neu.nutrons.lib.Utils;
import edu.neu.nutrons.reboundrumble.RobotMap;
import edu.neu.nutrons.reboundrumble.commands.shooter.ShooterMaintainPowerCmd;
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

    // Constants.
    // TODO: tune PID.
    private static final double kp = 0.0;
    private static final double ki = 0.0;
    private static final double kd = 0.0;
    private final double ENC_SCALE = 1.0;

    // Actual robot parts.
    private final Jaguar mot1 = new Jaguar(RobotMap.SHOOTER_MOTOR_1);
    private final Jaguar mot2 = new Jaguar(RobotMap.SHOOTER_MOTOR_2);
    private final Encoder enc = new Encoder(RobotMap.SHOOTER_ENC_A, RobotMap.SHOOTER_ENC_B,
                                      false, CounterBase.EncodingType.k1X);

    // Other variables.
    private double power = 0;
    private boolean enabled = false;

    public Shooter() {
        super(kp, ki, kd);
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

    public double getRate() {
        // TODO: smooth if needed.
        return ENC_SCALE * enc.getRate();
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
        // Start with a somewhat accurate guess and fine tune with PID.
        setPower(output);
    }
}
