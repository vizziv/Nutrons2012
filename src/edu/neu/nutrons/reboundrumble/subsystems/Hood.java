package edu.neu.nutrons.reboundrumble.subsystems;

import edu.neu.nutrons.reboundrumble.RobotMap;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 * Adjustable hood over shooter.
 *
 * @author Ziv
 */
public class Hood extends PIDSubsystem {

    // TODO: tune PID.
    private static final double kp = 0;
    private static final double kd = 0;

    private Jaguar mot = new Jaguar(RobotMap.HOOD_MOTOR);
    private AnalogChannel pot = new AnalogChannel(RobotMap.HOOD_POT);

    public Hood() {
        super(kp, 0, kd);
    }

    public void initDefaultCommand() {
    }

    public void setPower(double power) {
        mot.set(power);
    }

    public double getPos() {
        // TODO: add math to make this value make sense.
        return pot.getVoltage();
    }

    protected double returnPIDInput() {
        return getPos();
    }

    protected void usePIDOutput(double output) {
        mot.set(output);
    }
}
