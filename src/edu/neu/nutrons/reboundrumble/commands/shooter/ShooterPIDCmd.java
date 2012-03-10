/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.nutrons.reboundrumble.commands.shooter;

import edu.neu.nutrons.reboundrumble.commands.CommandBase;

/**
 * If PID is enabled, do that; otherwise, set shooter power to 0.
 *
 * @author Nutrons Pros
 */
public class ShooterPIDCmd extends CommandBase {

    public ShooterPIDCmd() {
        requires(shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if(!shooter.isEnabled()) {
            shooter.setPower(0);
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
