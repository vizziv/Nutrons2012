package edu.neu.nutrons.lib;

import edu.wpi.first.wpilibj.Timer;

/**
 * Takes the derivative of a signal.
 *
 * @author Ziv
 */
public class Derivative implements Filter {

    private double prevV = 0;
    private double dv = 0;
    private double prevT = 0;
    private double dt = 1;
    private Timer t = new Timer();

    public Derivative() {
        t.start();
        prevT = t.get();
    }

    public void feed(double val) {
        dv = val - prevV;
        prevV = val;
        dt = t.get() - prevT;
        prevT += dt;
    }

    public double get() {
        return dv / dt;
    }
}
