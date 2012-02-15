package edu.neu.nutrons.reboundrumble.commands.elevator;

import edu.neu.nutrons.reboundrumble.commands.CommandBase;

/**
 * Controls the elevator manually.
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
        if(oi.getElevShooter()){
            setElevShooter();
        }
        if(oi.getElevHopper()){
            setElevHopper();
        }
        if(oi.getElevHopperRev()){
            setElevHopperREV();
        }
        if(oi.getElevShooterRev()){
            setElevShooterREV();
        }else{
            elev.setPowerFB(0, 0);
        }
        //elev.setPowerFB(oi.getElevFront(), oi.getElevBack());
    }

    protected void setElevShooter(){
        elev.setPowerFB(-0.6, -0.6);
    }

    protected void setElevShooterREV(){
        elev.setPowerFB(0.6, 0.6);
    }

    protected void setElevHopper(){
        elev.setPowerFB(-0.6, 0.6);
    }

    protected void setElevHopperREV(){
        elev.setPowerFB(0.6, -0.6);
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
