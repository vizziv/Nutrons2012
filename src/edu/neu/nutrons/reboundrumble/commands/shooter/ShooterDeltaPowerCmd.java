package edu.neu.nutrons.reboundrumble.commands.shooter;

import edu.neu.nutrons.reboundrumble.commands.CommandBase;

/**
 * Changes the power sent to the shooter. Open loop.
 *
 * @author Nutrons Pros
 */
public class ShooterDeltaPowerCmd extends CommandBase {

    private double deltaPower = 0;
    private double initPower = 0;
    private double power = 0;
    private int sign = 1;

    public ShooterDeltaPowerCmd(double deltaPower) {
        this.deltaPower = deltaPower;
        sign  = deltaPower > 0 ? 1 : -1;
        requires(shooter);
    }

    protected void initialize() {
        shooter.disable();
        initPower = shooter.getPower();
        power = initPower;
    }

    protected void execute() {
        shooter.setPower(initPower + deltaPower);
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
