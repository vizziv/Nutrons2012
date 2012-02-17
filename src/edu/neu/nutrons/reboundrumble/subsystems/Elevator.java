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
    public static final double fPOWER = 0.4;
    public static final double bPOWER = 0.8;
    // Actual robot parts.
    private final Jaguar fMot = new Jaguar(RobotMap.F_ELEV_MOTOR);
    private final Jaguar bMot = new Jaguar(RobotMap.B_ELEV_MOTOR);
    private final AnalogChannel pressure = new AnalogChannel(RobotMap.PRESSURE);

    public void initDefaultCommand() {
        setDefaultCommand(new ElevatorIdleCmd());
    }

    public double getPressure() {
        // TODO: figure out if this needs scaling, smoothing and/or other jazz.
        return PRESSURE_SCALE * pressure.getVoltage();
    }


    public void setPowerFB(double fPower, double bPower) {
        // TODO: confirm signs.
        fMot.set(-fPower);
        bMot.set(bPower);
    }

    public void stop() {
        setPowerFB(0, 0);
    }


}
