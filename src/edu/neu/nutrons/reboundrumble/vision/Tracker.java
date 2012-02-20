package edu.neu.nutrons.reboundrumble.vision;

import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.camera.AxisCamera.ExposureT;
import edu.wpi.first.wpilibj.camera.AxisCamera.WhiteBalanceT;
import edu.wpi.first.wpilibj.camera.AxisCameraException;
import edu.wpi.first.wpilibj.image.*;

/**
 * Identifies rectangular targets on backboards using Axis camera. Processing
 * images and getting target information are in separate methods to allow for
 * the processing to occur in its own thread if desired.
 *
 * @author Ziv
 */
public class Tracker {

    private final int redLow = 32;
    private final int redHigh = 82;
    private final int greenLow = 3;
    private final int greenHigh = 33;
    private final int blueLow = 0;
    private final int blueHigh = 26;
    private final int bboxWidthMin = 20;
    private final int bboxHeightMin = 15;
    private final float inertiaXMin = 0.32f;
    private final float inertiaYMin = 0.18f;
    private final double ratioMin = 1.0;
    private final double ratioMax = 3.0;
    private final double rectitudeMin = 0.5;
    private final int camBrightness = 10;
    private final int camColor = 100;
    private final WhiteBalanceT camWhiteBalance = WhiteBalanceT.hold;
    private final ExposureT camExposure = ExposureT.hold;
    public static final int IMAGE_WIDTH = 320;
    public static final int IMAGE_HEIGHT = 240;

    private AxisCamera cam = AxisCamera.getInstance();
    private Target highTarget = Target.NullTarget;
    private Target target1 = Target.NullTarget;
    private Target target2 = Target.NullTarget;
    private Target target3 = Target.NullTarget;
    private Target target4 = Target.NullTarget;
    private CriteriaCollection boxCriteriaX = new CriteriaCollection();
    private CriteriaCollection boxCriteriaY = new CriteriaCollection();
    private CriteriaCollection inertiaCriteriaX = new CriteriaCollection();
    private CriteriaCollection inertiaCriteriaY = new CriteriaCollection();

    public Tracker() {
        cam.writeResolution(AxisCamera.ResolutionT.k320x240);
        cam.writeBrightness(camBrightness);
        cam.writeColorLevel(camColor);
        cam.writeWhiteBalance(camWhiteBalance);
        cam.writeExposureControl(camExposure);
        boxCriteriaX.addCriteria(NIVision.MeasurementType.IMAQ_MT_BOUNDING_RECT_WIDTH,
                             0, bboxWidthMin, true);
        boxCriteriaY.addCriteria(NIVision.MeasurementType.IMAQ_MT_BOUNDING_RECT_HEIGHT,
                             0, bboxHeightMin, true);
        inertiaCriteriaX.addCriteria(NIVision.MeasurementType.IMAQ_MT_NORM_MOMENT_OF_INERTIA_XX,
                             0, inertiaXMin, true);
        inertiaCriteriaY.addCriteria(NIVision.MeasurementType.IMAQ_MT_NORM_MOMENT_OF_INERTIA_YY,
                             0, inertiaYMin, true);
    }

    private void addTarget(Target t) {
        // Fill the first empty target slot.
        if(target1.isNull()) {
            target1 = t;
        }
        else if(target2.isNull()) {
            target2 = t;
        }
        else if(target3.isNull()) {
            target3 = t;
        }
        else if(target4.isNull()) {
            target4 = t;
        }
    }

    public boolean processImage() {
        boolean success = cam.freshImage();
        if(success) {
            try {
                ColorImage im = cam.getImage();
                // Look for target color.
                BinaryImage thresholdIm = im.thresholdRGB(redLow, redHigh,
                                                          greenLow, greenHigh,
                                                          blueLow, blueHigh);
                // We want targets to meet ALL criteria (not ANY), so we use
                // multiple filters.
                // The criteria:
                // -Bounding box at least 24x18.
                // -Moment of inertias are at least .32 (x^2) and .18 (y^2).
                //  (This selects for hollow particles.)
                BinaryImage filteredIm1 = thresholdIm.particleFilter(boxCriteriaX);
                BinaryImage filteredIm2 = filteredIm1.particleFilter(boxCriteriaY);
                BinaryImage filteredIm3 = filteredIm2.particleFilter(inertiaCriteriaX);
                BinaryImage filteredIm4 = filteredIm3.particleFilter(inertiaCriteriaY);
                // Look at convex hull of what's left. As well as convex area
                // being useful (see below), taking the convex hull of every
                // particle eliminates the target-within-a-taget phenomenon that
                // occasionally happens in bad lighting conditions (namely, when
                // only the outer and inner edge of the retroreflective strips
                // are picked up by the color threshold).
                BinaryImage convexHullIm = filteredIm4.convexHull(true);
                ParticleAnalysisReport[] particles = convexHullIm.getOrderedParticleAnalysisReports();
                // Initially assume that no targets are found.
                // TODO: remember previous targets if we briefly lose track.
                highTarget = Target.NullTarget;
                target1 = Target.NullTarget;
                target2 = Target.NullTarget;
                target3 = Target.NullTarget;
                target4 = Target.NullTarget;
                // Loop through targets, keep track of highest one.
                // Dispose of those that have an extreme length/width ratio or
                // aren't very rectangular (don't fill their bounding box).
                double minY = IMAGE_HEIGHT; // Minimum y <-> higher in image.
                for(int i=0; i < particles.length; i++) {
                    Target t = new Target(i, particles[i]);
                    if(t.ratio > ratioMin && t.ratio < ratioMax &&
                           t.rectitude > rectitudeMin) {
                        addTarget(t);
                        if(t.centerY <= minY) {
                            highTarget = t;
                        }
                    }
                }
                // Free memory used by images.
                im.free();
                thresholdIm.free();
                filteredIm1.free();
                filteredIm2.free();
                filteredIm3.free();
                filteredIm4.free();
                convexHullIm.free();
            }
            catch(AxisCameraException ex) {
                ex.printStackTrace();
            }
            catch(NIVisionException ex) {
                ex.printStackTrace();
            }
        }
        return success;
    }

    public Target getHighestTarget() {
        return highTarget;
    }
    public Target getTarget1() {
        return target1;
    }
    public Target getTarget2() {
        return target2;
    }
    public Target getTarget3() {
        return target3;
    }
    public Target getTarget4() {
        return target4;
    }
}
