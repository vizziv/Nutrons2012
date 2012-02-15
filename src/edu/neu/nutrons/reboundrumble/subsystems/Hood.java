package edu.neu.nutrons.reboundrumble.subsystems;

import edu.neu.nutrons.lib.LinearVictor;
import edu.neu.nutrons.reboundrumble.RobotMap;
import edu.neu.nutrons.reboundrumble.commands.hood.HoodDebugCmd;
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
    private static final double kp = 1.0;
    private static final double ki = 0.0;
    private static final double kd = 0.0;
    private final double POWER_SCALE = 0.5;
    private final double POT_MIN = 1.0;
    private final double POT_RANGE = 0.4;

    // Actual robot parts.
    private final LinearVictor mot = new LinearVictor(RobotMap.HOOD_MOTOR);
    private final AnalogChannel pot = new AnalogChannel(RobotMap.HOOD_POT);

    // Other variables.
    private boolean enabled = false;

    public Hood() {
        super(kp, ki, kd);
    }

    public void initDefaultCommand() {
        // TODO: replace or delete these two lines when we're done debugging.
        disable();
        setDefaultCommand(new HoodDebugCmd());
    }

    public void setPower(double power) {
        mot.set(POWER_SCALE * -power);
    }

    public double getPos() {
        // Transforms from voltage to (approximately) the interval [0,1].
        // TODO: do smoothing if necessary.
        return (pot.getVoltage() - POT_MIN) / POT_RANGE;
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
