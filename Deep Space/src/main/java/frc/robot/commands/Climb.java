/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

/**
 * An example command.  You can replace me with your own command.
 */
public class Climb extends Command {
  private boolean isDone;
  private double position;
  public Climb(double pos) {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.climbSystem);
    position = pos;
  }

  public Climb(double pos, int... talons) {
    for (int i = 0; i < talons.length; i++) {
      Robot.climbSystem.setPosition(pos, talons[i]);
    }
  }
  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.climbSystem.setInchPosition(position);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    isDone = true;
    for (int i = 0; i < Robot.climbSystem.returnTalons().size(); i++) {
      if(!(Math.abs(this.position - Robot.climbSystem.getInchPosition(i)) <= 0.05)) {
        isDone = false;
      }
      if(Robot.climbSystem.isRevLimitSwitch(i) && Robot.climbSystem.getPosition(i) < 0) {
        isDone = true;
        break;
      }
    }
    return isDone;
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
