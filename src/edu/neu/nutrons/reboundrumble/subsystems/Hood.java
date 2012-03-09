package edu.neu.nutrons.reboundrumble.subsystems;

import edu.neu.nutrons.reboundrumble.RobotMap;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * This is a pneumatically actuated hood.
 *
 * @author Nutrons Pros
 */
public class Hood extends Subsystem {

    private final Solenoid hood = new Solenoid(RobotMap.HOOD);

    public void setPos(boolean up){
        hood.set(up);
    }

    protected void initDefaultCommand() {
        // TODO: figure this out.
    }
}
