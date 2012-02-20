package edu.neu.nutrons.reboundrumble.commands.drivetrain;

import edu.neu.nutrons.reboundrumble.commands.CommandBase;

/**
 * Turns robot to face target.
 *
 * @author Ziv
 */
public class DTTurnToTargetCmd extends CommandBase {

    private static final double TOLERANCE = .025;
    private static final double kp = 5.0;
    private static final double servoKpScale = 1.0;

    private boolean rightSide = false;

    public DTTurnToTargetCmd(boolean rightSide) {
        this.rightSide = rightSide;
        requires(dt);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double power = kp * posError() / 2.0;
        if(rightSide) {
            dt.driveCar(-power, power);
        }
        else {
            dt.driveCar(power, power);
        }
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

    private double posError() {
        return cam.tracker.getTarget1().centerX;
    }
}
