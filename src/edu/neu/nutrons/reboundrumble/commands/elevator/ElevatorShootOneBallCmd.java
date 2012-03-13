package edu.neu.nutrons.reboundrumble.commands.elevator;

/**
 * Brings a ball up to the shooter and stops when another ball is ready.
 *
 * @author Ziv
 */
public class ElevatorShootOneBallCmd extends ElevatorShooterCmd {

    public ElevatorShootOneBallCmd() {
        super();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return elev.hasNewBall();
    }
}
