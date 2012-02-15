package edu.neu.nutrons.reboundrumble;

import edu.neu.nutrons.reboundrumble.commands.CommandBase;
import edu.wpi.first.wpilibj.Dashboard;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Sends data back to LabVIEW dashboard on computer. For camera targeting data,
 * sensor feedback, etc.
 *
 * @author Ziv
 */
public class Dashboards {

    private DriverStation ds = DriverStation.getInstance();
    private Dashboard lowDash = ds.getDashboardPackerLow();
    private static Dashboards instance = null;

    private Dashboards() {
    }

    public static Dashboards getInstance() {
        if(instance == null) {
            instance = new Dashboards();
        }
        return instance;
    }

    public void sendData() {
        // LabVIEW stuff.
        lowDash.addCluster(); // top
            lowDash.addCluster(); // target1 tracking
                lowDash.addInt((int)CommandBase.cam.tracker.getTarget1().rawBboxCornerX);
                lowDash.addInt((int)CommandBase.cam.tracker.getTarget1().rawBboxCornerY);
                lowDash.addInt((int)CommandBase.cam.tracker.getTarget1().rawBboxWidth);
                lowDash.addInt((int)CommandBase.cam.tracker.getTarget1().rawBboxHeight);
            lowDash.finalizeCluster();
            lowDash.addCluster(); // target2 tracking
                lowDash.addInt((int)CommandBase.cam.tracker.getTarget2().rawBboxCornerX);
                lowDash.addInt((int)CommandBase.cam.tracker.getTarget2().rawBboxCornerY);
                lowDash.addInt((int)CommandBase.cam.tracker.getTarget2().rawBboxWidth);
                lowDash.addInt((int)CommandBase.cam.tracker.getTarget2().rawBboxHeight);
            lowDash.finalizeCluster();
            lowDash.addCluster(); // target3 tracking
                lowDash.addInt((int)CommandBase.cam.tracker.getTarget3().rawBboxCornerX);
                lowDash.addInt((int)CommandBase.cam.tracker.getTarget3().rawBboxCornerY);
                lowDash.addInt((int)CommandBase.cam.tracker.getTarget3().rawBboxWidth);
                lowDash.addInt((int)CommandBase.cam.tracker.getTarget3().rawBboxHeight);
            lowDash.finalizeCluster();
            lowDash.addCluster(); // target4 tracking
                lowDash.addInt((int)CommandBase.cam.tracker.getTarget4().rawBboxCornerX);
                lowDash.addInt((int)CommandBase.cam.tracker.getTarget4().rawBboxCornerY);
                lowDash.addInt((int)CommandBase.cam.tracker.getTarget4().rawBboxWidth);
                lowDash.addInt((int)CommandBase.cam.tracker.getTarget4().rawBboxHeight);
            lowDash.finalizeCluster();
        lowDash.finalizeCluster();
        lowDash.commit();
        // SmartDashboard stuff.
        // TODO: implement getting setpoint of some subsystems.
        SmartDashboard.putDouble("Yaw position", CommandBase.dt.yawGyro.getAngle());
        SmartDashboard.putDouble("Target1 X", CommandBase.cam.tracker.getTarget1().centerX);
        SmartDashboard.putDouble("Cam servo", CommandBase.cam.getPos());
        SmartDashboard.putDouble("Shooter rate", CommandBase.shooter.getPosition());
        SmartDashboard.putDouble("Hood position", CommandBase.hood.getPosition());
    }
}
