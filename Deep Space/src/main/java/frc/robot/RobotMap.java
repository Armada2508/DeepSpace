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
  public static final TalonConfig liftConfig = new TalonConfig(1.0 * 1023 / 4096, 0.01 * 1023 / 4096, 0.01 * 1023 / 4096, 0, 0.1, 4000, 12, 30);
  public static final double maxAcceleration = 0.007 * 1023 / 4096;
  public static final int LiftLimit = 1400;

  public static final double lowCargo = 10;
  public static final double lowShield = 10;
  public static final double midCargo = 10;
  public static final double midShield = 10;
  public static final double highCargo = 10;
  public static final double highShield = 10;
  
}
