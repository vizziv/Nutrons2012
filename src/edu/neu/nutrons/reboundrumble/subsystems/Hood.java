package edu.neu.nutrons.reboundrumble.subsystems;

import edu.neu.nutrons.reboundrumble.RobotMap;
import edu.neu.nutrons.reboundrumble.commands.hood.HoodHoldPosCmd;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * This is a pneumatically actuated hood.
 *
 * @author Nutrons Pros
 */
public class Hood extends Subsystem {

    private final Solenoid hood = new Solenoid(RobotMap.HOOD);
    private boolean up = false;

    public void setPos(boolean up){
        this.up = up;
        hood.set(!this.up);
    }

    public boolean getPos() {
        return up;
    }

    protected void initDefaultCommand() {
        setDefaultCommand(new HoodHoldPosCmd());
    }
}
