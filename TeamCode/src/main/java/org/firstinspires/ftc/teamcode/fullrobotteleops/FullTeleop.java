package org.firstinspires.ftc.teamcode.fullrobotteleops;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Frog Force Tele-Op")
public class FullTeleop extends OpMode {
    DcMotorEx arm;
    Servo leftClaw;
    Servo rightClaw;

    @Override
    public void init() {
        arm = hardwareMap.get(DcMotorEx.class, "arm");

        leftClaw = hardwareMap.get(Servo.class, "leftClaw");
        rightClaw = hardwareMap.get(Servo.class, "rightClaw");

        arm.setDirection(DcMotorSimple.Direction.FORWARD);
        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        leftClaw.setDirection(Servo.Direction.REVERSE);
        rightClaw.setDirection(Servo.Direction.FORWARD);
    }

    @Override
    public void start() {

    }

    @Override
    public void loop() {
        if (gamepad2.dpad_up) {
            arm.setPower(0.5);
        } else if (gamepad2.dpad_down) {
            arm.setPower(-0.3);
        } else {
            arm.setPower(0.0);
        }

        if (gamepad2.a) {
            leftClaw.setPosition(1.0);
            rightClaw.setPosition(1.0);
        } else if (gamepad2.b) {
            leftClaw.setPosition(0.0);
            rightClaw.setPosition(0.0);
        }
    }

    @Override
    public void stop() {
        arm.setPower(0.0);
    }
}
