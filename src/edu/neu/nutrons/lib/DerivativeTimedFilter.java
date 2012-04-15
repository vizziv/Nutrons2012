/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.nutrons.lib;

import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author Ziv
 */
public class DerivativeTimedFilter extends Filter {

    private double x = 0;
    private double t = 0;
    private double dx = 0;
    private double dt = 1;
    private Timer timer = new Timer();

    public DerivativeTimedFilter() {
        timer.start();
    }

    public void feed(double in) {
        dx = in - x;
        x = in;
        dt = timer.get() - t;
        t += dt;
    }

    public double get() {
        return dx / dt;
    }
}
