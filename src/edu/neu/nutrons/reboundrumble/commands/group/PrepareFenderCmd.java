package edu.neu.nutrons.reboundrumble.commands.group;

import edu.neu.nutrons.reboundrumble.commands.camera.CamSetPosCmd;
import edu.neu.nutrons.reboundrumble.commands.hood.HoodSetPosCmd;
import edu.neu.nutrons.reboundrumble.commands.shooter.ShooterSetRateCmd;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.StartCommand;

/**
 * Sets both the shooter power and hood position to certain values.
 *
 * @author Ziv
 */
public class PrepareFenderCmd extends CommandGroup {

    public PrepareFenderCmd(double shooterRate, boolean hoodUp, double camPos) {
        addParallel(new StartCommand(new HoodSetPosCmd(hoodUp)));
        addParallel(new StartCommand(new CamSetPosCmd(camPos)));
        addSequential(new StartCommand(new ShooterSetRateCmd(shooterRate)));
    }
}
