package edu.neu.nutrons.lib;

import edu.wpi.first.wpilibj.Victor;

/**
 * This is a victor that has its output curve corrected to be linear. Victors
 * tend to have a log-like output relative to signal.
 *
 * @author Tom
 */
public class LinearVictor extends Victor {

    // Constants
    private final double a_1 = 0.394864;
    private final double a_3 = 0.67367;
    private final double a_5 = -3.409659;
    private final double a_7 = 4.197125;
    private final double deadband_value = 0.082;

    public LinearVictor(int port) {
        super(port);
    }

    public LinearVictor(int slot, int port) {
        super(slot, port);
    }

    public void set(double goal_speed) {
        if(goal_speed > deadband_value) {
            goal_speed -= deadband_value;
        }
        else if(goal_speed < -deadband_value) {
            goal_speed += deadband_value;
        }
        else {
            goal_speed = 0.0;
        }
        goal_speed = goal_speed / (1.0 - deadband_value);

        double goal_speed2 = goal_speed * goal_speed;
        double goal_speed3 = goal_speed2 * goal_speed;
        double goal_speed4 = goal_speed3 * goal_speed;
        double goal_speed5 = goal_speed4 * goal_speed;
        double goal_speed6 = goal_speed5 * goal_speed;
        double goal_speed7 = goal_speed6 * goal_speed;

        double answer_7th_order = (a_7 * goal_speed7 + a_5 * goal_speed5 +
                                   a_3 * goal_speed3 + a_1 * goal_speed);

        // Average polynomial with line
        double answer = 0.85 * 0.5 * (answer_7th_order)
                + .15 * goal_speed * (1.0 - deadband_value);

        if(answer > 0.001) {
            answer += deadband_value;
        }
        else if(answer < -0.001) {
            answer -= deadband_value;
        }

        super.set(answer);

    }
}
