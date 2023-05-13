package Project.Logic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListener implements ActionListener {
    private final ButtonListenerInterface listenerInterface;

    public ButtonListener(ButtonListenerInterface listenerInterface) {
        this.listenerInterface = listenerInterface;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        listenerInterface.buttonClicked();
    }
}