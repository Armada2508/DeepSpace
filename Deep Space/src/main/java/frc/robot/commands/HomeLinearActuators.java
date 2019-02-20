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

  private int cyclecount;
  private boolean isDone = false;
  private int[][] previousValues;

  public HomeLinearActuators() {
    requires(Robot.climbSystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    cyclecount = 0;
    previousValues = new int[Robot.climbSystem.returnTalons().size()][RobotMap.linearActuatorIntegralAccumualtorLimit];
    boolean linearActuatorsOut = false;
    //extends all actuators until they are off the switches
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
    }
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    cyclecount++;
    boolean linearActuatorsIn = true;
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
    }
    for (int talon = 0; talon < Robot.climbSystem.returnTalons().size(); talon++) {
      for (int i = RobotMap.linearActuatorIntegralAccumualtorLimit - 1; i > 0 ; i--) {
        previousValues[talon][i] = previousValues[talon][i - 1];
      }
      previousValues[talon][0] = Robot.climbSystem.getRawPosition(talon);      
      if(cyclecount >= RobotMap.linearActuatorIntegralAccumualtorLimit) {
        int change = Math.abs(previousValues[talon][0] - previousValues[talon][RobotMap.linearActuatorStopThreshold-1]);
        if(change > RobotMap.linearActuatorStopThreshold) {
          isDone = true;
        } 
      }
      if(cyclecount > RobotMap.linearActuatorHomingTimeout){
        isDone = true;
      }
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return isDone;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    ArrayList<Double> offsets = new ArrayList<Double>();
    for (int i = 0; i < Robot.climbSystem.returnTalons().size(); i++) {
      offsets.add(i, Robot.climbSystem.getRawInchPosition(i) + RobotMap.liftMargin);
    }
    Robot.climbSystem.setOffset(offsets);
    Robot.climbSystem.setPosition(0);
  }
  

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
