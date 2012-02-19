package edu.neu.nutrons.reboundrumble.subsystems;

import edu.neu.nutrons.reboundrumble.RobotMap;
import edu.neu.nutrons.reboundrumble.commands.elevator.ElevatorIdleCmd;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Lifts balls from intake to shooter and measures their squishiness.
 *
 * @author Ziv
 */
public class Elevator extends Subsystem {

    // Constants.
    private final double PRESSURE_SCALE = 1.0;
    public static final double F_POWER = 0.45;
    public static final double B_POWER = 0.85;

    // Actual robot parts.
    private final Jaguar fMot = new Jaguar(RobotMap.F_ELEV_MOTOR);
    private final Jaguar bMot = new Jaguar(RobotMap.B_ELEV_MOTOR);
    private final AnalogChannel pressure = new AnalogChannel(RobotMap.SQUISHINESS);

    public void initDefaultCommand() {
        setDefaultCommand(new ElevatorIdleCmd());
    }

    public double getPressure() {
        return PRESSURE_SCALE * pressure.getVoltage();
    }

    public void setPowerFB(double fPower, double bPower) {
        fMot.set(-fPower);
        bMot.set(bPower);
    }

    public void stop() {
        setPowerFB(0, 0);
    }
}
