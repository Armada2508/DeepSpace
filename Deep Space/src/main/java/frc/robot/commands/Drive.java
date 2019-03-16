package frc.robot.commands;

import edu.wpi.first.wpilibj.command.*;
import frc.robot.*;

public class Drive extends Command {

    public Drive() {
        requires(Robot.driveSystem);
    }

    //Called before command is first run
    protected void initialize() {
    }

    Pair lastPowerSetting = new Pair(0,0);

    // Called repeatedly when this Command is scheduled to run
	
    private Pair limitAcceleration(long millisPerTick, Pair newPowerSetting)
    {
        double maxVelocityChange = RobotMap.maxAcceleration*millisPerTick/1000;
        // if(Robot.oi.rb.get())
        // {
        //     maxVelocityChange /= 2;
        // }
        
		double leftChange = newPowerSetting.left - lastPowerSetting.left;
        double rightChange = newPowerSetting.right - lastPowerSetting.right;
       
        double newLeft = Math.max(-maxVelocityChange, Math.min(maxVelocityChange, leftChange));
        double newRight = Math.max(-maxVelocityChange, Math.min(maxVelocityChange, rightChange));
        
        lastPowerSetting = new Pair(lastPowerSetting.left + newLeft, lastPowerSetting.right + newRight);
        
        return lastPowerSetting;
    }

    //Called Repeatedly
    protected void execute() {
		double RPower, LPower;
		RPower = Robot.oi.stick.getRawAxis(1);
		LPower = Robot.oi.stick.getRawAxis(1);
		RPower += Robot.oi.stick.getRawAxis(4);
        LPower -= Robot.oi.stick.getRawAxis(4);
        
		RPower = Math.max(-1, Math.min(1, RPower));
		LPower = Math.max(-1, Math.min(1, LPower));

        double powerMultipler;

        // if(Robot.climbSystem.getExtendedFront()) {
        //     powerMultipler = RobotMap.drivePowerWhileLiftedFront;
        // } else if(Robot.climbSystem.getExtendedBack()) {
        //     powerMultipler = RobotMap.drivePowerWhileLiftedBack;
        // } else 
        if (Robot.oi.rb.get()) {
            powerMultipler = RobotMap.sprintPower;
            // System.out.println("Sprinting");
        } else {
            powerMultipler = RobotMap.defaultPower;
            // System.out.println("NotSprinting");
        }
        
        Pair powers = limitAcceleration(20, new Pair(LPower * powerMultipler, RPower * powerMultipler));


		Robot.driveSystem.drive(-powers.left,-powers.right);
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