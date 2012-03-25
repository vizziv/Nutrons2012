package edu.neu.nutrons.reboundrumble.commands.drivetrain;

import edu.neu.nutrons.reboundrumble.commands.CommandBase;

/**
 * Drive straight at a given power for a given amount of time.
 *
 * @author Ziv
 */
public class DTDriveTimeCmd extends CommandBase {

    private double throttle = 0;

    public DTDriveTimeCmd(double timeout, double throttle) {
        this.throttle = throttle;
        setTimeout(timeout);
        requires(dt);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        dt.driveCar(throttle, 0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
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
