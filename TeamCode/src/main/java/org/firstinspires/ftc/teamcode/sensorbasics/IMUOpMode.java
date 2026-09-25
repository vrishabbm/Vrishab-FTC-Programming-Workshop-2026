package org.firstinspires.ftc.teamcode.sensorbasics;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class IMUOpMode extends OpMode {
    IMU imu;

    @Override
    public void init() {
        imu = hardwareMap.get(IMU.class, "imu");

        RevHubOrientationOnRobot.LogoFacingDirection logoDirection = RevHubOrientationOnRobot.LogoFacingDirection.UP;
        RevHubOrientationOnRobot.UsbFacingDirection usbDirection = RevHubOrientationOnRobot.UsbFacingDirection.FORWARD;

        RevHubOrientationOnRobot orientationOnRobot = new RevHubOrientationOnRobot(logoDirection, usbDirection);
        IMU.Parameters imuParameters = new IMU.Parameters(orientationOnRobot);

        imu.initialize(imuParameters);
        imu.resetYaw();
    }

    @Override
    public void loop() {
        double robotHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);

        telemetry.addData("Robot Heading (Deg)", robotHeading);
        telemetry.update();
    }
}

