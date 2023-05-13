package Project.Frames;

import javax.swing.*;
import java.awt.*;

public class TeamDialog extends JFrame {
    public TeamDialog() {
        // Initialize the components for Window 1
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Window 1");
        panel.add(label);
        add(panel);

        setLayout(new BorderLayout());

        setSize(200, 200);
    }
}