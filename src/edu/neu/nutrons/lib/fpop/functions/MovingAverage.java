package edu.neu.nutrons.lib.fpop.functions;

import edu.neu.nutrons.lib.fpop.Block;
import edu.neu.nutrons.lib.fpop.Number;

/**
 * Smooths out an input stream of integers by averaging the latest values.
 *
 * @author Ziv
 */
public class MovingAverage extends Block {

    private Number x;
    private double[] data;
    private double sum = 0;
    private int length;
    private int i = 0;

    public MovingAverage(int length, Number x) {
        this.x = x;
        data = new double[length];
        this.length = length;
    }

    public double get() {
        return sum / length;
    }

    public void handle() {
        double newX = x.get();
        sum += newX - data[i];
        data[i] = newX;
        i++;
        if(i >= length) {
            i = 0;
        }
    }
}
