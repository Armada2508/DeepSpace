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
 * An example command.  You can replace me with your own command.
 */
public class HomeLinearActuators extends Command {

  //private boolean isDone = false;

  public HomeLinearActuators() {
    requires(Robot.climbSystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    /*boolean linearActuatorsOut = false;
    while(!linearActuatorsOut) {
      linearActuatorsOut = true;
      for (int i = 0; i < Robot.climbSystem.returnTalons().size(); i++) { 
        if(Robot.climbSystem.isRevLimitSwitch(i)) {
          linearActuatorsOut = false;
          Robot.climbSystem.setPower(-RobotMap.homingPower, i);
        } else {
          Robot.climbSystem.setPower(0.0, i);
        }
      }
    }*/
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    /*boolean linearActuatorsIn = true;
    for (int i = 0; i < Robot.climbSystem.returnTalons().size(); i++) { 
      if(!Robot.climbSystem.isRevLimitSwitch(i)) {
        linearActuatorsIn = false;
        Robot.climbSystem.setPower(RobotMap.homingPower, i);
      } else {
        Robot.climbSystem.setPower(0.0, i);
      }
    }
    if(linearActuatorsIn) {
      isDone = true;
    }*/
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return /*isDone*/true;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    /*ArrayList<Double> offsets = new ArrayList<Double>();
    for (int i = 0; i < Robot.climbSystem.returnTalons().size(); i++) {
      offsets.add(offsets.size(), Robot.climbSystem.getRawInchPosition(i) + RobotMap.liftMargin);
    }
   // Robot.climbSystem.setOffset(offsets);
    Robot.climbSystem.setPosition(0);
  */}

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
