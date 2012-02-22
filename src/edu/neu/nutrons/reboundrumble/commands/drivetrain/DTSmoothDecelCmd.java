package edu.neu.nutrons.reboundrumble.commands.drivetrain;

import edu.neu.nutrons.reboundrumble.commands.CommandBase;
import edu.neu.nutrons.reboundrumble.subsystems.DriveTrain;

/**
 * Smoothly decelerate to given values.
 *
 * @author Ziv
 */
public class DTSmoothDecelCmd extends CommandBase {

    private double throttle = 0;
    private double wheel = 0;
    private double power = 0;
    private int sign = 1;
    private boolean done = false;

    public DTSmoothDecelCmd(double throttle, double wheel) {
        this.throttle = throttle;
        this.wheel = wheel;
        sign  = throttle > 0 ? 1 : -1;
        requires(dt);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        power = throttle;
        done = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if(sign * power > 0) {
            power -= throttle / DriveTrain.SMOOTHING_CYCLES;
        }
        else {
            done = true;
        }
        dt.driveCar(power, wheel);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return oi.getDriveManual() || done;
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
