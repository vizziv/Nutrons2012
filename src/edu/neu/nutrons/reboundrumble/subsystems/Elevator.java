package edu.neu.nutrons.reboundrumble.subsystems;

import edu.neu.nutrons.reboundrumble.RobotMap;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Lifts balls from intake to shooter and measures their squishiness.
 *
 * @author Ziv
 */
public class Elevator extends Subsystem {

    private Jaguar fMot = new Jaguar(RobotMap.F_ELEV_MOTOR);
    private Jaguar bMot = new Jaguar(RobotMap.B_ELEV_MOTOR);
    private AnalogChannel pressure = new AnalogChannel(RobotMap.PRESSURE);

    public void initDefaultCommand() {
    }

    public void setPowerF(double power) {
        fMot.set(power);
    }

    public void setPowerB(double power) {
        bMot.set(power);
    }

    public double getPressure() {
        // TODO: figure out if this needs scaling, smoothing and/or other jazz.
        return (pressure.getVoltage() / 5.0);
    }

    public void setPowerFB(double fPower, double bPower) {
        setPowerF(fPower);
        setPowerB(bPower);
    }

    public void stop() {
        setPowerFB(0, 0);
    }
}
