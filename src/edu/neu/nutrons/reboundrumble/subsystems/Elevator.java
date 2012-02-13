package edu.neu.nutrons.reboundrumble.subsystems;

import edu.neu.nutrons.reboundrumble.RobotMap;
import edu.neu.nutrons.reboundrumble.commands.elevator.ElevatorManualCmd;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Lifts balls from intake to shooter and measures their squishiness.
 *
 * @author Ziv
 */
public class Elevator extends Subsystem {

    private final double PRESSURE_SCALE = 1.0 / 5.0;

    private final Jaguar fMot = new Jaguar(RobotMap.F_ELEV_MOTOR);
    private final Jaguar bMot = new Jaguar(RobotMap.B_ELEV_MOTOR);
    private final AnalogChannel pressure = new AnalogChannel(RobotMap.PRESSURE);

    public void initDefaultCommand() {
        setDefaultCommand(new ElevatorManualCmd());
    }

    public double getPressure() {
        // TODO: figure out if this needs scaling, smoothing and/or other jazz.
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
