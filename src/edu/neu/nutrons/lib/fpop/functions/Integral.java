package edu.neu.nutrons.lib.fpop.functions;

import edu.neu.nutrons.lib.fpop.Number;
import edu.neu.nutrons.lib.fpop.sources.TimerN;

/**
 * Calculates Sxdt. t is a timer by default. (S is an integral sign.)
 *
 * @author Ziv
 */
public class Integral {

    private Number x;
    private Number t;
    private double lastT = 0;
    private double integral = 0;

    public Integral(Number x, Number t) {
        this.x = x;
        this.t = t;
    }

    public Integral(Number x) {
        this(x, new TimerN());
    }

    public void handle() {
        double newT = t.get();
        integral += x.get() * (newT - lastT);
        lastT = newT;
    }

    public double get() {
        return integral;
    }
}
