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
    public boolean extended = false;

    double inchesPerTick = 0.000388; 

    ArrayList<TalonSRX> climbTalons;
    ArrayList<Double> offset;

    //This method gets called when the climb system is instatiated in Robot.java
    public ClimbSystem() {
        offset = new ArrayList<Double>();
        climbTalons = new ArrayList<TalonSRX>();

        climbTalons.add(new TalonSRX(5));
        climbTalons.add(new TalonSRX(6));
        climbTalons.add(new TalonSRX(7));
        
        for (int i = 0; i < climbTalons.size(); i++) {
            offset.add(0.0);  
        }
        
        for (TalonSRX climbTalon : climbTalons) {
            initClimbTalon(climbTalon);
        }
        // climbTalons.get(1).setSensorPhase(true);
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
        talon.configForwardSoftLimitThreshold((int)(20.0/inchesPerTick), 10);
        talon.configReverseSoftLimitEnable(false, 10);
        talon.configReverseSoftLimitThreshold(-1000, 10);
        talon.configMaxIntegralAccumulator(0, 200, 10);
        talon.setInverted(true);
        talon.setSensorPhase(false);
    }

    public void setPosition(double pos) {
        for (int i = 0; i < climbTalons.size(); i++) {   
            setPosition(pos,i);
        }
    }

    private double getTickOffset(int talon){
        return offset.get(talon) / inchesPerTick;
    }

    public void setPosition(double pos, int talon) {
        climbTalons.get(talon).set(ControlMode.Position, pos + getTickOffset(talon));
    }

    public void setInchPosition(double pos) {
        for (int i = 0; i < climbTalons.size(); i++) {
            setInchPosition(pos,i);
        }
	}

    public void setInchPosition(double pos, int talon) {
        setPosition(pos / inchesPerTick, talon);
    }
    
    public int getRawPosition(int talon) {
        return climbTalons.get(talon).getSelectedSensorPosition(0);
    }

    public int getPosition(int talon) {
        return (int) (this.getRawPosition(talon) - getTickOffset(talon));
    }

    public double getInchPosition(int talon) {
        return this.getPosition(talon) * inchesPerTick;
    }

    public double getRawInchPosition(int talon) {
        return this.getRawPosition(talon) * inchesPerTick;
    }
    
    public boolean isRevLimitSwitch(int talon) {
        return !climbTalons.get(talon).getSensorCollection().isRevLimitSwitchClosed();
    }
    
    public void setPower(double power, int talon) {
      climbTalons.get(talon).set(ControlMode.PercentOutput, power);
    }

    public void setOffset(ArrayList<Double> o) {
        for (int i = 0; i < o.size(); i++) {
            offset.add(i, o.get(i));
            offset.remove(i + 1);
        }
    }

    public ArrayList<TalonSRX> getTalons() {
        return climbTalons;
    }


    private boolean[] getExtended() {
        boolean[] extended = new boolean[climbTalons.size()];
        for (int i = 0; i < climbTalons.size(); i++) {
                extended[i] = Math.abs(getInchPosition(i)) > 0.5;
        }
        return extended;
    }

    public boolean getExtendedFront() {
        boolean[] extended = getExtended(); 
        return extended[0] || extended[1];
    }

    
    public boolean getExtendedBack() {
        boolean[] extended = getExtended();
        return extended[2];
    }
}