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
        addSequential(CommandBase.prepareKeyCmd(), .1);
        addSequential(new WaitCommand(shootTime));
        addSequential(new ElevatorShooterCmd(), .1);
        addSequential(new WaitCommand(2.2));
        addSequential(new ElevatorShooterCmd(), .2);
        addSequential(new WaitCommand(2.2));
        addSequential(new ElevatorShooterCmd(), .2);
        addSequential(new WaitCommand(2.2));
        addSequential(new ElevatorShooterCmd(), .2);
        addSequential(new WaitCommand(2.2));
        addSequential(new ElevatorShooterCmd(), .2);
        addSequential(new WaitCommand(2.2));
        addSequential(new ElevatorShooterCmd(), .2);
        addSequential(new WaitCommand(2.2));
        addSequential(new ElevatorShooterCmd(), .2);
        addSequential(new WaitCommand(2.2));
    }
}
