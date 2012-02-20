package edu.neu.nutrons.reboundrumble.commands.elevator;

import edu.neu.nutrons.lib.Utils;
import edu.neu.nutrons.reboundrumble.commands.CommandBase;
import edu.neu.nutrons.reboundrumble.subsystems.Elevator;

/**
 * Move elevator more slowly when measuring pressure of a ball.
 *
 * @author Ziv
 */
public class ElevatorMeasureCmd extends CommandBase {

    private final double THRESHOLD = .2;
    private double scale = 1;

    public ElevatorMeasureCmd() {
        requires(elev);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        scale = 1;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        scale = Utils.limit(Utils.absPow(1 - elev.getPressure() / 3.25, 4), 0, scale);
        elev.setPowerFB(scale * Elevator.F_POWER, scale * Elevator.B_POWER);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        elev.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
