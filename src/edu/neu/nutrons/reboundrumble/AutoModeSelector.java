package edu.neu.nutrons.reboundrumble;

import edu.neu.nutrons.lib.PulseTriggerBoolean;
import edu.neu.nutrons.lib.Utils;
import edu.neu.nutrons.reboundrumble.commands.auto.ShootFromFenderAutoMode;
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
    private PulseTriggerBoolean incShootDelay = new PulseTriggerBoolean();
    private PulseTriggerBoolean decShootDelay = new PulseTriggerBoolean();
    private int mode = 1;
    private int shootDelay = 0;

    public AutoModeSelector(Joystick js) {
        this.js = js;
    }

    public void runSelection() {
        // Get button data.
        incMode.feed(js.getRawButton(6));
        decMode.feed(js.getRawButton(5));
        incShootDelay.feed(js.getRawButton(1));
        decShootDelay.feed(js.getRawButton(2));
        // Change parameters accordingly.
        boolean im = incMode.get();
        boolean dm = decMode.get();
        boolean is = incShootDelay.get();
        boolean ds = decShootDelay.get();
        mode = (int)Utils.modulo(mode + Utils.toInt(im) - Utils.toInt(dm), NUM_MODES);
        shootDelay = (int)Utils.modulo(shootDelay + Utils.toInt(is) - Utils.toInt(ds), 15);
        if(im || dm || is || ds) {
            DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser2, 1, "Mode: " + getModeName(mode) + "     ");
            DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser3, 1, "Delay: " + shootDelay + "     ");
            DriverStationLCD.getInstance().updateLCD();
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
                autoMode = new ShootFromKeyHackyAutoMode(shootDelay);
                break;
            case FENDER:
                autoMode = new ShootFromFenderAutoMode(shootDelay);
                break;
            default:
                autoMode = new DTManualCheesyCmd();
        }
        return autoMode;
    }
}
