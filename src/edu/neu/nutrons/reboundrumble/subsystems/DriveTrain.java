package edu.neu.nutrons.reboundrumble.subsystems;

import edu.neu.nutrons.lib.LinearVictor;
import edu.neu.nutrons.lib.RelativeGyro;
import edu.neu.nutrons.reboundrumble.RobotMap;
import edu.neu.nutrons.reboundrumble.commands.drivetrain.DTManualCheesyCmd;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSource;
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
    private final double ENC_SCALE = 0.01265;
    private final double LEFT_SCALE = 1.0;
    private final double RIGHT_SCALE = 1.0;
    public static final double DIS_SETTLE_TIME = 1.0;
    public static final double YAW_SETTLE_TIME = 1.0;
    public static final double PITCH_SETTLE_TIME = 1.0;
    public static final double CAM_SETTLE_TIME = 1.0;
    public static final double SMOOTHING_CYCLES = 40;
    public static final double CAM_TOLERANCE = 0.0125;

    // Actual robot parts.
    private final LinearVictor lMot = new LinearVictor(RobotMap.L_DRIVE_MOTOR);
    private final LinearVictor rMot = new LinearVictor(RobotMap.R_DRIVE_MOTOR);
    private final Encoder lEnc = new Encoder(RobotMap.L_DRIVE_ENC_A, RobotMap.L_DRIVE_ENC_B);
    private final Encoder rEnc = new Encoder(RobotMap.R_DRIVE_ENC_A, RobotMap.R_DRIVE_ENC_B);
    public RelativeEncoderAvg disEncAvg = new RelativeEncoderAvg();
    public final RelativeGyro yawGyro = new RelativeGyro(RobotMap.H_GYRO);
    public final RelativeGyro pitchGyro = new RelativeGyro(RobotMap.V_GYRO);

    // Other variables.
    private double tSens = LOW_GEAR_T_SENS;

    public DriveTrain() {
        lEnc.start();
        rEnc.start();
    }

    public void initDefaultCommand() {
        setDefaultCommand(new DTManualCheesyCmd());
    }

    public void driveLR(double lPower, double rPower) {
        lMot.set(LEFT_SCALE * -lPower);
        rMot.set(RIGHT_SCALE * rPower);
    }

    public void setTSens(boolean highGear) {
        if(highGear) {
            tSens = HIGH_GEAR_T_SENS;
        }
        else {
            tSens = LOW_GEAR_T_SENS;
        }
    }

    public double getLeftPos() {
        return ENC_SCALE * lEnc.get();
    }

    public double getRightPos() {
        return ENC_SCALE * rEnc.get();
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

    public final class RelativeEncoderAvg implements PIDSource {
        private double initialSum;
        public double get() {
            return getRightPos() - initialSum; //(getLeftPos() + getRightPos() - initialSum) / 2.0;
        }
        public double getAbsolute() {
            return get() + initialSum;
        }
        public double pidGet() {
            return get();
        }
        public void reset() {
            initialSum = getRightPos(); //getLeftPos() + getRightPos();
        }
    }
}
