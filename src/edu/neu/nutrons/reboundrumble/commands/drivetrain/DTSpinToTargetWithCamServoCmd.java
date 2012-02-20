package edu.neu.nutrons.reboundrumble.commands.drivetrain;

import edu.neu.nutrons.reboundrumble.commands.TimedEndConditionCmd;

/**
 * Spins to target, using camera servo as feedback.
 *
 * @author Ziv
 */
public class DTSpinToTargetWithCamServoCmd extends TimedEndConditionCmd {

    private final double TOLERANCE = .02;
    private final double CAM_ANGLE_SCALE = 1.0;

    public DTSpinToTargetWithCamServoCmd() {
        super(1.0);
        requires(dt);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        dtPID.enableYaw(CAM_ANGLE_SCALE * cam.getAngle());
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        dt.driveCar(0, dtPID.yawOut.get());
    }

    // Make this return true when this Command no longer needs to run execute()
    // Will only end if true for a continuous amount of time.
    protected boolean returnEndCondition() {
        return Math.abs(cam.getAngle()) < TOLERANCE;
    }

    // Called once after isFinished returns true
    protected void end() {
        dt.stop();
        dtPID.disableYaw();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
