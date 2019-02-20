/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

import java.util.ArrayList;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.*;
import frc.robot.subsystems.*;
import frc.robot.commands.*;
import frc.robot.commands.ShieldEject.Eject;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */

public class Robot extends TimedRobot {
  public static ShieldEject shieldEject = new ShieldEject();
  public static DriveSystem driveSystem = new DriveSystem();
  public static Intake intake = new Intake();
  public static ClimbSystem climbSystem = new ClimbSystem();
  public static ClimbMotor climbMotor = new ClimbMotor();
  public static OI oi;
  Command autonomousCommand;
  private static Command eject;
  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    // UsbCamera topCamera = CameraServer.getInstance().startAutomaticCapture();
    // UsbCamera frontCamera = CameraServer.getInstance().startAutomaticCapture();
    // topCamera.setResolution(RobotMap.cameraSettings.topCameraWidth, RobotMap.cameraSettings.topCameraHeight);
    // frontCamera.setResolution(RobotMap.cameraSettings.frontCameraWidth, RobotMap.cameraSettings.frontCameraHeight);
    // topCamera.setFPS(RobotMap.cameraSettings.topCameraFPS);
    // frontCamera.setFPS(RobotMap.cameraSettings.frontCameraFPS);
    oi = new OI();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }


  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    Command DriveCMD = new Drive();
    DriveCMD.start();
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.

    

  
    Command homeActuators = new HomeActuatorGroup();
    homeActuators.start();

    
  }
  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    ArrayList<TalonSRX> things = climbSystem.getTalons();
    // System.out.println("Current " + things.get(0).getOutputCurrent() + " " + things.get(1).getOutputCurrent() + " " + things.get(2).getOutputCurrent());
    // System.out.println("RawPos " + climbSystem.getRawPosition(0) + " " + climbSystem.getRawPosition(1) + " " + climbSystem.getRawPosition(2));
    // System.out.println("LimitSwitch " + climbSystem.isRevLimitSwitch(0) + " " + climbSystem.isRevLimitSwitch(1) + " " + climbSystem.isRevLimitSwitch(2));
    // System.out.println("LimitSwitch " + climbSystem.getInchPosition(0) + " " + climbSystem.getInchPosition(1) + " " + climbSystem.getInchPosition(2));
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
