package org.firstinspires.ftc.teamcode.motorbasics;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class DCMotorBasics extends OpMode {
    DcMotorEx motor;

    @Override
    public void init() {
        motor = hardwareMap.get(DcMotorEx.class, "motor");

        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        motor.setDirection(DcMotorSimple.Direction.FORWARD);
        motor.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    @Override
    public void loop() {
        motor.setPower(1.0);
        motor.setPower(-1.0);
        motor.setPower(0);
    }
}
