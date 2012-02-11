package edu.neu.nutrons.lib;

import edu.wpi.first.wpilibj.Victor;

/**
 * This is a victor that has its output curve corrected to be linear. Victors
 * tend to have a log-like output relative to signal.
 *
 * @author Tom
 */
public class LinearVictor extends Victor {

    public LinearVictor(int port) {
        super(port);
    }

    public LinearVictor(int slot, int port) {
        super(slot, port);
    }

    public void set(double goal_speed) {
        final double deadband_value = 0.082;
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

        // Constants for the 5th order polynomial
        double victor_fit_e1 = 0.437239;
        double victor_fit_c1 = -1.56847;
        double victor_fit_a1 = (-(125.0 * victor_fit_e1 + 125.0 * victor_fit_c1 - 116.0) / 125.0);
        double answer_5th_order = (victor_fit_a1 * goal_speed5
                + victor_fit_c1 * goal_speed3
                + victor_fit_e1 * goal_speed);

        // Constants for the 7th order polynomial
        double victor_fit_c2 = -5.46889;
        double victor_fit_e2 = 2.24214;
        double victor_fit_g2 = -0.042375;
        double victor_fit_a2 = (-(125.0 * (victor_fit_c2 + victor_fit_e2 + victor_fit_g2) - 116.0) / 125.0);
        double answer_7th_order = (victor_fit_a2 * goal_speed7
                + victor_fit_c2 * goal_speed5
                + victor_fit_e2 * goal_speed3
                + victor_fit_g2 * goal_speed);

        // Average the 5th and 7th order polynomials
        double answer = 0.85 * 0.5 * (answer_7th_order + answer_5th_order)
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
