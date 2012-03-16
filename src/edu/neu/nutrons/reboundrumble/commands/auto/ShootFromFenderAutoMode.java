package edu.neu.nutrons.reboundrumble.commands.auto;

import edu.neu.nutrons.lib.LoopCmd;
import edu.neu.nutrons.lib.StartThenWaitCmd;
import edu.neu.nutrons.reboundrumble.commands.CommandBase;
import edu.neu.nutrons.reboundrumble.commands.drivetrain.DTFenderCreepToTargetCmd;
import edu.neu.nutrons.reboundrumble.commands.elevator.ElevatorShootOneBallCmd;
import edu.neu.nutrons.reboundrumble.subsystems.Elevator;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitUntilCommand;

/**
 * Shoots balls from the key after a specified delay.
 *
 * @author Ziv
 */
public class ShootFromFenderAutoMode extends CommandGroup {

    public ShootFromFenderAutoMode(double moveTime, double shootTime) {
        addSequential(CommandBase.prepareFender);
        addSequential(new WaitUntilCommand(moveTime));
        addSequential(new DTFenderCreepToTargetCmd(6.0));
        addSequential(new WaitUntilCommand(shootTime));
        addSequential(new LoopCmd(new StartThenWaitCmd(new ElevatorShootOneBallCmd(), Elevator.SHOOTING_DELAY)));
    }
}
