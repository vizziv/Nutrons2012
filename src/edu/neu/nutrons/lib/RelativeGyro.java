package edu.neu.nutrons.lib;

import edu.wpi.first.wpilibj.Gyro;

/**
 * Easily resettable gyro that keeps track of absolute angle. Output is in
 * interval [-180, 180], though absolute output has no bounds (as normal).
 *
 * @author Tom
 */
public class RelativeGyro extends Gyro {

    private double curAngle = 0;

    public RelativeGyro(int port){
        super(port);
    }

    public void reset(){
        curAngle = super.getAngle();
    }

    public double getAngle(){
        double angle = super.getAngle() - curAngle;
        // Shift by 180, normalize to [0, 360], then shift back to [-180, 180].
        return Utils.modulo(angle + 180, 360) - 180;
    }

    public double getAbsoluteAngle(){
        return super.getAngle();
    }

    public void resetAbsolute(){
        super.reset();
    }
}
