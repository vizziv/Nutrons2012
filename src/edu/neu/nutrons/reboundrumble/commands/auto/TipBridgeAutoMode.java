package edu.neu.nutrons.reboundrumble.commands.auto;

import edu.neu.nutrons.reboundrumble.commands.drivetrain.DTDriveDistanceCmd;
import edu.neu.nutrons.reboundrumble.commands.intake.IntakeSetCmd;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * Shoots balls from the key after a specified delay.
 *
 * @author Ziv
 */
public class TipBridgeAutoMode extends CommandGroup {

    public TipBridgeAutoMode() {
        addSequential(new IntakeSetCmd(true, true));
        addSequential(new DTDriveDistanceCmd(5.0, 1.0));
        addSequential(new WaitCommand(3.0));
        addSequential(new DTDriveDistanceCmd(-2.0, 0.6));
    }
}
