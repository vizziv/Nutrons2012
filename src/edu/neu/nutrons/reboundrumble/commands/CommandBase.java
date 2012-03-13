package edu.neu.nutrons.reboundrumble.commands;

import edu.neu.nutrons.reboundrumble.DTPIDController;
import edu.neu.nutrons.reboundrumble.OI;
import edu.neu.nutrons.reboundrumble.subsystems.*;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The base for all commands.
 *
 * @author Ziv
 */
public abstract class CommandBase extends Command {

    // Single static instance of OI and all subsystems.
    public static OI oi;
    public static DTPIDController dtPID;
    public static DriveTrain dt = new DriveTrain();
    public static Shifter shifter = new Shifter();
    public static Camera cam = new Camera();
    public static Shooter shooter = new Shooter();
    public static Hood hood = new Hood();
    public static Elevator elev = new Elevator();
    public static Intake intake = new Intake();

    public static void init() {
        // This MUST be here. If the OI creates Commands (which it very likely
        // will), constructing it during the construction of CommandBase (from
        // which commands extend), subsystems are not guaranteed to be
        // yet. Thus, their requires() statements may grab null pointers. Bad
        // news. Don't move it.
        oi = new OI();
        dtPID = new DTPIDController();

        // Display subsystem data on SmartDashboard.
        SmartDashboard.putData(dt);
        SmartDashboard.putData(cam);
        SmartDashboard.putData(shooter);
        // Drive train PID put on SmartDashboard in dtPID constructor.
    }

    public CommandBase(String name) {
        super(name);
    }

    public CommandBase() {
        super();
    }
}
