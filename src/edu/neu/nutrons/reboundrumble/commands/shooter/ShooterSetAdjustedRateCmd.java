package edu.neu.nutrons.reboundrumble.commands.shooter;

import edu.neu.nutrons.lib.Utils;
import edu.neu.nutrons.reboundrumble.commands.CommandBase;
import edu.neu.nutrons.reboundrumble.subsystems.Elevator;

/**
 * Set rate on the shooter. Closed loop.
 *
 * @author Nutrons Pros
 */
public class ShooterSetAdjustedRateCmd extends CommandBase {

    private double rate = 0;

    public ShooterSetAdjustedRateCmd(double rate) {
        this.rate = rate;
        requires(shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        shooter.enable();
        shooter.setSetpoint(adjustSetpoint(rate, elev.getPressure()));
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }

    private double adjustSetpoint(double idealRate, double pressure) {
        if(!elev.getSquishEnabled() || pressure < Elevator.PRESSURE_THRESHOLD) {
            return idealRate;
        }
        return idealRate * Utils.absPow(Elevator.IDEAL_PRESSURE / pressure, .5);
    }
}
