package edu.neu.nutrons.reboundrumble.commands.drivetrain;

import edu.neu.nutrons.reboundrumble.commands.CommandBase;

/**
 * Control drive train with joysticks.
 *
 * @author Ziv
 */
public class DTManualCheesyCmd extends CommandBase {

    public DTManualCheesyCmd() {
        requires(dt);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        dt.driveCheesy(oi.getDriveThrottle(), oi.getDriveWheel(), oi.getDriveQuickTurn());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        dt.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
