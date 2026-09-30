package org.firstinspires.ftc.teamcode.autons;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@Autonomous(name = "AJW Triangle Path Auto")
public class TrianglePathAuto extends LinearOpMode {
    DcMotorEx leftDrive;
    DcMotorEx rightDrive;

    IMU imu;

    final double CPR = 288;
    final double WHEEL_RADIUS_INCHES = 1.77;
    final double GEAR_RATIO = 2.0;
    final double DIST_TOLERANCE_INCHES = 0.5;
    final double DIST_KP = 0.08;
    final double TURN_TOLERANCE_DEGREES = 10;
    final double TURN_KP = 0.01;

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

        imu = hardwareMap.get(IMU.class, "imu");

        RevHubOrientationOnRobot.LogoFacingDirection logoDirection = RevHubOrientationOnRobot.LogoFacingDirection.UP;
        RevHubOrientationOnRobot.UsbFacingDirection usbDirection = RevHubOrientationOnRobot.UsbFacingDirection.FORWARD;

        RevHubOrientationOnRobot orientationOnRobot = new RevHubOrientationOnRobot(logoDirection, usbDirection);
        IMU.Parameters imuParameters = new IMU.Parameters(orientationOnRobot);

        imu.initialize(imuParameters);
        imu.resetYaw();

        waitForStart();

        for (int i = 0; i < 3; i++) {
            driveDistance(8);
            turnLeft(120);
        }
    }

    private void driveDistance(double distInches) {
        double encoderCount = leftDrive.getCurrentPosition();
        double initialDistance = ((encoderCount/CPR)/GEAR_RATIO) * 2 * Math.PI * WHEEL_RADIUS_INCHES;
        double goalDistance = initialDistance + distInches;

        double error = goalDistance - initialDistance;

        while (Math.abs(error) > DIST_TOLERANCE_INCHES) {
            // Proportional Controller
            double currentDistance = ((encoderCount/CPR)/GEAR_RATIO) * 2 * Math.PI * WHEEL_RADIUS_INCHES;
            error = goalDistance - currentDistance;

            double controllerOutput = DIST_KP * error;
            leftDrive.setPower(controllerOutput);
            rightDrive.setPower(controllerOutput);
        }

        leftDrive.setPower(0);
        rightDrive.setPower(0);
    }

    private void turnLeft(double angleDegrees) {
        double initialHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);
        double goalHeading = (initialHeading + angleDegrees);

        // Keep angle from -180 to 180 degrees
        goalHeading %= 360;

        if (goalHeading > 180) {
            goalHeading -= 360;
        }

        double error = goalHeading - initialHeading;

        while (Math.abs(error) > TURN_TOLERANCE_DEGREES) {
            double currentHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);
            error = goalHeading - currentHeading;

            double controllerOutput = TURN_KP * error;
            leftDrive.setPower(-controllerOutput);
            rightDrive.setPower(controllerOutput);
        }

        leftDrive.setPower(0);
        rightDrive.setPower(0);
    }
}
