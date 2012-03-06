package edu.neu.nutrons.reboundrumble.subsystems;

import edu.neu.nutrons.lib.LinearVictor;
import edu.neu.nutrons.lib.MovingAverage;
import edu.neu.nutrons.reboundrumble.RobotMap;
import edu.neu.nutrons.reboundrumble.commands.hood.HoodSetPowerCmd;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 * Adjustable hood over shooter.
 *
 * @author Ziv
 */
public class Hood extends PIDSubsystem {

    // Constants.
    public static final double MANUAL_POWER = 1;
    // TODO: tune PID.
    private static final double kp = 15.0;
    private static final double ki = 0.0;
    private static final double kd = 0.0;
    private final double POWER_SCALE = 0.5;
    private final double POT_MIN = 1.0;
    private final double POT_RANGE = 0.4;
    private final int MOVING_AVG_LENGTH = 20;
    private static final double TOLERANCE = .03;
    public static final double SETTLE_TIME = 1.0;
    public static final double FENDER_POS = 0.0;
    public static final double LONG_POS = 0.8;

    // Actual robot parts.
    private final LinearVictor mot = new LinearVictor(RobotMap.HOOD_MOTOR);
    private final AnalogChannel pot = new AnalogChannel(RobotMap.HOOD_POT);

    // Other variables.
    private boolean enabled = false;
    private MovingAverage potFilter = new MovingAverage(MOVING_AVG_LENGTH);

    public Hood() {
        super(kp, ki, kd);
    }

    public void initDefaultCommand() {
        // TODO: replace or delete these two lines when we're done debugging.
        disable();
        setDefaultCommand(new HoodSetPowerCmd(0));
    }

    public void processSensors() {
        potFilter.feed((pot.getVoltage() - POT_MIN) / POT_RANGE);
    }

    public void setPower(double power) {
        double pos = getPos();
        boolean tooLow = (pos < 0) && (power < 0);
        boolean tooHigh = (pos > 1) && (power > 0);
        if(tooLow || tooHigh) {
            mot.set(0);
        }
        else {
            mot.set(POWER_SCALE * -power);
        }
    }

    public double getPos() {
        return potFilter.get();
    }

    public boolean atSetpoint() {
        return Math.abs(getPos() - getSetpoint()) < TOLERANCE;
    }

    protected double returnPIDInput() {
        return getPos();
    }

    protected void usePIDOutput(double output) {
        setPower(output);
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
}
