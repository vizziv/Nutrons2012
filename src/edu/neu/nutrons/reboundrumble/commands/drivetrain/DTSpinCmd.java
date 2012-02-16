package edu.neu.nutrons.reboundrumble.commands.drivetrain;

import edu.neu.nutrons.reboundrumble.commands.TimedEndConditionCmd;
import edu.neu.nutrons.reboundrumble.subsystems.DriveTrain;

/**
 * Control drive train with joysticks, tank style.
 *
 * @author Ziv
 */
public class DTSpinCmd extends TimedEndConditionCmd {

    double setpoint = 0;

    public DTSpinCmd(double setpoint) {
        super(DriveTrain.YAW_SETTLE_TIME);
        this.setpoint = setpoint;
        requires(dt);
        requires(shifter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        dtPID.enableYaw(setpoint);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        shifter.shift(false);
        dt.driveCar(0, dtPID.yawOut.get());
    }

    // Make this return true when this Command no longer needs to run execute()
    // Will only end if true for a continuous amount of time.
    protected boolean returnEndCondition() {
        // TODO: decide on an end condition.
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
