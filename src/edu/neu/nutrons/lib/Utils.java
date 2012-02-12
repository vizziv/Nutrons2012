package edu.neu.nutrons.lib;

/**
 * Various useful functions.
 *
 * @author Nutrons Pros
 */
public class Utils {

    public static double limit(double x, double min, double max) {
        return Math.max(Math.min(x, max), min);
    }

    public static double modulo(double x, double base) {
        // Negate if and only if base is negative.
        // (Java's modulo isn't mathematically pretty in this way.)
        double sign = (base < 0) ? -1 : 1;
        return sign * (Math.abs(x) % Math.abs(base));
    }
}
