package edu.neu.nutrons.reboundrumble.commands.elevator;

import edu.neu.nutrons.reboundrumble.commands.CommandBase;
import edu.neu.nutrons.reboundrumble.subsystems.Elevator;

/**
 * Drives both elevator belts in the same direction.
 *
 * @author Ziv
 */
public class ElevatorShooterCmd extends CommandBase {

    public ElevatorShooterCmd() {
        requires(elev);
    }

    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        elev.setPowerFB(Elevator.B_SHOOTER_POWER, Elevator.B_SHOOTER_POWER);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
