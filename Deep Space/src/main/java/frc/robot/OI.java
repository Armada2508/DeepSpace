/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import frc.robot.commands.Climb;
import frc.robot.commands.Intake.*;
import edu.wpi.first.wpilibj.buttons.*;

import edu.wpi.first.wpilibj.Joystick;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());


  public OI() {
    intakeFast.whileHeld(new Intake(true));
    intakeSlow.whileHeld(new Intake(false));
    outputFast.whileHeld(new Output(true));
    outputSlow.whileHeld(new Output(false));

    extendTop.whenPressed(new Climb(RobotMap.highPlatformHeight));
    extendBottom.whenPressed(new Climb(RobotMap.lowPlatformHeight));
    retractFront.whenPressed(new Climb(0.0, 0, 1));
    retractBack.whenPressed(new Climb(0.0, 2));
  }

  // Joystick on port 0(Joystick)
  public Joystick stick = new Joystick(0);

  // Joystick on port 1(Button Board)
  public Joystick buttonBoard = new Joystick(1);

  //Maps buttons on controller
  public Button climbEnable = new JoystickButton(buttonBoard, 1);
  public Button lowClimb = new JoystickButton(buttonBoard, 2);
  public Button highClimb = new JoystickButton(buttonBoard, 3);
  public Button retract1 = new JoystickButton(buttonBoard, 4);
  public Button retract2 = new JoystickButton(buttonBoard, 5);

  public Button a = new JoystickButton(stick, 1);
  public Button b = new JoystickButton(stick, 2);
  public Button x = new JoystickButton(stick, 3);
  public Button y = new JoystickButton(stick, 4);
  public Button lb = new JoystickButton(stick, 5);
  public Button rb = new JoystickButton(stick, 6);
  public Button back = new JoystickButton(stick, 7);
  public Button start = new JoystickButton(stick, 8);
  Button l3 = new JoystickButton(stick, 9);
  Button r3 = new JoystickButton(stick, 10);
  double lX = stick.getRawAxis(0);
  public double lY = stick.getRawAxis(1);
  double lTrigger = stick.getRawAxis(2);
  double rTrigger = stick.getRawAxis(3);
  double rX = stick.getRawAxis(4);
  public double rY = stick.getRawAxis(5);
  int d_pad = stick.getPOV();

  ExclusiveButton intakeFast = new ExclusiveButton(y, a, b, x, lb);
  ExclusiveButton intakeSlow = new ExclusiveButton(x, a, b, y, lb);
  ExclusiveButton outputFast = new ExclusiveButton(a, b, x, y, lb);
  ExclusiveButton outputSlow = new ExclusiveButton(b, a, x, y, lb);

  Button shieldPiston = start;
  Button switchDriveDirection = back;


  ComboButton extendTop = new ComboButton(climbEnable, highClimb);
  ComboButton extendBottom = new ComboButton(climbEnable, lowClimb);
  ComboButton retractFront = new ComboButton(climbEnable, retract1);
  ComboButton retractBack = new ComboButton(climbEnable, retract2);
  
  // ComboButton retract = new ComboButton(rb, a);
  // ComboButton extend = new ComboButton(rb, b);
//  TriggerButton linearActuatorForward = new TriggerButton(stick, 2, 0.1);
//  TriggerButton linearActuatorBackwards = new TriggerButton(stick, 3, 0.1);
}