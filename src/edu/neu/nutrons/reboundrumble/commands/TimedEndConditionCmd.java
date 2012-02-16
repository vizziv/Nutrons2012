package edu.neu.nutrons.reboundrumble.commands;

import edu.neu.nutrons.lib.PulseTriggerBoolean;
import edu.wpi.first.wpilibj.Timer;

/**
 * A command type that ends only when an end condition has been true for a
 * certain amount of time. For example, a PID loop should end not just when the
 * position is a small acceptable interval but when it has been in that interval
 * for a good amount of time.
 *
 * @author Ziv
 */
public abstract class TimedEndConditionCmd extends CommandBase {

    private Timer t = new Timer();
    private double settleTime = 0;
    private PulseTriggerBoolean startEnding = new PulseTriggerBoolean();

    public TimedEndConditionCmd(double settleTime) {
        this.settleTime = settleTime;
    }

    // Child class uses this INSTEAD of isFinished();
    protected abstract boolean returnEndCondition();

    // Called just before this Command runs the first time
    protected void initialize() {
        t.stop();
        t.reset();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected final boolean isFinished() {
        boolean ending = returnEndCondition();
        startEnding.set(ending);
        if(startEnding.get()) {
            t.start();
        }
        else if(!ending) {
            t.stop();
            t.reset();
        }
        return ending && settleTime < t.get();
    }
}
