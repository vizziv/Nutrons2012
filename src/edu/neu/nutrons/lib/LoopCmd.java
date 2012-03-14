package edu.neu.nutrons.lib;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Loops a given command forever. Will interrupt the loop but not the lopped
 * command itself if that command is not interruptible.
 *
 * @author Ziv
 */
public class LoopCmd extends Command {

    private Command cmd;

    public LoopCmd(Command cmd) {
        this.cmd = cmd;
    }

    protected void initialize() {
        cmd.start();
    }

    protected void execute() {
        if(!cmd.isRunning()) {
            cmd.start();
        }
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        if(cmd.isInterruptible()) {
            cmd.cancel();
        }
    }

    protected void interrupted() {
        end();
    }
}
