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

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Intake extends Subsystem {
    TalonConfig intakeConfig = new TalonConfig(0, 0, 0, 0, 1, 18730, 12, 40);
    TalonSRX intake;
    //This method gets called when the drive system is instatiated in Robot.java
    public Intake() {
        intake = new TalonSRX(2);
        
        intake.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, 10);
        intake.configForwardSoftLimitEnable(false);
        intake.configNominalOutputForward(0f, 0);
		intake.configNominalOutputReverse(0f, 0);
		intake.configPeakOutputForward(intakeConfig.PeakVoltage, 0);  
		intake.configPeakOutputReverse(-intakeConfig.PeakVoltage, 0);
		intake.configPeakCurrentLimit(0, 0); 
		intake.configPeakCurrentDuration(0, 0);
		intake.configContinuousCurrentLimit(intakeConfig.PeakCurrent, 0);
        intake.enableCurrentLimit(true); 
		intake.config_kP(0, intakeConfig.P, 0);
		intake.config_kI(0, intakeConfig.I, 0); 
		intake.config_kD(0, intakeConfig.D, 0);
		intake.config_kF(0, intakeConfig.F, 0);
		intake.configClosedloopRamp(intakeConfig.Ramp, 0);
    }

    @Override
    public void initDefaultCommand() {
        intake.set(ControlMode.PercentOutput, 0);
    }

    public void IntakeCargo() {
		intake.set(ControlMode.PercentOutput, RobotMap.cargoIntakeSpeed);
    }

    public void OutputCargo() {
		intake.set(ControlMode.PercentOutput, -RobotMap.cargoOutputSpeed);
    }

    public void StopCargo() {
        intake.set(ControlMode.PercentOutput, 0);
    }
}
