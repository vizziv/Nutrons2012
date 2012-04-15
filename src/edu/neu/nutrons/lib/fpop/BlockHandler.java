package edu.neu.nutrons.lib.fpop;

import edu.neu.nutrons.lib.fpop.sources.Counter;
import java.util.Enumeration;
import java.util.Vector;

/**
 * List of every block in the program that processes each exactly once each
 * cycle of the main loop.
 *
 * @author Ziv
 */
public class BlockHandler extends Vector {

    private static BlockHandler instance = null;

    private BlockHandler() {};

    public static BlockHandler getInstance() {
        if(instance == null) {
            instance = new BlockHandler();
        }
        return instance;
    }

    public void addBlock(Block b) {
        this.addElement(b);
    }

    public void run() {
        for(Enumeration e = this.elements(); e.hasMoreElements();) {
            Block b = (Block)e.nextElement();
            b.handle();
        }
    }
}
