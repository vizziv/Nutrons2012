package edu.neu.nutrons.reboundrumble.subsystems;

import edu.neu.nutrons.reboundrumble.RobotMap;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Drop down intake used for herding balls and pushing down bridge.
 *
 * @author Ziv
 */
public class Intake extends Subsystem {

    private Relay intake = new Relay(RobotMap.INTAKE);
    private Solenoid dropDown = new Solenoid(RobotMap.DROP_DOWN);

    public void initDefaultCommand() {
    }

    public void setPos(boolean dropped) {
        dropDown.set(dropped);
    }

    public void setIntake(boolean intaking) {
        if(intaking) {
            intake.set(Relay.Value.kForward);
        }
        else {
            intake.set(Relay.Value.kOff);
        }
    }
}
