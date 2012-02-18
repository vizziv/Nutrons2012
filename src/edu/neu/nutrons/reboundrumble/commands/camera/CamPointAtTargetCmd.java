package edu.neu.nutrons.reboundrumble.commands.camera;

import edu.neu.nutrons.reboundrumble.commands.CommandBase;

/**
 * Turns camera until target is in the center.
 *
 * @author Ziv
 */
public class CamPointAtTargetCmd extends CommandBase {

    public CamPointAtTargetCmd() {
    //    requires(cam);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
      //  cam.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
   //     return cam.tracker.getTarget1().isNull();
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    //    cam.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
