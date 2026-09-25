package org.firstinspires.ftc.teamcode.drivetrainbasics;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class CorrectedStickValues extends OpMode {
    // typical value for a deadzone
    final double gamepadStickDeadzone = 0.1;

    @Override
    public void init() {}

    @Override
    public void loop() {
        // Corrected values of gamepad1 left stick
        double correctedXValue = 0.0;
        double correctedYValue = 0.0;

        // Check deadzone
        if (Math.abs(gamepad1.left_stick_x) > gamepadStickDeadzone) {
            correctedXValue = gamepad1.left_stick_x;
        }  else {
            correctedXValue = 0.0;
        }

        // Check deadzone
        if (Math.abs(gamepad1.left_stick_y) > gamepadStickDeadzone) {
            // Flip sign of y-value
            correctedYValue = -gamepad1.left_stick_y;
        }  else {
            correctedYValue = 0.0;
        }
    }
}



