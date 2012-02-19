/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.nutrons.reboundrumble.commands.hood;

import edu.neu.nutrons.reboundrumble.commands.TimedEndConditionCmd;
import edu.neu.nutrons.reboundrumble.subsystems.Hood;

/**
 *
 * @author Ziv
 */
public class HoodSetPosCmd extends TimedEndConditionCmd {

    private double setpoint = 0;

    public HoodSetPosCmd(double setpoint) {
        super(Hood.SETTLE_TIME);
        this.setpoint = setpoint;
        requires(hood);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        hood.enable();
        hood.setSetpoint(setpoint);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean returnEndCondition() {
        return hood.atSetpoint();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
