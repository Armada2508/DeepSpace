/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.*;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import java.util.*;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class ClimbSystem extends Subsystem {
    double ticksPerInch = 0.000388; 

    ArrayList<TalonSRX> climbTalons;
    ArrayList<Double> offset;

    //This method gets called when the climb system is instatiated in Robot.java
    public ClimbSystem() {
        offset = new ArrayList<Double>();
        climbTalons = new ArrayList<TalonSRX>();

        climbTalons.add(new TalonSRX(5));
        climbTalons.add(new TalonSRX(6));
    //    climbTalons.add(new TalonSRX(7));
        
        for (int i = 0; i < climbTalons.size(); i++) {
            offset.add(0.0);  
        }
        
        for (TalonSRX climbTalon : climbTalons) {
            initClimbTalon(climbTalon);
        }
    }

    @Override
    public void initDefaultCommand() {
        
    }

    public void initClimbTalon(TalonSRX talon) {
        talon.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.Disabled, 10);
        talon.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.Disabled, 10);
        TalonHelper.initTalon(talon, RobotMap.climbConfig);
        talon.configClearPositionOnLimitR(false, 10);
        talon.configForwardSoftLimitEnable(true, 10);
        talon.configForwardSoftLimitThreshold((int)(20.0/ticksPerInch), 10);
        talon.configReverseSoftLimitEnable(false, 10);
        talon.configReverseSoftLimitThreshold(-1000, 10);
        talon.configMaxIntegralAccumulator(0, 200, 10);
        talon.setInverted(true);
        talon.setSensorPhase(false);
    }

    public void setPosition(double pos) {
        for (int i = 0; i < climbTalons.size(); i++) {   
            double tickOffset = offset.get(i) / ticksPerInch;
            climbTalons.get(i).set(ControlMode.Position, pos + tickOffset);
        }
    }

    public void setPosition(double pos, int talon) {
        climbTalons.get(talon).set(ControlMode.Position, pos);
    }

    public void setInchPosition(double pos) {
        for (int i = 0; i < climbTalons.size(); i++) {
            climbTalons.get(i).set(ControlMode.Position, ( pos + offset.get(i) ) / ticksPerInch);
        }
	}

    public void setInchPosition(double pos, int talon) {
        climbTalons.get(talon).set(ControlMode.Position, ( pos + offset.get(talon) ) / ticksPerInch);
    }
    
    public int getRawPosition(int talonNumber) {
        return climbTalons.get(talonNumber).getSelectedSensorPosition(0);
    }

    public int getPosition(int talonNumber) {
        double tickOffset = offset.get(talonNumber) / ticksPerInch;
        return this.getRawPosition(talonNumber) + (int)(tickOffset);
    }

    public double getInchPosition(int talonNumber) {
        return this.getPosition(talonNumber) * ticksPerInch;
    }

    public double getRawInchPosition(int talonNumber) {
        return this.getRawPosition(talonNumber) * ticksPerInch;
    }
    
    public boolean isRevLimitSwitch(int talonNumber) {
        return climbTalons.get(talonNumber).getSensorCollection().isRevLimitSwitchClosed();
    }
    
    public void setPower(double power, int talonNumber) {
      climbTalons.get(talonNumber).set(ControlMode.PercentOutput, power);
    }

    public void setOffset(ArrayList<Double> o) {
        for (int i = 0; i < o.size(); i++) {
            offset.add(i, o.get(i));
            offset.remove(i + 1);
        }
    }

    public ArrayList<TalonSRX> returnTalons() {
        return climbTalons;
    }
}