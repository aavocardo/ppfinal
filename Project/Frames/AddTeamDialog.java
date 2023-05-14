package Project.Frames;

import javax.swing.*;
import java.awt.*;

public class AddTeamDialog extends JFrame {
    public AddTeamDialog() {
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Window 1");
        panel.add(label);
        add(panel);

        setLayout(new BorderLayout());

        setSize(200, 200);
    }
}