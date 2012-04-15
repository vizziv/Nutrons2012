package edu.neu.nutrons.lib.fpop;

/**
 * A number that needs real time management. Each block is automatically handled
 * as long as BlockHandler.getInstance().run() is called in the main loop.
 *
 * @author Ziv
 */
public abstract class Block implements Number {

    public Block() {
        // There's a scary sounding warning here, but tested in a normal java
        // application there appear to be no problems.
        BlockHandler.getInstance().addBlock(this);
    }

    public abstract void handle();
}
