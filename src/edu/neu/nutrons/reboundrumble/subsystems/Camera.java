package edu.neu.nutrons.reboundrumble.subsystems;

import edu.neu.nutrons.lib.Utils;
import edu.neu.nutrons.reboundrumble.RobotMap;
import edu.neu.nutrons.reboundrumble.commands.camera.CamManualCmd;
import edu.neu.nutrons.reboundrumble.vision.Tracker;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Controls servo that turns camera; contains target tracker.
 *
 * @author Ziv
 */
public class Camera extends Subsystem {

    // Constants.
    private final double MIN_POS = -1.0;
    private final double MAX_POS = 1.0;
    public static final double FENDER_POS = 0.38;
    public static final double KEY_POS = 0.65;

    // Actual robot parts.
    public final Tracker tracker = new Tracker(); // Axis camera is in here.
    private final Servo servo = new Servo(RobotMap.CAM_SERVO);

    public Camera() {
        setPos(.45);
    }

    protected void initDefaultCommand() {
        setDefaultCommand(new CamManualCmd());
    }

    public void setPos(double pos) {
        pos = Utils.limit(pos, MIN_POS, MAX_POS);
        // Change range from [-1,1] to [0,1].
        servo.set((pos + 1) / 2.0);
    }

    public double getPos() {
        // Change range from [0,1] to [-1,1].
        return 2*servo.get() - 1;
    }
}
