package edu.neu.nutrons.lib.fpop.functions;

import edu.neu.nutrons.lib.fpop.Block;
import edu.neu.nutrons.lib.fpop.Number;
import edu.neu.nutrons.lib.fpop.sources.TimerN;

/**
 * Calculates dx/dt. t is a timer by default.
 *
 * @author Ziv
 */
public class Derivative extends Block {

    private Number x;
    private Number t;
    private double lastX= 0;
    private double lastT = 0;
    private double derivative = 0;

    public Derivative(Number x, Number t) {
        this.x = x;
        this.t = t;
    }

    public Derivative(Number x) {
        this(x, new TimerN());
    }

    public void handle() {
        double newX = x.get();
        double newT = t.get();
        derivative = (newX - lastX) / (newT - lastT);
        lastX = newX;
        lastT = newT;
    }

    public double get() {
        return derivative;
    }
}
