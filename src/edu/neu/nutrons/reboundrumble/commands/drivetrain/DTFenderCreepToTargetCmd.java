package edu.neu.nutrons.reboundrumble.commands.drivetrain;

import edu.neu.nutrons.lib.Utils;
import edu.neu.nutrons.reboundrumble.commands.CommandBase;
import edu.neu.nutrons.reboundrumble.subsystems.DriveTrain;
import edu.neu.nutrons.reboundrumble.vision.Target;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Turns robot to face target.
 *
 * @author Ziv
 */
public class DTFenderCreepToTargetCmd extends CommandBase {

    private static double kp = 1.0;

    public DTFenderCreepToTargetCmd(double timeout) {
        setTimeout(timeout);
        requires(dt);
        requires(cam);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        dtPID.enableCam();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        // Drive the robot slower as we get closer to the fender.
        double targetWidth = cam.tracker.getTarget1().rawBboxWidth;
        double wheel = Utils.absPow(targetWidth * dtPID.camOut.get(), .65);
        SmartDashboard.putDouble("Cam turning power", wheel);
        double throttle = kp * Utils.absPow(disError(), .42);
        throttle = Utils.limit(throttle, -DriveTrain.MAX_CREEP_POW, DriveTrain.MAX_CREEP_POW);
        dt.driveCar(throttle, Math.abs(Utils.absPow(throttle, .3)) * wheel);
    }

    protected boolean isFinished() {
        // No absolute value; if we pass it, still stop.
        return isTimedOut() || disError() < DriveTrain.FENDER_CREEP_TOLERANCE;
    }

    protected boolean override() {
        return oi.getDriveManual();
    }

    // Called once after isFinished returns true
    protected void end() {
        dt.stop();
        dtPID.disableCam();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }

    private double posError() {
        return cam.tracker.getTarget1().centerX;
    }

    private double disError() {
        return (1 - cam.tracker.getTarget1().rawBboxWidth / Target.FENDER_TARGET_WIDTH);
    }
}
