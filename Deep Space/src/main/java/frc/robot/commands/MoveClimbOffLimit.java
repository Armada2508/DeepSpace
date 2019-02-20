/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;
import java.util.ArrayList;

/**
 * An example command. You can replace me with your own command.
 */
public class MoveClimbOffLimit extends Command {

    private boolean isDone = false;

    public MoveClimbOffLimit() {
        requires(Robot.climbSystem);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        System.out.println("Starting MoveClimbOffLimit");
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        boolean linearActuatorsOut = false;
        // extends all actuators until they are off the switches
        linearActuatorsOut = true;
        for (int i = 0; i < Robot.climbSystem.getTalons().size(); i++) {
            if (Robot.climbSystem.isRevLimitSwitch(i)) {
                linearActuatorsOut = false;
                Robot.climbSystem.setPower(RobotMap.homingPower, i);
            } else {
                Robot.climbSystem.setPower(0.0, i);
            }
        }

        isDone = linearActuatorsOut;
}

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return isDone;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {        
        System.out.println("Finished MoveClimbOffLimit");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }
}
