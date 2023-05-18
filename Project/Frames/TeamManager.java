package Project.Frames;

import Project.Logic.FileManager;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;


public class TeamManager extends JFrame {
    JLabel teamIDLabel, teamNameLabel, teamLocationLabel;
    JTextField teamIDField, teamNameField, teamLocationField;
    JButton addFootballerButton, saveTeamButton;
    JPanel fieldsPanel, buttonsPanel;
    String teamID, teamName, teamLocation;
    FileManager data = new FileManager("./Project/Data.xlsx");
    FileManager teamData = new FileManager("./Project/Teams.xlsx");

    private JPanel buttonsPanel() {
        // Create a panel for the buttons
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        // Create the buttons
        addFootballerButton = new JButton("Add Footballer");
        saveTeamButton = new JButton("Save Team");

        // Add action listener
        addFootballerButton.addActionListener(e -> {
            teamID = teamIDField.getText();
            teamName = teamNameField.getText();
            teamLocation = teamLocationField.getText();

            String[] td = {teamID, teamName, teamLocation};

            PlayerManager playerManager = new PlayerManager();
            playerManager.setVisible(true);

            playerManager.saveFootballerButton.addActionListener((ActionEvent event) -> {
                String[] fd = data.footballerData(playerManager.footballerIDField.getText(), playerManager.footballerNameField.getText(), playerManager.footballerSalaryField.getText());
                data.addDataRow(td, fd);
            });
        });

        saveTeamButton.addActionListener(e -> {
            String[] td = teamData.teamData(teamIDField.getText(), teamNameField.getText(), teamLocationField.getText());
            teamData.addRow(td);
            dispose();
        });

        // Add buttons to panel
        buttonsPanel.add(addFootballerButton);
        buttonsPanel.add(saveTeamButton);

        return buttonsPanel;
    }

    private JPanel fieldsPanel() {
        // Create a panel for the labels and text fields
        fieldsPanel = new JPanel();
        fieldsPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 5, 20));
        fieldsPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // Create labels for team ID, team name, and team location
        teamIDLabel = new JLabel("Team ID");
        teamNameLabel = new JLabel("Team Name");
        teamLocationLabel = new JLabel("Team Location");

        // Create text fields for team ID, team name, and team location
        teamIDField = new JTextField(15);
        teamNameField = new JTextField(15);
        teamLocationField = new JTextField(15);

        // Add constraints for team ID label and field
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(0, 0, 0, 5);
        fieldsPanel.add(teamIDLabel, c);

        c.gridx = 1;
        c.gridy = 0;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(0, 5, 0, 0);
        fieldsPanel.add(teamIDField, c);

        // Add constraints for team name label and field
        c.gridx = 0;
        c.gridy = 1;
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(0, 0, 0, 5);
        fieldsPanel.add(teamNameLabel, c);

        c.gridx = 1;
        c.gridy = 1;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(0, 5, 0, 0);
        fieldsPanel.add(teamNameField, c);

        // Add constraints for team location label and field
        c.gridx = 0;
        c.gridy = 2;
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(0, 0, 0, 5);
        fieldsPanel.add(teamLocationLabel, c);

        c.gridx = 1;
        c.gridy = 2;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(0, 5, 0, 0);
        fieldsPanel.add(teamLocationField, c);

        return fieldsPanel;
    }

    public TeamManager() {
        // Set up the JFrame
        setTitle("Team Manager");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        add(fieldsPanel(), BorderLayout.CENTER);
        add(buttonsPanel(), BorderLayout.SOUTH);

        // Pack and display the JFrame
        pack();
        setPreferredSize(new Dimension(420, 240)); // Set the window size
        setLocationRelativeTo(null); // Center the JFrame on the screen
        setResizable(false);
        setVisible(true);
    }
}
