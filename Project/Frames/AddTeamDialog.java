package Project.Frames;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.awt.FlowLayout;

public class AddTeamDialog extends JFrame {
    JPanel inputPanel, buttonPanel, mainPanel;
    JTextField teamID, teamName, teamLocation;
    JButton addFootballer, saveTeam;
    private JTextField teamID() {
        teamID = new JTextField();
        teamID.setBounds(50, 100, 100, 50);

        return teamID;
    }

    private JTextField teamName() {
        teamName = new JTextField();
        teamName.setBounds(50, 150, 100, 50);

        return teamName;
    }

    private JTextField teamLocation() {
        teamLocation = new JTextField();
        teamLocation.setBounds(50, 150, 100, 50);

        return teamLocation;
    }

    private JPanel inputPanel() {
        inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.X_AXIS));

        inputPanel.add(teamID());
        inputPanel.add(teamName());
        inputPanel.add(teamLocation());

        inputPanel.setPreferredSize(new Dimension(400, 100));

        return inputPanel;
    }

    private JButton addFootballer() {
        addFootballer = new JButton();
        addFootballer.setText("Add Footballer");

        return addFootballer;
    }

    private JButton saveTeam() {
        saveTeam = new JButton();
        saveTeam.setText("Save Team");

        return saveTeam;
    }

    private JPanel buttonPanel() {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        buttonPanel.add(addFootballer());
        buttonPanel.add(saveTeam());

        return buttonPanel;
    }

    private JPanel mainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        mainPanel.add(inputPanel());
        mainPanel.add(buttonPanel());

        return mainPanel;
    }

    public AddTeamDialog() {
        add(mainPanel());

        setTitle("Add New Team");
        setResizable(false);
        setSize(500, 250);
    }
}