package edu.neu.nutrons.reboundrumble.commands.shooter;

import edu.neu.nutrons.reboundrumble.commands.CommandBase;

/**
 * Runs the shooter wheel at a given power. Open loop.
 *
 * @author Nutrons Pros
 */
public class ShooterSetPowerCmd extends CommandBase {

    private double power = 0;

    public ShooterSetPowerCmd(double power) {
        this.power = power;
        requires(shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        shooter.disable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        shooter.setPower(power);
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
