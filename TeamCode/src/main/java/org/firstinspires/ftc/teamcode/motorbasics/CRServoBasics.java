package org.firstinspires.ftc.teamcode.motorbasics;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class CRServoBasics extends OpMode {
    CRServo continuousRotationServo;

    @Override
    public void init() {
        continuousRotationServo = hardwareMap.get(CRServo.class, "cr servo");

        continuousRotationServo.setDirection(DcMotorSimple.Direction.FORWARD);
        continuousRotationServo.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    @Override
    public void loop() {
        continuousRotationServo.setPower(-1.0);
        continuousRotationServo.setPower(1.0);
        continuousRotationServo.setPower(0.0);
    }
}

