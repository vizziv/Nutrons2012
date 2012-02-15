package edu.neu.nutrons.reboundrumble.commands.drivetrain;

import edu.neu.nutrons.reboundrumble.commands.CommandBase;

/**
 * Control drive train with joysticks, tank style.
 *
 * @author Ziv
 */
public class DTSpinCmd extends CommandBase {

    double setpoint = 0;

    public DTSpinCmd(double setpoint) {
        this.setpoint = setpoint;
        requires(dt);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        dtPID.enableYaw(setpoint);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        dt.shift(false);
        dt.driveCar(0, dtPID.yawOut.get());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        // TODO: Decide on an end condition.
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
