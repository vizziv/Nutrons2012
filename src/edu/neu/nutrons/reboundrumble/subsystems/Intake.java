package edu.neu.nutrons.reboundrumble.subsystems;

import edu.neu.nutrons.reboundrumble.RobotMap;
import edu.neu.nutrons.reboundrumble.commands.intake.IntakeSetCmd;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Drop down intake used for herding balls and pushing down bridge.
 *
 * @author Ziv
 */
public class Intake extends Subsystem {

    private final Relay intake = new Relay(RobotMap.INTAKE);
    private final Solenoid dropDown1 = new Solenoid(RobotMap.DROP_DOWN_1);
    private final Solenoid dropDown2 = new Solenoid(RobotMap.DROP_DOWN_2);

    public void initDefaultCommand() {
        setDefaultCommand(new IntakeSetCmd(false, false));
    }

    public void setDrop(boolean dropped) {
        dropDown1.set(dropped);
        dropDown2.set(dropped);
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
