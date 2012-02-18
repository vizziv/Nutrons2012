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
public class Derivative {

    private double x = 0;
    private double t = 0;
    private double dx = 0;
    private double dt = 1;
    private Timer timer = new Timer();

    public Derivative() {
        timer.start();
    }

    public void feed(double val) {
        dx = val - x;
        x = val;
        dt = timer.get() - t;
        t += dt;
    }

    public double get() {
        return dx / dt;
    }
}
