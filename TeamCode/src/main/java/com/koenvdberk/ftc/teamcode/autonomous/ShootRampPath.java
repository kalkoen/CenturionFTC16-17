package com.koenvdberk.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import com.koenvdberk.ftc.teamcode.RobotHardware;
import com.koenvdberk.ftc.teamcode.autonomous.base.RobotAutonomous;
import com.koenvdberk.ftc.teamcode.autonomous.module.PositionRPath;

@Autonomous (name = "Left | Shoot+Ramp | 0t | 0s", group = "left-competition-ready")
public class ShootRampPath extends RobotAutonomous {

    @Override
    public int getStartDelay() {
        return 8 * 1000;
    }


    @Override
    public void initOpMode() {
        super.initOpMode();

        add(new PositionRPath());

        if(!isOnLeft()) {
            add(actions.turn(getRotationDirection() * RobotHardware.angleToPosition(57.72), RobotHardware.FAST_POWER, 0.5 * RobotHardware.ACCELERATION_TIME));
        } else {
            add(actions.turn(getRotationDirection() * RobotHardware.angleToPosition(100), RobotHardware.FAST_POWER, 0.5 * RobotHardware.ACCELERATION_TIME));
        }
        add(actions.move(RobotHardware.cmToPosition(100), RobotHardware.NORMAL_POWER, RobotHardware.ACCELERATION_TIME));

    }

    public boolean isStartDelayed() {
        return false;
    }

    @Autonomous (name = "Right | Shoot Ramp | 0t | 0s", group = "right-competition-ready")
    public static class ShootRampPathRight extends ShootRampPath {
        @Override
        public boolean isOnLeft() {
            return false;
        }
    }

    @Autonomous (name = "Left | Shoot Ramp | 0t | 8s", group = "left-competition-ready")
    public static class ShootRampPathDelay extends ShootRampPath {
        @Override
        public boolean isStartDelayed() {
            return true;
        }
    }

    @Autonomous (name = "Right | Shoot Ramp | 0t | 8s", group = "right-competition-ready")
    public static class ShootRampPathRightDelay extends ShootRampPathRight {
        @Override
        public boolean isStartDelayed() {
            return true;
        }
    }

    @Autonomous (name = "Left | Shoot Ramp | 1t | 0s", group = "left-competition-ready")
    public static class ShootRampPath1T extends ShootRampPath {
        @Override
        public double getTileOffset() {
            return 1;
        }
    }

    @Autonomous (name = "Right | Shoot Ramp | 1t | 0s", group = "right-competition-ready")
    public static class ShootRampPathRight1T extends ShootRampPathRight {
        @Override
        public double getTileOffset() {
            return 1;
        }
    }

    @Autonomous (name = "Left | Shoot Ramp | 1t | 8s", group = "left-competition-ready")
    public static class ShootRampPath1TDelay extends ShootRampPathDelay {
        @Override
        public double getTileOffset() {
            return 1;
        }
    }

    @Autonomous (name = "Right | Shoot Ramp | 1t | 8s", group = "right-competition-ready")
    public static class ShootRampPathRight1TDelay extends ShootRampPathRightDelay {
        @Override
        public double getTileOffset() {
            return 1;
        }
    }

    @Autonomous (name = "Left | Shoot Ramp | -.5t | 0s", group = "left-competition-ready")
    public static class ShootRampPathMD5T extends ShootRampPath {
        @Override
        public double getTileOffset() {
            return -.5;
        }
    }

    @Autonomous (name = "Right | Shoot Ramp | -.5t | 0s", group = "right-competition-ready")
    public static class ShootRampPathRightMD5T extends ShootRampPathRight {
        @Override
        public double getTileOffset() {
            return -.5;
        }
    }

    @Autonomous (name = "Left | Shoot Ramp | -.5t | 8s", group = "left-competition-ready")
    public static class ShootRampPathMD5TDelay extends ShootRampPathDelay {
        @Override
        public double getTileOffset() {
            return -.5;
        }
    }

    @Autonomous (name = "Right | Shoot Ramp | -.5t | 8s", group = "right-competition-ready")
    public static class ShootRampPathRightMD5TDelay extends ShootRampPathRightDelay {
        @Override
        public double getTileOffset() {
            return -.5;
        }
    }





}
