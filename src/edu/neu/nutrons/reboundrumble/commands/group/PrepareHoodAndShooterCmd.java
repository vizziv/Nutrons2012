package edu.neu.nutrons.reboundrumble.commands.group;

import edu.neu.nutrons.reboundrumble.commands.hood.HoodSetPosCmd;
import edu.neu.nutrons.reboundrumble.commands.shooter.ShooterSetRateCmd;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Sets both the shooter power and hood position to certain values.
 *
 * @author Ziv
 */
public class PrepareHoodAndShooterCmd extends CommandGroup {

    public PrepareHoodAndShooterCmd(double shooterRate, boolean hoodUp) {
        addParallel(new HoodSetPosCmd(hoodUp));
        addSequential(new ShooterSetRateCmd(shooterRate));
    }
}
