package edu.neu.nutrons.lib;

import edu.wpi.first.wpilibj.Gyro;

/**
 * This makes it easy to read and "reset" a gyro without any extra logic, while
 * still keeping the match wide orientation.
 *
 * @author Tom
 */
public class RelativeGyro extends Gyro {

    double curAngle = 0;

    public RelativeGyro(int port){
        super(port);
    }

    public void reset(){
        curAngle = super.getAngle();
    }

    public double getAngle(){
        return super.getAngle() - curAngle;
    }

    public double getAbsoluteAngle(){
        return super.getAngle();
    }

    public void resetAbsolute(){
        super.reset();
    }
}
