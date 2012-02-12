package edu.neu.nutrons.reboundrumble.subsystems;

import edu.neu.nutrons.reboundrumble.RobotMap;
import edu.neu.nutrons.reboundrumble.commands.hood.HoodManualCmd;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 * Adjustable hood over shooter.
 *
 * @author Ziv
 */
public class Hood extends PIDSubsystem {

    // Constants.
    // TODO: tune PID.
    private static final double kp = 0.0;
    private static final double ki = 0.0;
    private static final double kd = 0.0;
    private final double POT_SCALE = 1.0;

    // Actual robot parts.
    private final Jaguar mot = new Jaguar(RobotMap.HOOD_MOTOR);
    private final AnalogChannel pot = new AnalogChannel(RobotMap.HOOD_POT);

    // Other variables.

    public Hood() {
        super(kp, ki, kd);
    }

    public void initDefaultCommand() {
        disable();
        setDefaultCommand(new HoodManualCmd());
    }

    public void setPower(double power) {
        mot.set(power);
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
}
