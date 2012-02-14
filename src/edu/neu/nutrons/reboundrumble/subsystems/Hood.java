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
    // TODO: tune PID.
    private static final double kp = 1.0;
    private static final double ki = 0.0;
    private static final double kd = 0.0;
    private final double POWER_SCALE = 0.5;
    private final double POT_SCALE = 1.0;

    // Actual robot parts.
    private final LinearVictor mot = new LinearVictor(RobotMap.HOOD_MOTOR);
    private final AnalogChannel pot = new AnalogChannel(RobotMap.HOOD_POT);

    // Other variables.
    private boolean enabled = false;

    public Hood() {
        super(kp, ki, kd);
    }

    public void initDefaultCommand() {
        disable();
        setDefaultCommand(new HoodDebugCmd());
    }

    public void setPower(double power) {
        mot.set(POWER_SCALE * -power);
    }

    public double getPos() {
        // TODO: do smoothing or math if necessary.
        return POT_SCALE * pot.getVoltage();
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
