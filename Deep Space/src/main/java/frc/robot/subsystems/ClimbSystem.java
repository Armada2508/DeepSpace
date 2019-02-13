/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.*;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class ClimbSystem extends Subsystem {
    double inchPerPulse = 0.000388; 
    int offset = 0;

    TalonSRX climbTalon;
    //This method gets called when the climb system is instatiated in Robot.java
    public ClimbSystem() {
        climbTalon = new TalonSRX(6);

        TalonHelper.initTalon(climbTalon,RobotMap.climbConfig);
 
        climbTalon.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.Disabled, 10);
        climbTalon.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyClosed, 10);
        climbTalon.configClearPositionOnLimitR(false, 10);
        climbTalon.configForwardSoftLimitEnable(true, 10);
        climbTalon.configForwardSoftLimitThreshold((int)(20.0/inchPerPulse), 10);
        climbTalon.configReverseSoftLimitEnable(false, 10);
        climbTalon.configReverseSoftLimitThreshold(-1000, 10);
        climbTalon.configMaxIntegralAccumulator(0, 200, 10);
        climbTalon.setInverted(true);
        
        climbTalon.setSensorPhase(false);
    }

    @Override
    public void initDefaultCommand() {
        
    }

    public void setPosition(double position) {
		climbTalon.set(ControlMode.Position, position + offset);
    }

    public void setInchPosition(double position) {
		climbTalon.set(ControlMode.Position, position + offset);
    }

    public int getPosition() {
        return climbTalon.getSelectedSensorPosition(0);
    }

    public double getInchPosition() {
        return getPosition() * inchPerPulse;
    }
    
    public boolean isRevLimitSwitch() {
        return climbTalon.getSensorCollection().isRevLimitSwitchClosed();
    }
    
    public void setPower(double power) {
      climbTalon.set(ControlMode.PercentOutput, power);
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
    
}