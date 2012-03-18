package edu.neu.nutrons.reboundrumble.commands.auto;

import edu.neu.nutrons.reboundrumble.commands.CommandBase;
import edu.neu.nutrons.reboundrumble.commands.group.ShootWhenReadyCmd;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitUntilCommand;

/**
 * Shoots balls from the key after a specified delay.
 *
 * @author Ziv
 */
public class ShootFromKeyHackyAutoMode extends CommandGroup {

    public ShootFromKeyHackyAutoMode(double shootTime) {
        addSequential(CommandBase.prepareKeyCmd());
        addSequential(new WaitUntilCommand(shootTime));
        addSequential(new ShootWhenReadyCmd());
        addSequential(new ShootWhenReadyCmd());
        addSequential(new ShootWhenReadyCmd());
        addSequential(new ShootWhenReadyCmd());
        addSequential(new ShootWhenReadyCmd());
        addSequential(new ShootWhenReadyCmd());
    }
}
