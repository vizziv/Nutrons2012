package edu.neu.nutrons.reboundrumble.commands.drivetrain;

import edu.neu.nutrons.reboundrumble.commands.CommandBase;

/**
 * Turns robot to face target.
 *
 * @author Ziv
 */
public class DTTurnToTargetCmd extends CommandBase {

    private static final double TOLERANCE = .05;
    private static final double kp = 1.5;
    private static final double servoKpScale = 1;

    private boolean useCamServo = false;

    public DTTurnToTargetCmd() {
        requires(dt);
    }

    public DTTurnToTargetCmd(boolean useCamServo) {
        this.useCamServo = useCamServo;
        requires(dt);
    }

    private double posError() {
        if(useCamServo) {
            return servoKpScale * cam.getPos();
        }
        else {
            return cam.tracker.getTarget1().centerX;
        }
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        dt.driveCar(0, kp * posError());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(posError()) < TOLERANCE ||
                   cam.tracker.getTarget1().isNull();
    }

    // Called once after isFinished returns true
    protected void end() {
        dt.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
