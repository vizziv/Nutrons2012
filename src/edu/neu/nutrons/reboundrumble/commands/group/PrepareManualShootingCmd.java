package edu.neu.nutrons.reboundrumble.commands.group;

import edu.neu.nutrons.reboundrumble.commands.camera.CamSetPosCmd;
import edu.neu.nutrons.reboundrumble.commands.hood.HoodSetPosCmd;
import edu.neu.nutrons.reboundrumble.commands.shooter.ShooterSetPowerCmd;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.StartCommand;

/**
 * Sets both the shooter power and hood position to certain values.
 *
 * @author Ziv
 */
public class PrepareManualShootingCmd extends CommandGroup {

    public PrepareManualShootingCmd(double shooterPower, boolean hoodUp, double camPos) {
        addParallel(new StartCommand(new HoodSetPosCmd(hoodUp)));
        addParallel(new StartCommand(new CamSetPosCmd(camPos)));
        addSequential(new StartCommand(new ShooterSetPowerCmd(shooterPower)));
    }
}
