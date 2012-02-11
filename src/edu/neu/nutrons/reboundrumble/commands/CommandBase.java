package edu.neu.nutrons.reboundrumble.commands;

import edu.neu.nutrons.reboundrumble.OI;
import edu.neu.nutrons.reboundrumble.subsystems.*;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The base for all commands. Has a single static instance of every singleton:
 * subsystems, the OI and so on. Extends PIDCommand, but if this is ignored in
 * child classes then they act like normal commands.
 *
 * @author Ziv
 */
public abstract class CommandBase extends PIDCommand {

    // Single static instance of OI, dashboard and all subsystems.
    public static OI oi;
    public static DriveTrain dt = new DriveTrain();
    public static Camera cam = new Camera();
    public static Shooter shooter = new Shooter();
    public static Hood hood = new Hood();
    public static Elevator elev = new Elevator();

    public static void init() {
        // This MUST be here. If the OI creates Commands (which it very likely
        // will), constructing it during the construction of CommandBase (from
        // which commands extend), subsystems are not guaranteed to be
        // yet. Thus, their requires() statements may grab null pointers. Bad
        // news. Don't move it.
        oi = new OI();

        // Display subsystem data on SmartDashboard.
        SmartDashboard.putData(dt);
        SmartDashboard.putData(cam);
        SmartDashboard.putData(shooter);
        SmartDashboard.putData(hood);
    }

    public CommandBase(String name) {
        super(name, 0, 0, 0);
    }

    public CommandBase() {
        super(0, 0, 0);
    }

    public CommandBase(String name, double kp, double ki, double kd) {
        super(name, kp, ki, kd);
    }

    public CommandBase(double kp, double ki, double kd) {
        super(kp, ki, kd);
    }

    protected double returnPIDInput() {
        // By default, PID doesn't happen.
        return 0;
    }

    protected void usePIDOutput(double output) {
        // By default, PID doesn't happen.
    }
}
