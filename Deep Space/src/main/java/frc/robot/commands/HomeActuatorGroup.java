package frc.robot.commands;


import edu.wpi.first.wpilibj.command.CommandGroup;

public class HomeActuatorGroup  extends CommandGroup {
	public HomeActuatorGroup() {		
        addSequential(new MoveClimbOffLimit());        
        addSequential(new HomeLinearActuators());
    }
}