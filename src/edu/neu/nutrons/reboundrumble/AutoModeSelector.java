package edu.neu.nutrons.reboundrumble;

import edu.neu.nutrons.lib.PulseTriggerBoolean;
import edu.neu.nutrons.lib.Utils;
import edu.neu.nutrons.reboundrumble.commands.auto.ShootFromFenderAutoMode;
import edu.neu.nutrons.reboundrumble.commands.auto.ShootFromKeyAutoMode;
import edu.neu.nutrons.reboundrumble.commands.auto.ShootFromKeyHackyAutoMode;
import edu.neu.nutrons.reboundrumble.commands.drivetrain.DTManualCheesyCmd;
import edu.wpi.first.wpilibj.DriverStationLCD;
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
    private int shootTime = 0;
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
        // Change parameters accordingly.
        boolean im = incMode.get();
        boolean dm = decMode.get();
        boolean isw = incShootWait.get();
        boolean dsw = decShootWait.get();
        mode = (mode + Utils.toInt(im) - Utils.toInt(dm)) % NUM_MODES;
        shootTime = (shootTime + Utils.toInt(isw) - Utils.toInt(dsw)) % 15;
        if(im || dm || isw || dsw) {
            DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser2, 1, getAutoModeString());
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
                autoMode = new ShootFromKeyHackyAutoMode(shootTime);
                break;
            case FENDER:
                autoMode = new ShootFromFenderAutoMode(shootTime);
                break;
            default:
                autoMode = new DTManualCheesyCmd();
        }
        return autoMode;
    }

    public String getAutoModeString() {
        return "Mode = " + getModeName(mode) + ", Shoot delay = " + shootTime + ", Move delay = " + moveWait;
    }
}
