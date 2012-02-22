package edu.neu.nutrons.reboundrumble.commands.drivetrain;

import edu.neu.nutrons.lib.Utils;
import edu.neu.nutrons.reboundrumble.commands.TimedEndConditionCmd;
import edu.neu.nutrons.reboundrumble.subsystems.DriveTrain;

/**
 * Turns robot to face target.
 *
 * @deprecated Not going to use so gains aren't suited for it.
 *
 * @author Ziv
 */
public class DTTurnToTargetCmd extends TimedEndConditionCmd {

    private boolean rightSide = false;

    public DTTurnToTargetCmd(boolean rightSide) {
        super(1.0);
        this.rightSide = rightSide;
        requires(dt);
        requires(cam);
        requires(shifter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        shifter.shift(false);
        dtPID.enableCam();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double power = Utils.absPow(dtPID.camOut.get(), .5);
        if(rightSide) {
            dt.driveCar(-power, power); // Will only move right side.
        }
        else {
            dt.driveCar(power, power); // Will only move left side.
        }
    }

    protected boolean returnEndCondition() {
        return Math.abs(posError()) < DriveTrain.CAM_TOLERANCE;
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
}
