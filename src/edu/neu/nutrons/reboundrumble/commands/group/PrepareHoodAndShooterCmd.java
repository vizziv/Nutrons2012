/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.nutrons.reboundrumble.commands.group;

import edu.neu.nutrons.reboundrumble.commands.hood.HoodSetPosCmd;
import edu.neu.nutrons.reboundrumble.commands.shooter.ShooterMaintainPowerCmd;
import edu.neu.nutrons.reboundrumble.commands.shooter.ShooterSetPowerCmd;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author Ziv
 */
public class PrepareHoodAndShooterCmd extends CommandGroup {

    public PrepareHoodAndShooterCmd(double shooterPower, double hoodPos) {
        addParallel(new HoodSetPosCmd(hoodPos));
        addSequential(new ShooterSetPowerCmd(shooterPower));
        addSequential(new ShooterMaintainPowerCmd());
    }
}
