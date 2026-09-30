package org.firstinspires.ftc.teamcode.autons;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name = "AJW Bang Bang Auto")
public class BangBangControlAuto extends LinearOpMode {
    DcMotorEx leftDrive;
    DcMotorEx rightDrive;

    final double CPR = 288;
    final double WHEEL_RADIUS_INCHES = 1.77;
    final double GEAR_RATIO = 2.0;
    final double TOLERANCE_INCHES = 0.5;

    @Override
    public void runOpMode() {
        leftDrive = hardwareMap.get(DcMotorEx.class, "leftDrive");
        rightDrive = hardwareMap.get(DcMotorEx.class, "rightDrive");

        leftDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        leftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        rightDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        rightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        waitForStart();

        driveDistance(20);
    }

    private void driveDistance(double distInches) {
        double encoderCount = leftDrive.getCurrentPosition();
        double initialDistance = ((encoderCount/CPR)/GEAR_RATIO) * 2 * Math.PI * WHEEL_RADIUS_INCHES;
        double goalDistance = initialDistance + distInches;

        double error = goalDistance - initialDistance;

        while (Math.abs(error) > TOLERANCE_INCHES) {
            // Bang Bang Controller
            double currentDistance = ((encoderCount/CPR)/GEAR_RATIO) * 2 * Math.PI * WHEEL_RADIUS_INCHES;
            error = goalDistance - currentDistance;

            if (error < 0) {
                leftDrive.setPower(0.75);
                rightDrive.setPower(0.75);
            } else if (error > 0) {
                leftDrive.setPower(-0.75);
                rightDrive.setPower(-0.75);
            } else {
                leftDrive.setPower(0);
                rightDrive.setPower(0);
            }
        }

        leftDrive.setPower(0);
        rightDrive.setPower(0);
    }
}
