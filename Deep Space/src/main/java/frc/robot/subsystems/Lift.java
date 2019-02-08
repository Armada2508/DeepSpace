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
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Lift extends Subsystem {

    TalonSRX liftMain;
    TalonSRX liftFollower;
    //This method gets called when the drive system is instatiated in Robot.java
    public Lift() {
        liftMain = new TalonSRX(9);
        liftFollower = new TalonSRX(8);

        TalonHelper.initTalonSet(liftMain, liftFollower, RobotMap.liftConfig);
    
        liftMain.configForwardSoftLimitEnable(true, 10);
        liftMain.configForwardSoftLimitThreshold(RobotMap.LiftLimit, 10);
        liftMain.configMaxIntegralAccumulator(0, 200, 10);
        
        liftMain.setSensorPhase(true);
        liftFollower.setInverted(false);
    }

    @Override
    public void initDefaultCommand() {
        
    }

    public void moveLift(double position) {
		liftMain.set(ControlMode.Position, position);
    }

    public int getPosition() {
        return liftMain.getSelectedSensorPosition(0);
    }
    
    
}
