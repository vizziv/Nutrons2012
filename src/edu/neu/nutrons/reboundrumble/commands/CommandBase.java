package edu.neu.nutrons.reboundrumble.commands;

import edu.neu.nutrons.reboundrumble.DTPIDController;
import edu.neu.nutrons.reboundrumble.OI;
import edu.neu.nutrons.reboundrumble.commands.group.PrepareManualShootingCmd;
import edu.neu.nutrons.reboundrumble.commands.group.PrepareShootingCmd;
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

    // Some commands to be used in multiple places.
    public static Command prepareFenderCmd(boolean manual) {
        if(manual) {
            return new PrepareManualShootingCmd(Shooter.FENDER_POWER, false, Camera.FENDER_POS);
        }
        else {
            return new PrepareShootingCmd(Shooter.FENDER_RATE, false, Camera.FENDER_POS);
        }
    }

    public static Command prepareFenderCmd() {
        return prepareFenderCmd(false);
    }

    public static Command prepareKeyCmd(boolean manual) {
        if(manual) {
            return new PrepareManualShootingCmd(Shooter.KEY_POWER, false, Camera.KEY_POS);
        }
        else {
            return new PrepareShootingCmd(Shooter.KEY_RATE, false, Camera.KEY_POS);
        }
    }

    public static Command prepareKeyCmd() {
        return prepareFenderCmd(false);
    }

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
        SmartDashboard.putData(hood);
        // Drive train PID put on SmartDashboard in dtPID constructor.
    }

    public CommandBase(String name) {
        super(name);
    }

    public CommandBase() {
        super();
    }
}
