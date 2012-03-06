package edu.neu.nutrons.lib;

/**
 * Smooths out an input stream of integers by averaging the latest values.
 *
 * @author Ziv
 */
public class MovingAverage extends Filter {

    private double[] data;
    private double sum = 0;
    private int length;
    private int i = 0;

    public MovingAverage(int length) {
        data = new double[length];
        this.length = length;
    }

    public void feed(double in) {
        sum += in - data[i];
        data[i] = in;
        i++;
        if(i >= length) {
            i = 0;
        }
    }

    public double get() {
        return sum / length;
    }
}
