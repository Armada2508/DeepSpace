/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;


/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class DriveSystem extends Subsystem {

  TalonSRX rightMain;
  TalonSRX rightFollower;
  TalonSRX leftMain;
  TalonSRX leftFollower;

  //This method gets called when the drive system is instatiated in Robot.java
  public DriveSystem() {
      //Mapping Talons
      rightMain = new TalonSRX(0);
      rightFollower = new TalonSRX(1);
      
      leftMain = new TalonSRX(11);
      leftFollower = new TalonSRX(10);
  
      //This method configures the Talons with settings like PID,
      //Voltag and Current limits, etc.
      //It also makes the second Talon mirror the first so
      //we only have to set the first talon(as they are in the same gearbox)
      TalonHelper.initTalonSet(rightMain, rightFollower, RobotMap.driveConfig);
      TalonHelper.initTalonSet(leftMain, leftFollower, RobotMap.driveConfig);  
      rightMain.setInverted(true);
      rightFollower.setInverted(true);
  }

  @Override
  public void initDefaultCommand() {
  }

  
	//Drives the left and right sides of the robot at the power specified
	public void drive(double powerL, double powerR){
		rightMain.set(ControlMode.Velocity, processDeadband(powerR));
		leftMain.set(ControlMode.Velocity, processDeadband(powerL));
  }
  
  //Gets encoder value of left side
	public int getLeftPosition()
	{
		return leftMain.getSelectedSensorPosition(0);
	}
	
  //Gets encoder value of right side
	public int getRightPosition()
	{
		return rightMain.getSelectedSensorPosition(0);
	}
  
  private double processDeadband(double input){
		double output = 0;
		
		if(Math.abs(input) >= RobotMap.DeadbandThreshold)
      output = input * RobotMap.driveConfig.MaxRPM;

		return output;
	}
	
}
