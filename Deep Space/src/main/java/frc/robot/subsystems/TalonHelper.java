package frc.robot.subsystems;

import frc.robot.*;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;

public final class TalonHelper {

	// only value that works
	private static final int pidIdx = 0;
	private static final int driveSystemTimeoutMs = 0;
	
	public static void initTalonSet(TalonSRX mainTalon, TalonSRX followerTalon, TalonConfig talonConfig){
		mainTalon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, pidIdx, driveSystemTimeoutMs);
		mainTalon.configNominalOutputForward(0f, driveSystemTimeoutMs);
		mainTalon.configNominalOutputReverse(0f, driveSystemTimeoutMs);
		mainTalon.configPeakOutputForward(talonConfig.PeakVoltage, driveSystemTimeoutMs);
		mainTalon.configPeakOutputReverse(-talonConfig.PeakVoltage, driveSystemTimeoutMs);
		mainTalon.configPeakCurrentLimit(0, 0); 
		mainTalon.configPeakCurrentDuration(0, 0);
		mainTalon.configContinuousCurrentLimit(talonConfig.PeakCurrent, 0);
		mainTalon.enableCurrentLimit(true); 
		mainTalon.set(ControlMode.Velocity, 0f);
		mainTalon.config_IntegralZone(0, 1000, 10);
		mainTalon.setSensorPhase(true);
        mainTalon.configForwardSoftLimitEnable(false, 0);
		mainTalon.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, 10);
		mainTalon.config_kP(0, talonConfig.P, driveSystemTimeoutMs);
		mainTalon.config_kI(0, talonConfig.I, driveSystemTimeoutMs); 
		mainTalon.config_kD(0, talonConfig.D, driveSystemTimeoutMs);
		mainTalon.config_kF(0, talonConfig.F, driveSystemTimeoutMs);
		mainTalon.configClosedloopRamp(talonConfig.Ramp, driveSystemTimeoutMs);
		mainTalon.setInverted(false);
		
		followerTalon.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, 10);
		followerTalon.setInverted(false);
		followerTalon.set(ControlMode.Follower, 0f);
		followerTalon.follow(mainTalon);
	}
}
