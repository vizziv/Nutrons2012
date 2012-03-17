package edu.neu.nutrons.reboundrumble.commands.auto;

import edu.neu.nutrons.reboundrumble.commands.CommandBase;
import edu.neu.nutrons.reboundrumble.commands.elevator.ElevatorShooterCmd;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * Shoots balls from the key after a specified delay.
 *
 * @author Ziv
 */
public class ShootFromKeyHackyAutoMode extends CommandGroup {

    public ShootFromKeyHackyAutoMode(double shootTime) {
        addSequential(CommandBase.prepareKey, 2.0);
        addSequential(new WaitCommand(shootTime), 2.0);
        addSequential(new ElevatorShooterCmd(), 5.0);
    }
}
