package edu.neu.nutrons.reboundrumble.commands.elevator;

import edu.neu.nutrons.reboundrumble.commands.CommandBase;
import edu.neu.nutrons.reboundrumble.subsystems.Elevator;

/**
 * Drives elevator belts in opposite directions.
 *
 * @author Ziv
 */
public class ElevatorSpitCmd extends CommandBase {

    public ElevatorSpitCmd() {
        requires(elev);
    }

    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        elev.setPowerFB(-Elevator.F_SPIT_POWER, Elevator.B_SPIT_POWER);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
