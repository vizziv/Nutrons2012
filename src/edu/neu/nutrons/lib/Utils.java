/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.nutrons.lib;

/**
 * Various useful things.
 *
 * @author Nutrons Pros
 */
public class Utils {
    
    public static double limit(double x, double min, double max) {
        return Math.max(Math.min(x, max), min);
    }
}
