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
public class LiftPivot extends Subsystem {
    
    private Solenoid in;
    private Solenoid out;

    public LiftPivot() {
    }

    @Override
    public void initDefaultCommand() {
        in = new Solenoid(3);
        out = new Solenoid(4);
        in.set(false);
        out.set(true);
    }

    public void Extend() {
        in.set(false);
        out.set(true);
    }

    public void Retract() {
        in.set(true);
        out.set(false);
    }

    public void Vent() {
        in.set(false);
        out.set(false);
    }
}
