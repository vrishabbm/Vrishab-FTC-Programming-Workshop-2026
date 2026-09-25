package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Team Frog Force Teleop Key")
public class FullTeleopKey extends OpMode {
    private DcMotorEx arm;
    private Servo rightClaw;
    private Servo leftClaw;

    @Override
    public void init() {
        arm = hardwareMap.get(DcMotorEx.class, "arm");

        rightClaw = hardwareMap.get(Servo.class, "claw right");
        leftClaw = hardwareMap.get(Servo.class, "claw left");

        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        arm.setDirection(DcMotorSimple.Direction.FORWARD);

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

        if (gamepad2.x) {
            leftClaw.setPosition(1.0);
            rightClaw.setPosition(1.0);
        } else if (gamepad2.y) {
            leftClaw.setPosition(0.0);
            rightClaw.setPosition(0.0);
        }
    }

    @Override
    public void stop() {
        arm.setPower(0.0);
    }
}
