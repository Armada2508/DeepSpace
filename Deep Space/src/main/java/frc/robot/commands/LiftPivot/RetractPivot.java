package frc.robot.commands.LiftPivot;

import edu.wpi.first.wpilibj.command.*;
import frc.robot.*;

public class RetractPivot extends Command {

    public RetractPivot() {
        requires(Robot.liftPivot);
    }

    //Called before command is first run
    protected void initialize() {
        Robot.liftPivot.Retract();
    }
    //Returns true when the command is finished
    protected boolean isFinished() {
        return false;
    }
    //Called when the command ends
    protected void end() {

    }
    //Called when the command is interruped
    protected void interrupted() {

    }
}