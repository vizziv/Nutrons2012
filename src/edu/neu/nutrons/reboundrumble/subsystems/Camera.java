package edu.neu.nutrons.reboundrumble.subsystems;

import edu.neu.nutrons.reboundrumble.RobotMap;
import edu.neu.nutrons.reboundrumble.commands.CommandBase;
import edu.neu.nutrons.reboundrumble.commands.camera.CamSetPosCmd;
import edu.neu.nutrons.reboundrumble.vision.Tracker;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 * Controls servo that turns camera; contains target tracker.
 *
 * @author Ziv
 */
public class Camera extends PIDSubsystem {

    // Constants.
    // TODO: tune PID more.
    private static final double kp = 0.125;
    private static final double ki = 0;
    private static final double kd = 0;

    // Actual robot parts.
    public final Tracker tracker = new Tracker(); // Axis camera is in here.
    private final Servo servo = new Servo(RobotMap.CAM_SERVO);

    public Camera() {
        super(kp, ki, kd);
    }

    protected void initDefaultCommand() {
        disable();
        setDefaultCommand(new CamSetPosCmd(0));
    }

    public void setPos(double pos) {
        // Change range from [-1,1] to [0,1].
        servo.set((pos + 1) / 2.0);
    }

    public double getPos() {
        // Change range from [0,1] to [-1,1].
        return 2*servo.get() - 1;
    }

    protected double returnPIDInput() {
        return -tracker.getTarget1().centerX;
    }

    protected void usePIDOutput(double output) {
        // Change position by PID output.
        setPos(getPos() + output - (CommandBase.dt.yawGyro.getAbsoluteAngle() / 90.0));
    }

    public double getAngle() {
        // This assumes that signal is linear relative to angle.
        return 90*getPos();
    }
}
