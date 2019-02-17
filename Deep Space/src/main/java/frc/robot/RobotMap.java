/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  //Minimum distance the stick must be moved to drive
  public static final double DeadbandThreshold = 0.05;
  //Settings for the DriveSystem TalonSRXs
  public static final TalonConfig driveConfig = new TalonConfig(1.0 * 1023 / 4096, 0.01 * 1023 / 4096, 0.01 * 1023 / 4096, 0, 0.1, 4000, 12, 40);
  public static final TalonConfig climbConfig = new TalonConfig(1, .2 , 0, 0, 0.1, 4000, 12, 30);
  public static final TalonConfig intakeConfig = new TalonConfig(0, 0, 0, 0, 1, 20000, 12, 40);
  public static final double maxAcceleration = 4;
  //Software Height Limit for Lift
  public static final int LiftLimit = 2000;

  //Input and Output Speeds for intake(-1.0 min, 1.0 max)
  public static final double fastCargoIntakeSpeed = 1.0;
  public static final double slowCargoIntakeSpeed = 0.5;
  public static final double fastCargoOutputSpeed = 1.0;
  public static final double slowCargoOutputSpeed = 0.5;

  //Power used for homing
  public static final double homingPower = 0.2;
  //Lift Margin(Inches)
  public static final double liftMargin = 0.0;

  public static final double shieldEjectWait = 0.5;

  public static final double lowPlatformHeight = 6.0;
  public static final double highPlatformHeight = 19.0;
}
