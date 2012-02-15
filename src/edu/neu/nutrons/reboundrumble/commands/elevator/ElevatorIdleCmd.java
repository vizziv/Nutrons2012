package edu.neu.nutrons.reboundrumble.commands.elevator;

import edu.neu.nutrons.reboundrumble.commands.CommandBase;

/**
 * Elevator sits still.
 *
 * @author Ziv
 */
public class ElevatorIdleCmd extends CommandBase {

    public ElevatorIdleCmd() {
        requires(elev);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        elev.stop();
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
