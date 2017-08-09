package com.koenvdberk.ftc.teamcode.autonomous.base;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import com.koenvdberk.ftc.teamcode.RobotHardware;
import com.koenvdberk.ftc.teamcode.driver.MotionController;
import com.koenvdberk.ftc.teamcode.driver.TriggerDriver;

public abstract class RobotAutonomous extends AutonomousOpMode {

    public RobotAutonomousActions actions;
    public RobotHardware robotHardware;
    public DcMotor leftMotor, rightMotor, launchMotor, pickupMotor;
    public ColorSensor colorSensor;
    public Servo beaconServo;

    public TriggerDriver driver;
    public MotionController motionController;

//    boolean turning = false;
//    boolean modePosition = false;
//
//    @Override
//    public void fix() {
//        if(rightMotor.getMode() == DcMotor.RunMode.RUN_TO_POSITION) {
//            modePosition = true;
//            rightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        } else if(rightMotor.getMode() == DcMotor.RunMode.RUN_USING_ENCODER) {
//            rightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//            modePosition = false;
//        }
//
//        if(modePosition) {
//            rightMotor.setPower(turning ? -leftMotor.getPower() : leftMotor.getPower());
//        }
//    }

    @Override
    public void initOpMode() {
        actions = new RobotAutonomousActions(this);

        robotHardware = new RobotHardware(hardwareMap);
        robotHardware.init();

        leftMotor = robotHardware.getLeftMotor();
        rightMotor = robotHardware.getRightMotor();
        colorSensor = robotHardware.getColorSensor();
        beaconServo = robotHardware.getBeaconServo();
        launchMotor = robotHardware.getLaunchMotor();
        pickupMotor = robotHardware.getPickupMotor();

        robotHardware.setDirection(false);

        leftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        beaconServo = robotHardware.getBeaconServo();

        driver = new TriggerDriver(leftMotor, rightMotor, telemetry);
        motionController = new MotionController(pickupMotor, launchMotor, beaconServo);

        setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        idle();
        setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        if (isStartDelayed()) {
            add(actions.sleep(getStartDelay()));
        }
    }

    public void turn(int factor) {
        // In autonomous, left and right motors are switch
        // negative -> turn left
        // positive -> turn right
        leftMotor.setTargetPosition(Math.round(leftMotor.getCurrentPosition() - factor));
        rightMotor.setTargetPosition(Math.round(rightMotor.getCurrentPosition() + factor));
    }

    public void move(int factor) {
        leftMotor.setTargetPosition(leftMotor.getCurrentPosition() + factor);
        rightMotor.setTargetPosition(rightMotor.getCurrentPosition() + factor);
    }

    public void setPower(double power) {
        leftMotor.setPower(power);
        rightMotor.setPower(power);
    }

    public void setMode(DcMotor.RunMode mode) {
        leftMotor.setMode(mode);
        rightMotor.setMode(mode);
    }

    public boolean driving = false;

    @Override
    protected void runActions() {
        if(gamepad2.y) {
            driving = true;
        }

        if(driving) {
            driver.drive(gamepad1, gamepad2);
            motionController.control(gamepad1, gamepad2);
        } else {
            super.runActions();
        }
    }

    /* Most likely overridden */

    public int getStartDelay() {
        return 0;
    }

    public boolean isStartDelayed() {
        return false;
    }

    public double getTileOffset() {
        return 0;
    }

    public boolean isOnLeft() {
        return true;
    }

    public int getRotationDirection() {
        return isOnLeft() ? -1 : 1;
    }

    /* */

    public boolean areMotorsAtRest() {
        if(leftMotor.getPower() > 0) {
            return leftMotor.getCurrentPosition() >= leftMotor.getTargetPosition();
        } else {
            return leftMotor.getCurrentPosition() <= leftMotor.getTargetPosition();
        }
    }

    public boolean isOnLine() {
        return colorSensor.alpha() < 10;
    }
}
