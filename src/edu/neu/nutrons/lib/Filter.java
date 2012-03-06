package edu.neu.nutrons.lib;

/**
 * Filters a series of numbers. (E.g., moving average, derivative.)
 *
 * @author Ziv
 */
public abstract class Filter {

    public abstract void feed(double in);
    public abstract double get();

    public void reset() {}
}
