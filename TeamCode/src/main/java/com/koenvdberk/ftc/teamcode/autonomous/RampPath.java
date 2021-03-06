package com.koenvdberk.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import com.koenvdberk.ftc.teamcode.RobotHardware;
import com.koenvdberk.ftc.teamcode.autonomous.base.RobotAutonomous;

@Autonomous (name = "Left | Ramp | 0t | 0s", group = "left-competition-ready")
public class RampPath extends RobotAutonomous {

    public long getSTART_DELAY() {
        return 12 * 1000;
    }

    @Override
    public void initOpMode() {
        super.initOpMode();

        if(getTileOffset() == 0) {
            add(actions.move(RobotHardware.cmToPosition(74 - RobotHardware.AXIS_BACK_DISTANCE), RobotHardware.FAST_POWER, RobotHardware.ACCELERATION_TIME));
        } else {
            add(actions.move(RobotHardware.cmToPosition(40 - RobotHardware.AXIS_BACK_DISTANCE), RobotHardware.FAST_POWER, RobotHardware.ACCELERATION_TIME));
            add(actions.turn(getRotationDirection() * RobotHardware.angleToPosition(90), RobotHardware.NORMAL_POWER, 0.5 * RobotHardware.ACCELERATION_TIME));
            add(actions.move(RobotHardware.cmToPosition(getTileOffset() * RobotHardware.TILE_WIDTH_CM), RobotHardware.FAST_POWER, RobotHardware.ACCELERATION_TIME));
            add(actions.turn(-getRotationDirection() * RobotHardware.angleToPosition(90), RobotHardware.NORMAL_POWER, 0.5 * RobotHardware.ACCELERATION_TIME));
            add(actions.move(RobotHardware.cmToPosition(34), RobotHardware.FAST_POWER, RobotHardware.ACCELERATION_TIME));
        }
        add(actions.turn(getRotationDirection() * RobotHardware.angleToPosition(80), RobotHardware.NORMAL_POWER, 0.5 * RobotHardware.ACCELERATION_TIME));

        add(actions.move(RobotHardware.cmToPosition(83), RobotHardware.FAST_POWER, RobotHardware.ACCELERATION_TIME));

        add(actions.turn(getRotationDirection() * RobotHardware.angleToPosition(60), RobotHardware.NORMAL_POWER, 0.5 * RobotHardware.ACCELERATION_TIME));
        add(actions.move(RobotHardware.cmToPosition(90), RobotHardware.FAST_POWER));
    }



    @Autonomous (name = "Right | Ramp | 0t | 0s", group = "right-competition-ready")
    public static class RampPathRight extends RampPath {
        @Override
        public boolean isOnLeft() {
            return false;
        }
    }

    @Autonomous (name = "Left | Ramp | 0t | 12s", group = "left-competition-ready")
    public static class RampPathDelay extends RampPath {
        @Override
        public boolean isStartDelayed() {
            return true;
        }
    }

    @Autonomous (name = "Right | Ramp | 0t | 12s", group = "right-competition-ready")
    public static class RampPathRightDelay extends RampPathRight {
        @Override
        public boolean isStartDelayed() {
            return true;
        }
    }



    @Autonomous (name = "Left | Ramp | 1t | 0s", group = "left-competition-ready")
    public static class RampPathLeft1T extends RampPath {
        @Override
        public double getTileOffset() {
            return 1;
        }
    }

    @Autonomous (name = "Right | Ramp | 1t | 0s", group = "right-competition-ready")
    public static class RampPathRight1T extends RampPathRight {
        @Override
        public double getTileOffset() {
            return 1;
        }
    }

    @Autonomous (name = "Left | Ramp | 1t | 12s", group = "left-competition-ready")
    public static class RampPath1TDelay extends RampPathDelay {
        @Override
        public double getTileOffset() {
            return 1;
        }
    }

    @Autonomous (name = "Right | Ramp | 1t | 12s", group = "right-competition-ready")
    public static class RampPathRight1TDelay extends RampPathRightDelay {
        @Override
        public double getTileOffset() {
            return 1;
        }
    }

    @Autonomous (name = "Left | Ramp | -.5t | 0s", group = "left-competition-ready")
    public static class RampPathLeftMD5T extends RampPath {
        @Override
        public double getTileOffset() {
            return -0.5;
        }
    }

    @Autonomous (name = "Right | Ramp | -.5t | 0s", group = "right-competition-ready")
    public static class RampPathRightMD5T extends RampPathRight {
        @Override
        public double getTileOffset() {
            return -0.5;
        }
    }

    @Autonomous (name = "Left | Ramp | -.5t | 12s", group = "left-competition-ready")
    public static class RampPathMD5TDelay extends RampPathDelay {
        @Override
        public double getTileOffset() {
            return -0.5;
        }
    }

    @Autonomous (name = "Right | Ramp | -.5t | 12s", group = "right-competition-ready")
    public static class RampPathRightMD5TDelay extends RampPathRightDelay {
        @Override
        public double getTileOffset() {
            return -0.5;
        }
    }






}
