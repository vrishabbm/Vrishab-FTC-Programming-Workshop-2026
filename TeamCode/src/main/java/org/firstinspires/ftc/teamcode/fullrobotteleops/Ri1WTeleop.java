package org.firstinspires.ftc.teamcode.fullrobotteleops;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "Robot in One Week 2026: BIOBUZZ")
public class Ri1WTeleop extends OpMode {
    private DcMotorEx leftDrive;
    private DcMotorEx rightDrive;

    private DcMotorEx intake;

    private Servo nectarGate;
    private Servo pollenGate;
    private DcMotorEx shooter;

    private final double JOYSTICK_DEADZONE = 0.1;

    // Ramp Up Logic
    private final double MIN_RAMP_UP_TIME = 2.0;
    private ElapsedTime rampUpTimer = new ElapsedTime();
    private boolean rampUpTimerReset = false;


    @Override
    public void init() {
        leftDrive = hardwareMap.get(DcMotorEx.class, "leftDrive");
        rightDrive = hardwareMap.get(DcMotorEx.class, "rightDrive");
        intake = hardwareMap.get(DcMotorEx.class, "intake");
        nectarGate = hardwareMap.get(Servo.class, "nectarGate");
        pollenGate = hardwareMap.get(Servo.class, "pollenGate");
        shooter = hardwareMap.get(DcMotorEx.class, "shooter");

        leftDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        leftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        rightDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        rightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        intake.setDirection(DcMotorSimple.Direction.FORWARD);
        intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        shooter.setDirection(DcMotorSimple.Direction.FORWARD);
        shooter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        nectarGate.setDirection(Servo.Direction.REVERSE);
        pollenGate.setDirection(Servo.Direction.FORWARD);
    }

    @Override
    public void start() {

    }

    @Override
    public void loop() {
        double forwardPower = 0.0;
        double rightTurnPower = 0.0;

        if (Math.abs(gamepad1.left_stick_y) > JOYSTICK_DEADZONE) {
            forwardPower = -gamepad1.left_stick_y;
        } else {
            forwardPower = 0.0;
        }

        if (Math.abs(gamepad1.right_stick_x) > JOYSTICK_DEADZONE) {
            rightTurnPower = gamepad1.right_stick_x;
        } else {
            rightTurnPower = 0.0;
        }

        leftDrive.setPower(forwardPower + rightTurnPower);
        rightDrive.setPower(forwardPower - rightTurnPower);

        if (gamepad1.right_trigger_pressed) {
            shooter.setPower(0.75);

            if (gamepad1.left_bumper) {
                nectarGate.setPosition(1.0);
            } else {
                nectarGate.setPosition(0.0);
            }

            if (gamepad1.right_bumper) {
                pollenGate.setPosition(1.0);
            } else {
                pollenGate.setPosition(0.3);
            }
        } else {
            shooter.setPower(0.0);
            nectarGate.setPosition(0.0);
            pollenGate.setPosition(0.3);
        }

        if (gamepad1.left_trigger_pressed) {
            intake.setPower(1.0);
            nectarGate.setPosition(0.0);
            pollenGate.setPosition(0.3);
        } else {
            intake.setPower(0.0);
        }


    }

    @Override
    public void stop() {

    }
}
