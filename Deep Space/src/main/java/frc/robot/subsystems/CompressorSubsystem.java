/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.*;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class CompressorSubsystem extends Subsystem {

  private Compressor c;

  @Override
  public void initDefaultCommand() {
    c = new Compressor(0);
    c.setClosedLoopControl(true);
  }
}
