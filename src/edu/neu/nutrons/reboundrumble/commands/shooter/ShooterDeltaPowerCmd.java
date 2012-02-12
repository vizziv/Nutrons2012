package edu.neu.nutrons.reboundrumble.commands.shooter;

/**
 * Changes the power sent to the shooter. Open loop.
 *
 * @author Nutrons Pros
 */
public class ShooterDeltaPowerCmd extends ShooterSetPowerCmd {

    private double deltaPower = 0;

    public ShooterDeltaPowerCmd(double deltaPower) {
        super(0); // Calls requires(shooter).
    }

    protected void initialize() {
        power = shooter.getPower() + deltaPower;
        super.initialize();
    }
}
