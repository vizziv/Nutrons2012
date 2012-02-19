package edu.neu.nutrons.reboundrumble.commands.elevator;

/**
 * Drives elevator belts in opposite directions.
 *
 * @author Ziv
 */
public class ElevatorSpitCmd extends ElevatorShooterCmd {

    public ElevatorSpitCmd(boolean up) {
        super(up); // Calls requires(elev).
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        elev.setPowerFB(sign * .85, sign * -.55);
    }
}
