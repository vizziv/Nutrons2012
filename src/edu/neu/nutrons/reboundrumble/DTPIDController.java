package edu.neu.nutrons.reboundrumble;

import edu.neu.nutrons.reboundrumble.commands.CommandBase;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.smartdashboard.SendablePIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Controls automatic drive train movements with multiple PID controllers. Like
 * an OI, but methods
 *
 * @author Ziv
 */
public class DTPIDController {

    // TODO: tune PIDs.
    private static final double disKp = 0;
    private static final double disKi = 0;
    private static final double disKd = 0;
    private static final double yawKp = 0;
    private static final double yawKi = 0;
    private static final double yawKd = 0;
    private static final double pitchKp = 0;
    private static final double pitchKi = 0;
    private static final double pitchKd = 0;

    // PID-related objects.
    public Output disOut = new Output();
    public Output yawOut = new Output();
    public Output pitchOut = new Output();
    private SendablePIDController disPID = new SendablePIDController(disKp, disKi, disKd,
                                            CommandBase.dt.disEncAvg, disOut);
    private SendablePIDController yawPID = new SendablePIDController(yawKp, yawKi, yawKd,
                                            CommandBase.dt.yawGyro, yawOut);
    private SendablePIDController pitchPID = new SendablePIDController(pitchKp, pitchKi, pitchKd,
                                            CommandBase.dt.pitchGyro, pitchOut);

    public DTPIDController() {
        yawPID.setInputRange(-180, 180);
        yawPID.setContinuous();
        SmartDashboard.putData("Distance PID", disPID);
        SmartDashboard.putData("Yaw PID", yawPID);
        SmartDashboard.putData("Pitch PID", pitchPID);
    }

    public void enableDistance(double setpoint) {
        disPID.enable();
        disPID.setSetpoint(setpoint);
    }

    public void enableYaw(double setpoint) {
        yawPID.enable();
        yawPID.setSetpoint(setpoint);
    }

    public void enablePitch(double setpoint) {
        pitchPID.enable();
        pitchPID.setSetpoint(setpoint);
    }

    public void disableDistance() {
        disPID.disable();
    }

    public void disableYaw() {
        yawPID.disable();
    }

    public void disablePitch() {
        pitchPID.disable();
    }

    public void disableAll() {
        disablePitch();
        disableYaw();
        disablePitch();
    }

    public double getDisSetpoint() {
        return disPID.getSetpoint();
    }

    public double getYawSetpoint() {
        return yawPID.getSetpoint();
    }

    public double getPitchSetpoint() {
        return pitchPID.getSetpoint();
    }

    public final class Output implements PIDOutput {
        private double output = 0;
        public void pidWrite(double output) {
            this.output = output;
        }
        public double get() {
            return output;
        }
    }
}
