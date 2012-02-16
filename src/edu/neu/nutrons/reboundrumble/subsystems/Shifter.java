package edu.neu.nutrons.reboundrumble.subsystems;

import edu.neu.nutrons.reboundrumble.RobotMap;
import edu.neu.nutrons.reboundrumble.commands.CommandBase;
import edu.neu.nutrons.reboundrumble.commands.shifter.ShifterStaticCmd;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * 8-wheel tank drive train.
 *
 * @author Ziv
 */
public class Shifter extends Subsystem {

    private final Solenoid shifter = new Solenoid(RobotMap.SHIFTER);
    public static final boolean DEFAULT = true;

    public void initDefaultCommand() {
        setDefaultCommand(new ShifterStaticCmd(DEFAULT));
    }

    public void shift(boolean highGear) {
        CommandBase.dt.setTSens(highGear);
        shifter.set(highGear);
    }
}
