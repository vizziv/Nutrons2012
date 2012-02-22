package edu.neu.nutrons.reboundrumble.commands.elevator;

import edu.neu.nutrons.reboundrumble.subsystems.Elevator;

/**
 * Drives elevator belts in opposite directions.
 *
 * @author Ziv
 */
public class ElevatorHopperCmd extends ElevatorShooterCmd {

    public ElevatorHopperCmd(boolean up) {
        super(up); // Calls requires(elev).
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        elev.setPowerFB(sign * Elevator.F_HOPPER_POWER, sign * -Elevator.B_HOPPER_POWER);
    }
}
