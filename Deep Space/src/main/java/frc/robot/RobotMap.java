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
  public static final TalonConfig climbConfig = new TalonConfig(1, .2 , 0, 0, 0.1, 4000, 12, 10);
  public static final TalonConfig intakeConfig = new TalonConfig(0, 0, 0, 0, 1, 20000, 12, 40);
  public static final double maxAcceleration = 2;
  //Software Height Limit for Lift
  public static final int LiftLimit = 2000;

  //Input and Output Speeds for intake(-1.0 min, 1.0 max)
  public static final double fastCargoIntakeSpeed = 1.0;
  public static final double slowCargoIntakeSpeed = 0.5;
  public static final double fastCargoOutputSpeed = 1.0;
  public static final double slowCargoOutputSpeed = 0.5;

  //Power used for homing
  public static final double homingPower = 0.30;
  //Lift Margin(Inches)
  public static final double liftMargin = 0.0;

  public static final double shieldEjectWait = 0.20;

  public static final double lowPlatformHeight = 9.0;
  public static final double highPlatformHeight = 19.0;

  public static final int linearActuatorIntegralAccumualtorLimit = 20;
  public static final int linearActuatorHomingTimeout = 30*50; // 30 / 0.02
  public static final int linearActuatorStopThreshold = 20;

  class cameraSettings {
    public static final int topCameraWidth = 320;
    public static final int topCameraHeight = 240;
    public static final int topCameraFPS = 22;
    public static final int frontCameraWidth = 320;
    public static final int frontCameraHeight = 240;
    public static final int frontCameraFPS = 30;
  }
}
