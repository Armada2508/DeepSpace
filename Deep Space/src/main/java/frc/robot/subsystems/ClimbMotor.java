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
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import java.util.*;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class ClimbMotor extends Subsystem {
    TalonSRX climbMotorTalon;

    //This method gets called when the climb system is instatiated in Robot.java
    public ClimbMotor() {
        TalonConfig climbMotorConfig = new TalonConfig(0, 0, 0, 0, 0, 0, 12, 30);
        climbMotorTalon = new TalonSRX(4);
        TalonHelper.initTalon(climbMotorTalon, climbMotorConfig);
    }

    @Override
    public void initDefaultCommand() {
        
    }
    
    public void setPower(double power) {
        climbMotorTalon.set(ControlMode.PercentOutput, power);
    }
}