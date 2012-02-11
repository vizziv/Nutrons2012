/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.nutrons.reboundrumble.commands.hood;

import edu.neu.nutrons.reboundrumble.commands.CommandBase;

/**
 *
 * @author Nutrons Pros
 */
public class HoodManualCmd extends CommandBase {

    public HoodManualCmd() {
      requires(hood);
      hood.disable();
    }

    protected void initialize() {
    }


    protected void execute() {
        hood.setPower(oi.getHoodPower());
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
