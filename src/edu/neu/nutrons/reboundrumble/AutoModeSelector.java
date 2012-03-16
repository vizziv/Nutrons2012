package edu.neu.nutrons.reboundrumble;

import edu.neu.nutrons.lib.PulseTriggerBoolean;
import edu.neu.nutrons.lib.Utils;
import edu.neu.nutrons.reboundrumble.commands.auto.ShootFromFenderAutoMode;
import edu.neu.nutrons.reboundrumble.commands.auto.ShootFromKeyAutoMode;
import edu.neu.nutrons.reboundrumble.commands.drivetrain.DTManualCheesyCmd;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Used to select an autonomous mode.
 *
 * @author Ziv
 */
public class AutoModeSelector {

    // Constants.
    private final int KEY = 1;
    private final int FENDER = 2;
    private final int NUM_MODES = 3;

    // Other variables.
    private Joystick js;
    private PulseTriggerBoolean incMode = new PulseTriggerBoolean();
    private PulseTriggerBoolean decMode = new PulseTriggerBoolean();
    private PulseTriggerBoolean incShootWait = new PulseTriggerBoolean();
    private PulseTriggerBoolean decShootWait = new PulseTriggerBoolean();
    private PulseTriggerBoolean incMoveWait = new PulseTriggerBoolean();
    private PulseTriggerBoolean decMoveWait = new PulseTriggerBoolean();
    private int mode = 0;
    private int shootWait = 0;
    private int moveWait = 0;

    public AutoModeSelector(Joystick js) {
        this.js = js;
    }

    public void runSelection() {
        // Get button data.
        incMode.feed(js.getRawButton(6));
        decMode.feed(js.getRawButton(5));
        incShootWait.feed(js.getRawButton(1));
        decShootWait.feed(js.getRawButton(2));
        incMoveWait.feed(js.getRawButton(4));
        decMoveWait.feed(js.getRawButton(3));
        // Change parameters accordingly.
        boolean im = incMode.get();
        boolean dm = decMode.get();
        boolean isw = incShootWait.get();
        boolean dsw = decShootWait.get();
        boolean imw = incMoveWait.get();
        boolean dmw = decMoveWait.get();
        mode = (mode + Utils.toInt(im) + Utils.toInt(dm)) % NUM_MODES;
        shootWait = (shootWait + Utils.toInt(isw) + Utils.toInt(dsw)) % 15;
        moveWait = (moveWait + Utils.toInt(imw) + Utils.toInt(dmw)) % 15;
        if(im || dm || isw || dsw || imw || dmw) {
            String display = "Mode = " + mode + ", Shoot delay = " + shootWait +
                             ", Move delay = " + moveWait;
            System.out.println(display);
        }
    }

    private String getModeName(int mode) {
        switch(mode) {
            case KEY:
                return "Key";
            case FENDER:
                return "Fender";
            default:
                return "None";
        }
    }

    public Command getAutoMode() {
        Command autoMode;
        switch(mode) {
            case KEY:
                autoMode = new ShootFromKeyAutoMode(shootWait);
                break;
            case FENDER:
                autoMode = new ShootFromFenderAutoMode(moveWait, shootWait);
                break;
            default:
                autoMode = new DTManualCheesyCmd();
        }
        return autoMode;
    }
}
