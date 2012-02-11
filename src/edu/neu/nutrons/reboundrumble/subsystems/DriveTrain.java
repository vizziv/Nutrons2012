package edu.neu.nutrons.reboundrumble.subsystems;

import edu.neu.nutrons.lib.LinearVictor;
import edu.neu.nutrons.reboundrumble.RobotMap;
import edu.neu.nutrons.reboundrumble.commands.drivetrain.DTManualCheesyCmd;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * 8-wheel tank drive train.
 *
 * @author Ziv
 */
public class DriveTrain extends Subsystem {

    private final double HIGH_GEAR_T_SENS = 1.7;
    private final double LOW_GEAR_T_SENS = 1.36;
    private final double ENCODER_SCALE = 1;

    private LinearVictor lMot1 = new LinearVictor(RobotMap.L_1_DRIVE_MOTOR);
    private LinearVictor lMot2 = new LinearVictor(RobotMap.L_2_DRIVE_MOTOR);
    private LinearVictor rMot1 = new LinearVictor(RobotMap.R_1_DRIVE_MOTOR);
    private LinearVictor rMot2 = new LinearVictor(RobotMap.R_2_DRIVE_MOTOR);
    private Solenoid shifter = new Solenoid(RobotMap.SHIFTER);
    private Encoder lEnc = new Encoder(RobotMap.L_DRIVE_ENC_A, RobotMap.L_DRIVE_ENC_B);
    private Encoder rEnc = new Encoder(RobotMap.R_DRIVE_ENC_A, RobotMap.R_DRIVE_ENC_B);
    private Gyro hGyro = new Gyro(RobotMap.H_GYRO);
    private Gyro vGyro = new Gyro(RobotMap.V_GYRO);
    private double tSens = LOW_GEAR_T_SENS;

    public void initDefaultCommand() {
        setDefaultCommand(new DTManualCheesyCmd());
    }

    public void driveLR(double lPower, double rPower) {
        lMot1.set(lPower);
        lMot2.set(lPower);
        rMot1.set(rPower);
        rMot2.set(rPower);
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

    public double getYaw() {
        return hGyro.getAngle();
    }

    public double getPitch() {
        return vGyro.getAngle();
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
}
