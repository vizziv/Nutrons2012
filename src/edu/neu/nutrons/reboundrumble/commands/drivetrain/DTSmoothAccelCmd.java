package edu.neu.nutrons.reboundrumble.commands.drivetrain;

import edu.neu.nutrons.reboundrumble.commands.CommandBase;
import edu.neu.nutrons.reboundrumble.subsystems.DriveTrain;

/**
 * Drive with given values, car-style.
 *
 * @author Ziv
 */
public class DTSmoothAccelCmd extends CommandBase {

    private double throttle = 0;
    private double wheel = 0;
    private double power = 0;

    public DTSmoothAccelCmd(double throttle, double wheel) {
        this.throttle = throttle;
        this.wheel = wheel;
        requires(dt);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        power = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if(power < throttle) {
            power += throttle / DriveTrain.SMOOTHING_CYCLES;
        }
        dt.driveCar(power, wheel);
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
