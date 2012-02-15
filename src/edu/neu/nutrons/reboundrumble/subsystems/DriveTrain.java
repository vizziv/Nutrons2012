package edu.neu.nutrons.reboundrumble.subsystems;

import edu.neu.nutrons.lib.LinearVictor;
import edu.neu.nutrons.lib.RelativeGyro;
import edu.neu.nutrons.reboundrumble.RobotMap;
import edu.neu.nutrons.reboundrumble.commands.drivetrain.DTManualLRCmd;
import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * 8-wheel tank drive train.
 *
 * @author Ziv
 */
public class DriveTrain extends Subsystem {

    // Constants.
    private final double HIGH_GEAR_T_SENS = 1.7;
    private final double LOW_GEAR_T_SENS = 1.36;
    private final double ENCODER_SCALE = 1.0;

    // Actual robot parts.
    private final LinearVictor lMot = new LinearVictor(RobotMap.L_DRIVE_MOTOR);
    private final LinearVictor rMot = new LinearVictor(RobotMap.R_DRIVE_MOTOR);
    private final Solenoid shifter = new Solenoid(RobotMap.SHIFTER);
    private final Encoder lEnc = new Encoder(RobotMap.L_DRIVE_ENC_A, RobotMap.L_DRIVE_ENC_B,
                                       false, CounterBase.EncodingType.k1X);
    private final Encoder rEnc = new Encoder(RobotMap.R_DRIVE_ENC_A, RobotMap.R_DRIVE_ENC_B,
                                       false, CounterBase.EncodingType.k1X);
    public RelativeEncoderAvg disEncAvg = new RelativeEncoderAvg();
    public final RelativeGyro yawGyro = new RelativeGyro(RobotMap.H_GYRO);
    public final RelativeGyro pitchGyro = new RelativeGyro(RobotMap.V_GYRO);

    // Other variables.
    private double tSens = LOW_GEAR_T_SENS;

    public void initDefaultCommand() {
        setDefaultCommand(new DTManualLRCmd());
    }

    public void driveLR(double lPower, double rPower) {
        lMot.set(-lPower);
        rMot.set(rPower);
    }

    public void shift(boolean highGear) {
        if(highGear) {
            tSens = HIGH_GEAR_T_SENS;
        }
        else {
            tSens = LOW_GEAR_T_SENS;
        }
        shifter.set(highGear);
    }

    public double getLeftPos() {
        return ENCODER_SCALE * lEnc.get();
    }

    public double getRightPos() {
        return ENCODER_SCALE * rEnc.get();
    }

    public void stop() {
        driveLR(0, 0);
    }

    public void driveCar(double throttle, double wheel) {
        driveLR(throttle + wheel, throttle - wheel);
    }

    public void driveCheesy(double throttle, double wheel, boolean quickTurn) {
        double angular_power;
        double overPower;
        double rPower;
        double lPower;
        if(quickTurn) {
            overPower = 1.0;
            angular_power = wheel;
        }
        else {
            overPower = 0.0;
            angular_power = Math.abs(throttle) * wheel * tSens;
        }
        rPower = lPower = throttle;
        lPower += angular_power;
        rPower -= angular_power;
        if(lPower > 1.0) {
            rPower -= overPower * (lPower - 1.0);
            lPower = 1.0;
        }
        else if(rPower > 1.0) {
            lPower -= overPower * (rPower - 1.0);
            rPower = 1.0;
        }
        else if(lPower < -1.0) {
            rPower += overPower * (-1.0 - lPower);
            lPower = -1.0;
        }
        else if(rPower < -1.0) {
            lPower += overPower * (-1.0 - rPower);
            rPower = -1.0;
        }
        driveLR(lPower, rPower);
    }

    public double getAvgPos() {
        return disEncAvg.pidGet();
    }

    public final class RelativeEncoderAvg implements PIDSource {
        private double initialSum;
        public double get() {
            return (getLeftPos() + getRightPos() - initialSum) / 2.0;
        }
        public double pidGet() {
            return get();
        }
        public void reset() {
            initialSum = getLeftPos() + getRightPos();
        }
    }
}
