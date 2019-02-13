package frc.robot;

import frc.robot.*;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;

public final class TalonHelper {

	//Only value that works
	private static final int pidIdx = 0;
	private static final int driveSystemTimeoutMs = 0;
	
	public static void initTalonSet(TalonSRX mainTalon, TalonSRX followerTalon, TalonConfig talonConfig){
		initTalon(mainTalon, talonConfig);
		
		//Clears Limit Switch
		followerTalon.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, 10);
		//Resets Motor Direction
		followerTalon.setInverted(false);
		//Set Follower Talon's Speed to Zero
		followerTalon.set(ControlMode.Follower, 0f);
		//Makes Follower Talon follow Main Talon
		followerTalon.follow(mainTalon);
	}

	public static void initTalon(TalonSRX mainTalon, TalonConfig talonConfig){
		//Configure/Add Encoder
		mainTalon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, pidIdx, driveSystemTimeoutMs);
		//Set Minimum Motor ourput to 0(No Limit)
		mainTalon.configNominalOutputForward(0f, driveSystemTimeoutMs);
		mainTalon.configNominalOutputReverse(0f, driveSystemTimeoutMs);
		//Set Maximum Output Voltage
		mainTalon.configPeakOutputForward(talonConfig.PeakVoltage, driveSystemTimeoutMs);
		mainTalon.configPeakOutputReverse(-talonConfig.PeakVoltage, driveSystemTimeoutMs);
		//Set Maximum Output Current and Current Duration
		mainTalon.configPeakCurrentLimit(0, 0); 
		mainTalon.configPeakCurrentDuration(0, 0);
		//Set Maximum Continuous Output Current
		mainTalon.configContinuousCurrentLimit(talonConfig.PeakCurrent, 0);
		//Enable Current Limit
		mainTalon.enableCurrentLimit(true); 
		//Set Velocity to 0
		mainTalon.set(ControlMode.Velocity, 0f);
		//Sets Maximum Integral
		mainTalon.config_IntegralZone(0, 1000, 10);
		//Sets Encoder as Inverted
		mainTalon.setSensorPhase(true);
		//Set Software Limit
		mainTalon.configForwardSoftLimitEnable(false, 0);
		//Config/Add Limit Switch
		mainTalon.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, 10);
		//Set PIDF Values
		mainTalon.config_kP(0, talonConfig.P, driveSystemTimeoutMs);
		mainTalon.config_kI(0, talonConfig.I, driveSystemTimeoutMs); 
		mainTalon.config_kD(0, talonConfig.D, driveSystemTimeoutMs);
		mainTalon.config_kF(0, talonConfig.F, driveSystemTimeoutMs);
		//Set ramp
		mainTalon.configClosedloopRamp(talonConfig.Ramp, driveSystemTimeoutMs);
		//Set the main Talon as not Inverted
		mainTalon.setInverted(false);
	}

}
