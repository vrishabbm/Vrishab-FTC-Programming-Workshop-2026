package org.firstinspires.ftc.teamcode.autons;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Disabled
@Autonomous(name = "AJW Proportional Control Auto")
public class ProportionalControlAuto extends LinearOpMode {
    DcMotorEx leftDrive;
    DcMotorEx rightDrive;

    final double CPR = 288;
    final double WHEEL_RADIUS_INCHES = 1.77;
    final double GEAR_RATIO = 2.0;
    final double TOLERANCE_INCHES = 0.5;
    final double kP = 0.08;

    @Override
    public void runOpMode() {
        leftDrive = hardwareMap.get(DcMotorEx.class, "leftDrive");
        rightDrive = hardwareMap.get(DcMotorEx.class, "rightDrive");

        leftDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        leftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        rightDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        rightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

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
            // Proportional Controller
            double currentDistance = ((encoderCount/CPR)/GEAR_RATIO) * 2 * Math.PI * WHEEL_RADIUS_INCHES;
            error = goalDistance - currentDistance;

            double controllerOutput = kP * error;
            leftDrive.setPower(controllerOutput);
            rightDrive.setPower(controllerOutput);
        }

        leftDrive.setPower(0);
        rightDrive.setPower(0);
    }
}
