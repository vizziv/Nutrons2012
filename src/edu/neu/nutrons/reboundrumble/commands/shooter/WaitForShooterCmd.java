package edu.neu.nutrons.reboundrumble.commands.shooter;

import edu.neu.nutrons.reboundrumble.commands.TimedEndConditionCmd;
import edu.neu.nutrons.reboundrumble.subsystems.Shooter;

/**
 * Waits until the shooter is at its setpoint.
 *
 * @author Ziv
 */
public class WaitForShooterCmd extends TimedEndConditionCmd {

    public WaitForShooterCmd() {
        super(.25);
        setTimeout(Shooter.RATE_SETTLE_TIMEOUT);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    protected boolean returnEndCondition() {
        return shooter.atSetpoint();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
