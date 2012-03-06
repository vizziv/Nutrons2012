/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.neu.nutrons.reboundrumble;

import edu.neu.nutrons.reboundrumble.commands.CommandBase;
import edu.neu.nutrons.reboundrumble.commands.drivetrain.DTManualCheesyCmd;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Nutrons2012 extends IterativeRobot {

    private Command autonomousCommand;
    private Compressor comp = new Compressor(RobotMap.AIR_PRESSURE, RobotMap.COMPRESSOR);

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        // Assign autonomous command.
        autonomousCommand = new DTManualCheesyCmd();

        // Initialize all subsystems.
        CommandBase.init();
        SmartDashboard.putDouble("ShooterPIDExp", 1);
    }

    public void disabledInit() {
        comp.stop();
    }

    public void autonomousInit() {
        // Start autonomous routine.
        autonomousCommand.start();
    }

    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        autonomousCommand.cancel();
        comp.start();
    }

    public void teleopPeriodic() {
        CommandBase.shooter.processSensors();
        CommandBase.hood.processSensors();
        Scheduler.getInstance().run();
        Dashboards.getInstance().sendPeriodicData();
    }

    public void teleopContinuous() {
        // Do camera processing and dashboard communication separately.
        CommandBase.cam.tracker.processImage();
        Dashboards.getInstance().sendContinuousData();
    }
}
