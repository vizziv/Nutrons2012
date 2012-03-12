package edu.neu.nutrons.reboundrumble.commands.hood;

import edu.neu.nutrons.reboundrumble.commands.CommandBase;

/**
 * Does nothing. Yes, very interesting.
 *
 * @author Nutrons Pros
 */
public class HoodHoldPosCmd extends CommandBase {

    public HoodHoldPosCmd() {
        requires(hood);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
