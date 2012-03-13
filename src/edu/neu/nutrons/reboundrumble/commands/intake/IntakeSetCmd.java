package edu.neu.nutrons.reboundrumble.commands.intake;

import edu.neu.nutrons.reboundrumble.commands.CommandBase;

/**
 * Sets the intake to either up or down and either running or
 *
 * @author Ziv
 */
public class IntakeSetCmd extends CommandBase {

    private boolean dropped = false;
    private boolean intaking = false;

    public IntakeSetCmd(boolean dropped, boolean intaking) {
        this.dropped = dropped;
        this.intaking = intaking;
        requires(intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        intake.setDrop(dropped);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        intake.setIntake(intaking);
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
