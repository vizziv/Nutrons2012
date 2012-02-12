package edu.neu.nutrons.reboundrumble.commands.hood;

import edu.neu.nutrons.reboundrumble.commands.CommandBase;

/**
 * Sets hood motor power manually.
 *
 * @author Nutrons Pros
 */
public class HoodManualCmd extends CommandBase {

    private final double HOOD_POWER = 0.5;

    public HoodManualCmd() {
        requires(hood);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        hood.disable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double up = oi.getHoodUp() ? HOOD_POWER : 0.0;
        double down = oi.getHoodUp() ? -HOOD_POWER : 0.0;
        hood.setPower(up + down);
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
