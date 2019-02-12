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

/**
 * An example command.  You can replace me with your own command.
 */
public class HomeLinearActuators extends Command {
  
  public HomeLinearActuators() {
    requires(Robot.climbSystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {   
    if(!Robot.climbSystem.isRevLimitSwitch()) {
      Robot.climbSystem.setPower(-RobotMap.homingPower);
    }
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Robot.climbSystem.isRevLimitSwitch();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.climbSystem.setOffset(Robot.climbSystem.getPosition() + RobotMap.liftMargin);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
