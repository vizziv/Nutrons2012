package edu.neu.nutrons.reboundrumble.commands.intake;

/**
 *
 * @author Ziv
 */
public class IntakeSetCmdUnint extends IntakeSetCmd {

    public IntakeSetCmdUnint(boolean dropped, boolean intaking) {
        super(dropped, intaking);
        setInterruptible(false);
    }
}
