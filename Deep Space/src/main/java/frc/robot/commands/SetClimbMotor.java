package frc.robot.commands;

import edu.wpi.first.wpilibj.command.*;
import frc.robot.*;

public class SetClimbMotor extends Command {

    private double power;

    public SetClimbMotor(double power) {
        requires(Robot.climbMotor);
        this.power = power;
    }

    //Called before command is first run
    protected void initialize() {
        Robot.climbMotor.setPower(this.power);
    }


    //Called Repeatedly
    protected void execute() {
    }
        //Returns true when the command is finished
    protected boolean isFinished() {
        return true;
    }
    //Called when the command ends
    protected void end() {
    }
    //Called when the command is interruped
    protected void interrupted() {
        Robot.climbMotor.setPower(0.0);
    }
}