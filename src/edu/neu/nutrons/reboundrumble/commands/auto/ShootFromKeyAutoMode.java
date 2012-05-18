package edu.neu.nutrons.reboundrumble.commands.auto;

import edu.neu.nutrons.reboundrumble.commands.CommandBase;
import edu.neu.nutrons.reboundrumble.commands.elevator.ElevatorShooterUntilShotCmd;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * Shoots balls from the key after a specified delay.
 *
 * @author Ziv
 */
public class ShootFromKeyAutoMode extends CommandGroup {

    public ShootFromKeyAutoMode(double shootTime, double betweenTime) {
        addParallel(CommandBase.prepareKeyCmd());
        addSequential(new WaitCommand(shootTime));
        addSequential(new ElevatorShooterUntilShotCmd());
        addSequential(new WaitCommand(betweenTime));
        addSequential(new ElevatorShooterUntilShotCmd());
    }
}
