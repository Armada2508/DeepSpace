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
  public static final TalonConfig liftConfig = new TalonConfig(1.0 * 1023 / 4096, 0.01 * 1023 / 4096, 0.01 * 1023 / 4096, 0, 0.1, 4000, 12, 40);
  public static final TalonConfig climbConfig = new TalonConfig(1, .2 , 0, 0, 0.1, 4000, 12, 30);
  public static final double maxAcceleration = 4;
  //Software Height Limit for Lift
  public static final int LiftLimit = 2000;

  //Cargo/Shield Positions
  public static final double lowCargo = 1500;
  public static final double lowShield = 1500;
  public static final double midCargo = 1500;
  public static final double midShield = 1500;
  public static final double highCargo = 1500;
  public static final double highShield = 1500;

  //Input and Output Speeds for intake(-1.0 min, 1.0 max)
  public static final double cargoIntakeSpeed = 1.0;
  public static final double cargoOutputSpeed = 0.5;
  
  //Power used for homing
  public static final double homingPower = 0.2;
  //Lift Margin(Inches)
  public static final double liftMargin = 0.0;

  public static final double shieldEjectWait = 0.15;
  
}
