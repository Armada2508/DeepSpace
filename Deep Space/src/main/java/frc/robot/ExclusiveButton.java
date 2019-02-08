package frc.robot;

import edu.wpi.first.wpilibj.buttons.*;

public class ExclusiveButton extends Button {
    private Button[] excludedButtons;
    private Button requiredButton;

    public ExclusiveButton(Button requiredButton, Button... excludedButtons) {
        this.excludedButtons = excludedButtons;
        this.requiredButton = requiredButton;
    }

    public boolean get() {
        if (requiredButton.get()) {
            for (Button button : excludedButtons) {
                if (button.get())
                    return false;
            }
            return true;
        }
        return false;
    }
}