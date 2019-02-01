/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.*;
import frc.robot.commands.*;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class ShieldEject extends Subsystem {

  private Compressor c;
  private Solenoid out;
  private Solenoid in;

  @Override
  public void initDefaultCommand() {
    c = new Compressor(0);
    c.setClosedLoopControl(true);
    out = new Solenoid(1);
    in = new Solenoid(0);
  }

  public void Extend() {
    out.set(true);
    in.set(false);
  }

  public void Retract() {
    out.set(false);
    in.set(true);
  }

  public void Vent() {
    out.set(false);
    in.set(false);
  }
}
