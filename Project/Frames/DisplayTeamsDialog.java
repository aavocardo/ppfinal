package Project.Frames;

import Project.Logic.FileManager;

import javax.swing.*;
import java.awt.*;

public class DisplayTeamsDialog extends JFrame {
    JPanel panel;
    JLabel label;
    JTable teamInformation, footballers;
    FileManager file = new FileManager("./Project/Data.xlsx");
    Object[] data;
    public DisplayTeamsDialog(String teamName) {
        data = file.getAllRows("team_name", teamName);
        panel = new JPanel();

        label = new JLabel();
        label.setText("Team Information");

        add(panel);
        setLayout(new BorderLayout());
        setSize(500, 600);
    }
}
