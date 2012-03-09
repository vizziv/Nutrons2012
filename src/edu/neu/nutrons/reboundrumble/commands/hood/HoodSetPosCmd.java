package edu.neu.nutrons.reboundrumble.commands.hood;

import edu.neu.nutrons.reboundrumble.commands.CommandBase;

/**
 * Sets hood to be up or down.
 *
 * @author Nutrons Pros
 */
public class HoodSetPosCmd extends CommandBase {

    private boolean up = false;

    public HoodSetPosCmd(boolean up) {
        this.up = up;
        requires(hood);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        hood.setPos(up);
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
