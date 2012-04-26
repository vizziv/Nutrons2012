package edu.neu.nutrons.reboundrumble.commands.elevator;

import edu.neu.nutrons.reboundrumble.commands.CommandBase;
import edu.neu.nutrons.reboundrumble.subsystems.Elevator;

/**
 * Drives both elevator belts in the same direction.
 *
 * @author Ziv
 */
public class ElevatorShooterWhenReadyCmd extends CommandBase {

    public ElevatorShooterWhenReadyCmd() {
        requires(elev);
    }

    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        // Go to shooter if it's off or if it's spinning at the correct speed.
        if(shooter.getPower() < .1 || shooter.atSetpoint()) {
            elev.setPowerFB(Elevator.F_SHOOTER_POWER, Elevator.B_SHOOTER_POWER);
        }
        else {
            elev.stop();
        }
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
