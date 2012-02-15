package edu.neu.nutrons.reboundrumble.commands.hood;

import edu.neu.nutrons.reboundrumble.commands.CommandBase;

/**
 * Sets hood motor power manually if and only if PID is disabled. Also enables
 * and disables PID, but no way to set the setpoint. (Use SmartDashboard.)
 *
 * @author Nutrons Pros
 */
public class HoodDebugCmd extends CommandBase {

    private final double HOOD_POWER = 1;

    public HoodDebugCmd() {
        requires(hood);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        hood.disable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double up = oi.getHoodUp() ? HOOD_POWER : 0.0;
        double down = oi.getHoodDown() ? -HOOD_POWER : 0.0;
        if(oi.getDebug1()) {
            hood.disable();
        }
        if(oi.getDebug2()) {
            hood.enable();
        }
        if(!hood.isEnabled()) {
            hood.setPower(up + down);
        }
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
