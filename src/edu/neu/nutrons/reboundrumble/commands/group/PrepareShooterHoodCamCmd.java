package edu.neu.nutrons.reboundrumble.commands.group;

import edu.neu.nutrons.reboundrumble.commands.camera.CamSetPosCmd;
import edu.neu.nutrons.reboundrumble.commands.hood.HoodSetPosCmd;
import edu.neu.nutrons.reboundrumble.commands.shooter.ShooterSetRateCmd;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Sets both the shooter power and hood position to certain values.
 *
 * @author Ziv
 */
public class PrepareShooterHoodCamCmd extends CommandGroup {

    public PrepareShooterHoodCamCmd(double shooterRate, boolean hoodUp, double camPos) {
        addParallel(new HoodSetPosCmd(hoodUp));
        addParallel(new CamSetPosCmd(camPos));
        addSequential(new ShooterSetRateCmd(shooterRate));
    }
}
