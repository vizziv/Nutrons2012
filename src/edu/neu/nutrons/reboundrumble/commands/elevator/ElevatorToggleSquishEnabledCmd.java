package edu.neu.nutrons.reboundrumble.commands.elevator;

import edu.neu.nutrons.reboundrumble.commands.CommandBase;

/**
 * Sets whether or not the elevator will compensate for ball pressure when doing
 * key shots.
 *
 * @author Ziv
 */
public class ElevatorToggleSquishEnabledCmd extends CommandBase {

    // Called just before this Command runs the first time
    protected void initialize() {
        elev.setSquishEnabled(!elev.getSquishEnabled());
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
