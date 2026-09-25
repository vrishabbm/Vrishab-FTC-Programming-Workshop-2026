package org.firstinspires.ftc.teamcode.sensorbasics;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class EncoderOpMode extends OpMode {
    DcMotorEx motor;
    final double motorCPR = 288;
    final double gamepadStickDeadzone = 0.1;

    @Override
    public void init() {
        motor = hardwareMap.get(DcMotorEx.class, "motor");

        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        motor.setDirection(DcMotorSimple.Direction.FORWARD);

        // Disable Motor and Reset encoder position to 0
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        // Re-enable Motor
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    @Override
    public void loop() {
        double motorEncoderCount = motor.getCurrentPosition();
        double motorRotationDegrees = (motorEncoderCount/motorCPR) * 360.0;

        telemetry.addData("Raw Encoder Count", motorEncoderCount);
        telemetry.addData("Motor Rotation (Degrees)", motorRotationDegrees);
        telemetry.update();
    }
}

