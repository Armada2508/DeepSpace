/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * An example command.  You can replace me with your own command.
 */
public class Climb extends Command {
  private double Position;
  private double CurrentPosition = 0;
  public Climb(double position) {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.climbSystem);
    Position = position;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {   
    CurrentPosition = Robot.climbSystem.getPosition() + Position;
    Robot.climbSystem.setPosition(CurrentPosition);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(Math.abs(this.CurrentPosition - Robot.climbSystem.getPosition()) <= 50){
      System.out.println("got there");
      return true;
    }
    if(this.CurrentPosition <= 0 && Robot.climbSystem.isRevLimitSwitch() ){
      System.out.println("hit rev limit");
      return true;
    }
    
    
    
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
