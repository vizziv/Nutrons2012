package edu.neu.nutrons.lib;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Starts a given command, then waits for a given time.
 *
 * @author Ziv
 */
public class StartThenWaitCmd extends Command {

    private Command cmd;

    public StartThenWaitCmd(Command cmd, double delay) {
        this.cmd = cmd;
        setTimeout(delay);
    }

    protected void initialize() {
        cmd.start();
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
