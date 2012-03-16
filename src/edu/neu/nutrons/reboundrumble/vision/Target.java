package edu.neu.nutrons.reboundrumble.vision;

import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;

/**
 * Container for rectangular target information.
 *
 * @author Ziv
 */
public class Target {

    // Constants.
    public static final double FENDER_TARGET_WIDTH = 125;

    public final int index;
    // Measured values.
    public final double rawBboxCornerX;
    public final double rawBboxCornerY;
    public final double rawBboxWidth;
    public final double rawBboxHeight;
    public final double rawArea;
    // Calculated values.
    public final double centerX;
    public final double centerY;
    public final double ratio;
    public final double rectitude; // It's a pun!
    // To check whether a target is null, we check whether index >= 0. (However,
    // on the LabVIEW dashboard we check whether rawBboxCornerX >= 0, so we
    // cover our bases and make everything -1.)
    public static final Target NullTarget = new Target(-1,-1,-1,-1,-1,-1);

    public Target(int index, double bboxCornerX, double bboxCornerY,
                  double bboxWidth, double bboxHeight, double area) {
        this.index = index;
        rawBboxCornerX = bboxCornerX;
        rawBboxCornerY = bboxCornerY;
        rawBboxWidth = bboxWidth;
        rawBboxHeight = bboxHeight;
        rawArea = area;
        // If we have a valid target, calculate various useful values.
        // Otherwise, don't bother, avoiding possible division by 0 shenanigans.
        if(index >= 0) {
            centerX = (-Tracker.IMAGE_WIDTH/2.0 + bboxCornerX + bboxWidth/2.0) /
                            Tracker.IMAGE_WIDTH;
            centerY = (-Tracker.IMAGE_HEIGHT/2.0 + bboxCornerY + bboxHeight/2.0) /
                            Tracker.IMAGE_HEIGHT;
            ratio = (double)bboxWidth / (double)bboxHeight;
            rectitude = (double)area / (double)(bboxWidth * bboxHeight);
        }
        else {
            centerX = 0;
            centerY = 0;
            ratio = 0;
            rectitude = 0;
        }
    }

    public Target(int index, ParticleAnalysisReport p) {
        this(index, p.boundingRectLeft, p.boundingRectTop, p.boundingRectWidth,
             p.boundingRectHeight, p.particleArea);
    }

    public static Target average(Target t1, Target t2) {
        double bboxCornerX = (t1.rawBboxCornerX + t2.rawBboxCornerX) / 2.0;
        double bboxCornerY = (t1.rawBboxCornerY + t2.rawBboxCornerY) / 2.0;
        double bboxWidth = (t1.rawBboxWidth + t2.rawBboxWidth) / 2.0;
        double bboxHeight = (t1.rawBboxHeight + t2.rawBboxHeight) / 2.0;
        double area = (t1.rawArea + t2.rawArea) / 2.0;
        // TODO: make index less hacky.
        return new Target(9001, bboxCornerX, bboxCornerY, bboxWidth, bboxHeight, area);
    }

    public boolean isNotNull() {
        return index >= 0;
    }
    public boolean isNull() {
        return index < 0;
    }
}
