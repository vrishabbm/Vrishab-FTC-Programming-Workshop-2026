package org.firstinspires.ftc.teamcode.motorbasics;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

public class ServoBasics extends OpMode {
    Servo servo;

    @Override
    public void init() {
        servo = hardwareMap.get(Servo.class, "servo");

        servo.setDirection(Servo.Direction.FORWARD);
        servo.setDirection(Servo.Direction.REVERSE);
    }

    @Override
    public void loop() {
        servo.setPosition(0);
        servo.setPosition(0.5);
        servo.setPosition(1.0);
    }
}


