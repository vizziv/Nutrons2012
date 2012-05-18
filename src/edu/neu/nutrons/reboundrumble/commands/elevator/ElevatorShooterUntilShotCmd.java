package edu.neu.nutrons.reboundrumble.commands.elevator;

/**
 *
 * @author Ziv
 */
public class ElevatorShooterUntilShotCmd extends ElevatorShooterCmd {

    protected boolean isFinished() {
        return !shooter.nearSetpoint();
    }
}
