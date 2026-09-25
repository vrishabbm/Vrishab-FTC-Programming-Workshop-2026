package org.firstinspires.ftc.teamcode.drivetrainbasics;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class ArcadeDriveOpMode extends OpMode {
    DcMotorEx leftDrive;
    DcMotorEx rightDrive;
    final double gamepadStickDeadzone = 0.1;

    @Override
    public void init() {
        leftDrive = hardwareMap.get(DcMotorEx.class, "left drive");
        rightDrive = hardwareMap.get(DcMotorEx.class, "right drive");

        leftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        leftDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        rightDrive.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    @Override
    public void loop() {
        double forwardPower = 0.0;
        double rightTurnPower = 0.0;

        if (Math.abs(gamepad1.left_stick_y) > gamepadStickDeadzone) {
            forwardPower = -gamepad1.left_stick_y;
        }  else {
            forwardPower = 0.0;
        }

        if (Math.abs(gamepad1.right_stick_x) > gamepadStickDeadzone) {
            rightTurnPower = gamepad1.right_stick_x;
        }  else {
            rightTurnPower = 0.0;
        }

        leftDrive.setPower(forwardPower + rightTurnPower);
        rightDrive.setPower(forwardPower - rightTurnPower);
    }
}
