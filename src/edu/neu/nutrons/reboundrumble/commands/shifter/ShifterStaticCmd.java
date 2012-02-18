package edu.neu.nutrons.reboundrumble.commands.shifter;

import edu.neu.nutrons.reboundrumble.commands.CommandBase;

/**
 * Shifts the drive train gear as specified and holds it there indefinitely.
 *
 * @author Ziv
 */
public class ShifterStaticCmd extends CommandBase {

    boolean highGear = false;

    public ShifterStaticCmd(boolean highGear) {
        this.highGear = highGear;
        requires(shifter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        shifter.shift(highGear);
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
