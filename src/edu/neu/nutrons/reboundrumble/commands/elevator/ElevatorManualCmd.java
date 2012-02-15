package edu.neu.nutrons.reboundrumble.commands.elevator;

import edu.neu.nutrons.reboundrumble.commands.CommandBase;
import edu.neu.nutrons.reboundrumble.subsystems.Elevator;

/**
 * Controls the elevator manually.
 *
 * @deprecated Separated into several commands for modularity.
 *
 * @author root
 */
public class ElevatorManualCmd extends CommandBase {

    public ElevatorManualCmd() {
        requires(elev);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if(oi.getElevShooter()) {
            setElevShooter();
        }
        if(oi.getElevHopper()) {
            setElevHopper();
        }
        if(oi.getElevHopperRev()) {
            setElevHopperREV();
        }
        if(oi.getElevShooterRev()) {
            setElevShooterREV();
        }
        else {
            elev.stop();
        }
    }

    private void setElevShooter(){
        elev.setPowerFB(-Elevator.POWER, -Elevator.POWER);
    }

    private void setElevShooterREV(){
        elev.setPowerFB(Elevator.POWER, Elevator.POWER);
    }

    private void setElevHopper(){
        elev.setPowerFB(-Elevator.POWER, Elevator.POWER);
    }

    private void setElevHopperREV(){
        elev.setPowerFB(Elevator.POWER, -Elevator.POWER);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
