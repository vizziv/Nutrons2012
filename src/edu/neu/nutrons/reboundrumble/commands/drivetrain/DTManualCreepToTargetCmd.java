package edu.neu.nutrons.reboundrumble.commands.drivetrain;

import edu.neu.nutrons.lib.Utils;
import edu.neu.nutrons.reboundrumble.commands.TimedEndConditionCmd;
import edu.neu.nutrons.reboundrumble.subsystems.DriveTrain;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Turns robot to face target.
 *
 * @author Ziv
 */
public class DTManualCreepToTargetCmd extends TimedEndConditionCmd {

    public DTManualCreepToTargetCmd() {
        super(DriveTrain.CAM_SETTLE_TIME);
        requires(dt);
        requires(cam);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        dtPID.enableCam();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double wheel = Utils.absPow(cam.tracker.getTarget1().rawBboxWidth * dtPID.camOut.get(), .65);
        SmartDashboard.putDouble("Cam turning power", wheel);
        dt.driveCar(DriveTrain.MAX_CREEP_POW * oi.getDriveThrottle(),
                    Math.abs(Utils.absPow(oi.getDriveThrottle(), .3)) * wheel);
    }

    protected boolean returnEndCondition() {
        return Math.abs(posError()) < DriveTrain.CAM_TOLERANCE;
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
}
