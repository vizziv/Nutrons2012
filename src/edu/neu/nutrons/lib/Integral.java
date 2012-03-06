package edu.neu.nutrons.lib;

/**
 *
 * @author Ziv
 */
public class Integral extends Filter {

    private double sum = 0;

    public void feed(double in) {
        sum += in;
    }

    public double get() {
        return sum;
    }

    public void reset() {
        sum = 0;
    }

    public void set(double sum) {
        this.sum = sum;
    }
}
