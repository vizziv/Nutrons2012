package edu.neu.nutrons.reboundrumble.commands.hood;

import edu.neu.nutrons.reboundrumble.commands.CommandBase;

/**
 * Sets the hood motor to move at a certain power.
 *
 * @author Ziv
 */
public class HoodSetPowerCmd extends CommandBase {

    private double power = 0;

    public HoodSetPowerCmd(double power) {
        this.power = power;
        requires(hood);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        hood.disable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        hood.setPower(power);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        // TODO: put hood.enable() here if necessary.
        hood.setPower(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
