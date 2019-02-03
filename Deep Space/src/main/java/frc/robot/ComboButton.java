package frc.robot;

import edu.wpi.first.wpilibj.buttons.*;

public class ComboButton extends Button {
    private Button[] buttons;
    public ComboButton(Button... buttons){
        this.buttons = buttons;
    }
    public boolean get() {
        for (Button button : buttons) {
            if(button.get())
                return false;
        }
        return true;
    }
}