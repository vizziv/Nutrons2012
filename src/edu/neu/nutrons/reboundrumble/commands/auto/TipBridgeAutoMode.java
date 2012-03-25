package edu.neu.nutrons.reboundrumble.commands.auto;

import edu.neu.nutrons.reboundrumble.commands.drivetrain.DTDriveTimeCmd;
import edu.neu.nutrons.reboundrumble.commands.intake.IntakeSetCmd;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.StartCommand;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * Shoots balls from the key after a specified delay.
 *
 * @author Ziv
 */
public class TipBridgeAutoMode extends CommandGroup {

    public TipBridgeAutoMode(double driveTime) {
        addSequential(new StartCommand(new IntakeSetCmd(true, true)));
        addSequential(new DTDriveTimeCmd(driveTime / 4.0, 0.8));
        addSequential(new WaitCommand(3.0));
        addSequential(new DTDriveTimeCmd(driveTime / 4.0, -0.6));
    }
}
