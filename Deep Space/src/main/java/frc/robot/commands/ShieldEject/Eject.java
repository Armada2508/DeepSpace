/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.ShieldEject;

import edu.wpi.first.wpilibj.command.*;
import frc.robot.RobotMap;

/**
 * An example command.  You can replace me with your own command.
 */
public class Eject extends CommandGroup {
  public Eject() {
    addSequential(new Extend());
    addSequential(new WaitCommand(RobotMap.shieldEjectWait));
    addSequential(new Retract());
  }
}
