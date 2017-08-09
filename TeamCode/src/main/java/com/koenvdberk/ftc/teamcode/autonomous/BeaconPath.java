package com.koenvdberk.ftc.teamcode.autonomous;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import com.koenvdberk.ftc.teamcode.RobotHardware;
import com.koenvdberk.ftc.teamcode.autonomous.base.Action;
import com.koenvdberk.ftc.teamcode.autonomous.base.RobotAutonomous;
import com.koenvdberk.ftc.teamcode.autonomous.module.BeaconColorDetector;
import com.koenvdberk.ftc.teamcode.autonomous.module.LineCatcher;
import com.koenvdberk.ftc.teamcode.autonomous.module.LineFollower;
import com.koenvdberk.ftc.teamcode.autonomous.module.PositionRPath;

@Autonomous (name = "Left | Beacon | 0t | 0s", group = "left-competition-ready")
public class BeaconPath extends RobotAutonomous {

    private final int
            COLOR_DETECTION_ATTEMPTS = 10;
    private final long
            START_DELAY = 10 * 1000;

    BeaconColorDetector bcd;

    @Override
    public void initOpMode() {
        super.initOpMode();

        bcd = new BeaconColorDetector(this);
        bcd.startCamera();

        add(new PositionRPath());


        add(actions.move(RobotHardware.cmToPosition(30), RobotHardware.NORMAL_POWER));
        add(actions.turn(-getRotationDirection() * RobotHardware.angleToPosition(48.13), RobotHardware.NORMAL_POWER, 0.5 * RobotHardware.ACCELERATION_TIME));

        add(new Action() {
            @Override
            public void run() {
                setPower(RobotHardware.NORMAL_POWER);
            }
        });
        add(new LineCatcher() {
            @Override
            public boolean rotateLeft() {
                return isOnLeft();
            }
        });
        add(new LineFollower() {
            @Override
            public boolean hasModuleEnded() {
                return false;
            }

            @Override
            public boolean isLineLeft() {
                return isOnLeft();
            }
        });
        add(new Action() {
            @Override
            public void start() {
                setPower(RobotHardware.VERY_SLOW_POWER);
            }

            int red = 0, blue = 0;
            @Override
            public void run() {
                bcd.analyzeImage();

                if(bcd.leftColor == Color.RED) {
                    red++;
                } else if(bcd.leftColor == Color.BLUE) {
                    blue++;
                }
            }

            @Override
            public boolean hasEnded() {
                return red + blue >= COLOR_DETECTION_ATTEMPTS;
            }

            @Override
            public void stop() {
                if(blue > red) {
                    beaconServo.setPosition(0);
                } else {
                    beaconServo.setPosition(1);
                }
            }
        });
        add(actions.sleep(5000));
        add(actions.move(RobotHardware.cmToPosition(-20), RobotHardware.NORMAL_POWER));
        add(actions.turn(-getRotationDirection() * RobotHardware.angleToPosition(60.85), RobotHardware.NORMAL_POWER));
        add(actions.move(-RobotHardware.cmToPosition(83.65), RobotHardware.FAST_POWER));
        add(actions.turn(getRotationDirection() * RobotHardware.angleToPosition(105.85), RobotHardware.NORMAL_POWER));
        add(actions.move(RobotHardware.cmToPosition(100), RobotHardware.FAST_POWER));


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

    @Autonomous (name = "Right | Beacon | 0t 0s", group = "right-competition-ready")
    public static class BeaconPathRight extends BeaconPath {
        @Override
        public boolean isOnLeft() {
            return false;
        }
    }

    @Autonomous (name = "Left | Beacon | 0t | 10s", group = "left-competition-ready")
    public static class BeaconPathDelay extends BeaconPath {
        @Override
        public boolean isStartDelayed() {
            return true;
        }
    }

    @Autonomous (name = "Right | Beacon | 0t | 10s", group = "right-competition-ready")
    public static class BeaconPathRightDelay extends BeaconPathRight {
        @Override
        public boolean isStartDelayed() {
            return true;
        }
    }

    @Autonomous (name = "Left | Beacon | 1t | 0s", group = "left-competition-ready")
    public static class BeaconPath1T extends BeaconPath {
        @Override
        public double getTileOffset() {
            return 1;
        }
    }

    @Autonomous (name = "Right | Beacon | 1t | 0s", group = "right-competition-ready")
    public static class BeaconPathRight1T extends BeaconPathRight {
        @Override
        public double getTileOffset() {
            return 1;
        }
    }

    @Autonomous (name = "Left | Beacon | 1t | 10s", group = "left-competition-ready")
    public static class BeaconPath1TDelay extends BeaconPathDelay {
        @Override
        public double getTileOffset() {
            return 1;
        }
    }

    @Autonomous (name = "Right | Beacon | 1t | 10s", group = "right-competition-ready")
    public static class BeaconPathRight1TDelay extends BeaconPathRightDelay {
        @Override
        public double getTileOffset() {
            return 1;
        }
    }

    @Autonomous (name = "Left | Beacon | -.5t | 0s", group = "left-competition-ready")
    public static class BeaconPathMD5T extends BeaconPath {
        @Override
        public double getTileOffset() {
            return -.5;
        }
    }

    @Autonomous (name = "Right | Beacon | -.5t | 0s", group = "right-competition-ready")
    public static class BeaconPathRightMD5T extends BeaconPathRight {
        @Override
        public double getTileOffset() {
            return -.5;
        }
    }

    @Autonomous (name = "Left | Beacon | -.5t | 10s", group = "left-competition-ready")
    public static class BeaconPathMD5TDelay extends BeaconPathDelay {
        @Override
        public double getTileOffset() {
            return -.5;
        }
    }

    @Autonomous (name = "Right | Beacon | -.5t | 10s", group = "right-competition-ready")
    public static class BeaconPathRightMD5TDelay extends BeaconPathRightDelay {
        @Override
        public double getTileOffset() {
            return -.5;
        }
    }

}
