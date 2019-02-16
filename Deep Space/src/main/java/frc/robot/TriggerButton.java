package frc.robot;

import edu.wpi.first.wpilibj.buttons.*;
import edu.wpi.first.wpilibj.Joystick;

public class TriggerButton extends Button {
    private Joystick stick;
    private double threshold;
    private int axis;

    public TriggerButton(Joystick stick, int axis, double threshold) {
        this.stick = stick;
        this.threshold = threshold;
        this.axis = axis;
    }

    public boolean get() {
        if(this.stick.getRawAxis(axis) >= threshold) {
            return true;
        }
        return false;
    }
}