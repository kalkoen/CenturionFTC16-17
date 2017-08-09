package com.koenvdberk.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import com.koenvdberk.ftc.teamcode.autonomous.base.Action;
import com.koenvdberk.ftc.teamcode.autonomous.base.RobotAutonomous;

@Autonomous (name = "TestPathMOVE", group = "left-competition-ready")
public class TestPath extends RobotAutonomous {

    private final double
            POWER = 0.2;

    @Override
    public void initOpMode() {
        super.initOpMode();

        setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        add(new Action() {
            @Override
            public void run() {
                setPower(POWER);
            }
        });

        telemetry.addData("Status", "Ready for takeoff");
    }

    public boolean isOnLeft() {
        return true;
    }

    public int getRotationDirection() {
        return isOnLeft() ? -1 : 1;
    }

    public boolean isStartDelayed() {
        return false;
    }

}
