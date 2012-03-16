package edu.neu.nutrons.reboundrumble.commands.auto;

import edu.neu.nutrons.lib.LoopCmd;
import edu.neu.nutrons.reboundrumble.commands.CommandBase;
import edu.neu.nutrons.reboundrumble.commands.group.ShootWhenReadyCmd;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitUntilCommand;

/**
 * Shoots balls from the key after a specified delay.
 *
 * @author Ziv
 */
public class ShootFromKeyAutoMode extends CommandGroup {

    public ShootFromKeyAutoMode(double shootTime) {
        addSequential(CommandBase.prepareKey);
        addSequential(new WaitUntilCommand(shootTime));
        addSequential(new LoopCmd(new ShootWhenReadyCmd()));
    }
}
