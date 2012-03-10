package edu.neu.nutrons.reboundrumble.commands.shooter;

import edu.neu.nutrons.reboundrumble.commands.CommandBase;

/**
 * Set rate on the shooter. Closed loop.
 *
 * @author Nutrons Pros
 */
public class ShooterSetRateCmd extends CommandBase {

    private double rate = 0;

    public ShooterSetRateCmd(double rate) {
        this.rate = rate;
        requires(shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        shooter.enable();
        shooter.setSetpoint(rate);
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
