package edu.neu.nutrons.reboundrumble.subsystems;

import com.sun.squawk.util.MathUtils;
import edu.neu.nutrons.reboundrumble.RobotMap;
import edu.neu.nutrons.reboundrumble.commands.CommandBase;
import edu.neu.nutrons.reboundrumble.commands.camera.CamManualCmd;
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
    private static final double kp = 0.3;
    private static final double ki = 0;
    private static final double kd = 0;

    // Actual robot parts.
    public final Tracker tracker = new Tracker(); // Axis camera is in here.
    private final Servo servo = new Servo(RobotMap.CAM_SERVO);

    // Other variables.
    private double lastAngle = 0;

    public Camera() {
        super(kp, ki, kd);
    }

    protected void initDefaultCommand() {
        disable();
        setDefaultCommand(new CamManualCmd());
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
        // Change position by PID output if we see the target.
        double curAngle = CommandBase.dt.yawGyro.getAbsoluteAngle();
        double delta = curAngle - lastAngle;
        lastAngle = curAngle;
        if(tracker.getTarget1().isNotNull()) {
            setPos(getPos() + MathUtils.asin(output) + delta / 70.0);
        }
    }

    public double getAngle() {
        // This assumes that signal is linear relative to angle.
        return 90*getPos();
    }
}
