/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.TalonConfig;
import frc.robot.TalonHelper;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Intake extends Subsystem {
    
    TalonSRX intakeMain;
    TalonSRX intakeFollower;
    //This method gets called when the drive system is instatiated in Robot.java
    public Intake() {
      intakeMain = new TalonSRX(8);
      intakeFollower = new TalonSRX(9);

      TalonHelper.initTalonSet(intakeMain, intakeFollower, RobotMap.intakeConfig);
      IntakeConfig(intakeMain);
      IntakeConfig(intakeFollower);
    }

    @Override
    public void initDefaultCommand() {
      intakeMain.set(ControlMode.PercentOutput, 0);
    }

    public void IntakeConfig(TalonSRX intake) {
      intake.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, 10);
      intake.configForwardSoftLimitEnable(false);
      intake.configNominalOutputForward(0f, 0);
  		intake.configNominalOutputReverse(0f, 0);
  		intake.configPeakOutputForward(RobotMap.intakeConfig.PeakVoltage, 0);
  		intake.configPeakOutputReverse(-RobotMap.intakeConfig.PeakVoltage, 0);
  		intake.configPeakCurrentLimit(0, 0);
  		intake.configPeakCurrentDuration(0, 0);
  		intake.configContinuousCurrentLimit(RobotMap.intakeConfig.PeakCurrent, 0);
      intake.enableCurrentLimit(true);
  		intake.config_kP(0, RobotMap.intakeConfig.P, 0);
  		intake.config_kI(0, RobotMap.intakeConfig.I, 0);
  		intake.config_kD(0, RobotMap.intakeConfig.D, 0);
  		intake.config_kF(0, RobotMap.intakeConfig.F, 0);
  		intake.configClosedloopRamp(RobotMap.intakeConfig.Ramp, 0);
      intake.setInverted(true);
    }
    public void IntakeCargo(boolean fast) {
      if(fast)
        intakeMain.set(ControlMode.PercentOutput, RobotMap.fastCargoIntakeSpeed);
      else
        intakeMain.set(ControlMode.PercentOutput, RobotMap.slowCargoIntakeSpeed);
    }

    public void OutputCargo(boolean fast) {
      if(fast)
        intakeMain.set(ControlMode.PercentOutput, -RobotMap.fastCargoOutputSpeed);
      else
        intakeMain.set(ControlMode.PercentOutput, -RobotMap.slowCargoOutputSpeed);  
    }

    public void StopCargo() {
        intakeMain.set(ControlMode.PercentOutput, 0);
    }
}
