package edu.neu.nutrons.lib;

/**
 * Composes two filters f1 and f2. (Following normal mathematical notation, this
 * means that f2 is applied first, then the output of f2 is applied to f1.)
 *
 * @author Ziv
 */
public class ComposedFilter extends Filter {

    private Filter f1;
    private Filter f2;

    public ComposedFilter(Filter f1, Filter f2) {
        this.f1 = f1;
        this.f2 = f2;
    }

    public void feed(double in) {
        f2.feed(in);
        f1.feed(f2.get());
    }

    public double get() {
        return f1.get();
    }

    public void reset() {
        f1.reset();
        f2.reset();
    }
}
