package edu.neu.nutrons.reboundrumble.commands.drivetrain;

import edu.neu.nutrons.reboundrumble.commands.TimedEndConditionCmd;
import edu.neu.nutrons.reboundrumble.subsystems.DriveTrain;

/**
 * Drive straight a given distance.
 *
 * @author Ziv
 */
public class DTDriveDistanceCmd extends TimedEndConditionCmd {

    private double feet;
    private final double TOLERANCE = .05;

    public DTDriveDistanceCmd(double feet) {
        super(DriveTrain.DIS_SETTLE_TIME);
        this.feet = feet;
        requires(dt);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        dtPID.enableDistance(dt.disEncAvg.get() + feet);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        dt.driveCar(dtPID.disOut.get(), 0);
    }

    protected boolean returnEndCondition() {
        return Math.abs(dt.disEncAvg.get() - feet) < TOLERANCE;
    }

    protected boolean override() {
        return oi.getDriveManual();
    }

    // Called once after isFinished returns true
    protected void end() {
        dtPID.disableDistance();
        dt.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
