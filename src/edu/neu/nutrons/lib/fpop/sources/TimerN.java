package edu.neu.nutrons.lib.fpop.sources;

import edu.neu.nutrons.lib.fpop.Number;
import edu.wpi.first.wpilibj.Timer;

/**
 * WPILib Timer class with Number interface.
 *
 * @author Ziv
 */
public class TimerN extends Timer implements Number {

    public TimerN() {
        start();
    }
}
