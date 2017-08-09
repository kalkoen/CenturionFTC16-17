package com.koenvdberk.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import com.koenvdberk.ftc.teamcode.RobotHardware;
import com.koenvdberk.ftc.teamcode.autonomous.base.Action;
import com.koenvdberk.ftc.teamcode.autonomous.base.RobotAutonomous;
import com.koenvdberk.ftc.teamcode.autonomous.module.LineCatcher;

/**
 * Created by user on 13/05/17.
 */

@Autonomous (name = "LineCatcherTest")
public class LineCatcherTest extends RobotAutonomous {

    @Override
    public void initOpMode() {
        super.initOpMode();

        add(new Action() {
            @Override
            public void run() {
                leftMotor.setPower(RobotHardware.NORMAL_POWER);
                rightMotor.setPower(RobotHardware.NORMAL_POWER);
            }
        });
        add(new LineCatcher() {
            @Override
            public boolean rotateLeft() {
                return false;
            }
        });
    }

}
