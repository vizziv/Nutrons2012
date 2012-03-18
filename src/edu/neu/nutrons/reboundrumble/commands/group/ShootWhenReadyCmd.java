/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.nutrons.reboundrumble.commands.group;

import edu.neu.nutrons.reboundrumble.commands.elevator.ElevatorShooterCmd;
import edu.neu.nutrons.reboundrumble.commands.shooter.WaitForShooterCmd;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author Ziv
 */
public class ShootWhenReadyCmd extends CommandGroup {

    public ShootWhenReadyCmd() {
        addSequential(new WaitForShooterCmd());
        addSequential(new ElevatorShooterCmd(), 0.08);//ElevatorShootOneBallCmd());
    }
}
