package edu.neu.nutrons.reboundrumble.commands.elevator;

import edu.neu.nutrons.reboundrumble.commands.CommandBase;
import edu.neu.nutrons.reboundrumble.subsystems.Elevator;

/**
 * Drives elevator belts in opposite directions.
 *
 * @author Ziv
 */
public class ElevatorSuperintakeCmd extends CommandBase {

    public ElevatorSuperintakeCmd() {
        requires(elev);
    }

    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        elev.setPowerFB(Elevator.F_SUPERINTAKE_POWER, -Elevator.B_SUPERINTAKE_POWER);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        elev.stop();
    }

    protected void interrupted() {
        end();
    }
}
