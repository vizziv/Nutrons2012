package edu.neu.nutrons.lib;

/**
 * Filters a series of numbers. (E.g., moving average, derivative.)
 *
 * @author Ziv
 */
public interface Filter {

    public void feed(double in);
    public double get();
}
