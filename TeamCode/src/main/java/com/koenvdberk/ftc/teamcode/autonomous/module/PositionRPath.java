package com.koenvdberk.ftc.teamcode.autonomous.module;

import com.koenvdberk.ftc.teamcode.RobotHardware;
import com.koenvdberk.ftc.teamcode.autonomous.base.AutonomousModule;
import com.koenvdberk.ftc.teamcode.autonomous.base.AutonomousOpMode;
import com.koenvdberk.ftc.teamcode.autonomous.base.RobotAutonomous;

/**
 * Created by user on 13/05/17.
 */

public class PositionRPath implements AutonomousModule {

//    private boolean rotateLeft;
//    public PositionRPath(boolean rotateLeft) {
//        this.rotateLeft = rotateLeft;
//    }


    @Override
    public void insert(AutonomousOpMode opMode) {
        RobotAutonomous r = (RobotAutonomous) opMode;

        double angle = 20;
        double startCm = 31.5;

        r.add(r.actions.move(RobotHardware.cmToPosition(startCm - RobotHardware.AXIS_BACK_DISTANCE), RobotHardware.NORMAL_POWER, RobotHardware.ACCELERATION_TIME));

        if(r.isOnLeft()) {
            r.add(r.actions.turn(-RobotHardware.angleToPosition(100), RobotHardware.NORMAL_POWER, 0.5 * RobotHardware.ACCELERATION_TIME));

            r.add(r.actions.move(RobotHardware.cmToPosition(-80 + r.getTileOffset() * RobotHardware.TILE_WIDTH_CM), RobotHardware.NORMAL_POWER, RobotHardware.ACCELERATION_TIME));

            r.add(r.actions.turn(r.getRotationDirection() * RobotHardware.angleToPosition(angle), RobotHardware.SLOW_POWER, 0.5 * RobotHardware.ACCELERATION_TIME));

            r.add(r.actions.shoot());

            r.add(r.actions.turn(-r.getRotationDirection() * RobotHardware.angleToPosition(angle), RobotHardware.FAST_POWER, 0.5 * RobotHardware.ACCELERATION_TIME));
        } else {
            r.add(r.actions.turn(-RobotHardware.angleToPosition(90), RobotHardware.NORMAL_POWER, 0.5 * RobotHardware.ACCELERATION_TIME));

            r.add(r.actions.move(RobotHardware.cmToPosition(80 - r.getTileOffset() * RobotHardware.TILE_WIDTH_CM), RobotHardware.NORMAL_POWER, RobotHardware.ACCELERATION_TIME));

            r.add(r.actions.turn(r.getRotationDirection() * RobotHardware.angleToPosition(90 - angle), RobotHardware.SLOW_POWER, 0.5 * RobotHardware.ACCELERATION_TIME));

            r.add(r.actions.shoot());

            r.add(r.actions.turn(r.getRotationDirection() * RobotHardware.angleToPosition(90 + angle), RobotHardware.FAST_POWER, 0.5 * RobotHardware.ACCELERATION_TIME));
        }

        r.add(r.actions.turn(-r.getRotationDirection() * RobotHardware.angleToPosition(12.72), RobotHardware.SLOW_POWER));
        r.add(r.actions.move(RobotHardware.cmToPosition(179.83), RobotHardware.FAST_POWER, RobotHardware.ACCELERATION_TIME));
    }

}
