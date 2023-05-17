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

public class AddFootballerGUI extends JFrame {
    JLabel footballerIDLabel, footballerNameLabel, footballerSalaryLabel;
    JTextField footballerIDField, footballerNameField, footballerSalaryField;
    JButton saveFootballerButton;
    JPanel fieldPanel, buttonPanel;
    String[] teamData, footballerData;
    String footballerID, footballerName, footballerSalary;
    FileManager file = new FileManager("./Project/Data.xlsx");



    protected JPanel buttonsPanel() {
        // Create a panel for the buttons
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        // Create the button
        saveFootballerButton = new JButton("Save Footballer");

        // Add button to panel
        buttonPanel.add(saveFootballerButton);

        return buttonPanel;
    }

    private JPanel fieldsPanel() {
        // Create a panel for the labels and text fields
        fieldPanel = new JPanel();
        fieldPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 5, 20));
        fieldPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // Create labels for team ID, team name, and team location
        footballerIDLabel = new JLabel("Footballer ID");
        footballerNameLabel = new JLabel("Footballer Name");
        footballerSalaryLabel = new JLabel("Footballer Salary");

        // Create text fields for team ID, team name, and team location
        footballerIDField = new JTextField(15);
        footballerNameField = new JTextField(15);
        footballerSalaryField = new JTextField(15);

        // Add constraints for team ID label and field
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(0, 0, 0, 5);
        fieldPanel.add(footballerIDLabel, c);

        c.gridx = 1;
        c.gridy = 0;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(0, 5, 0, 0);
        fieldPanel.add(footballerIDField, c);

        // Add constraints for team name label and field
        c.gridx = 0;
        c.gridy = 1;
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(0, 0, 0, 5);
        fieldPanel.add(footballerNameLabel, c);

        c.gridx = 1;
        c.gridy = 1;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(0, 5, 0, 0);
        fieldPanel.add(footballerNameField, c);

        // Add constraints for team location label and field
        c.gridx = 0;
        c.gridy = 2;
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(0, 0, 0, 5);
        fieldPanel.add(footballerSalaryLabel, c);

        c.gridx = 1;
        c.gridy = 2;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(0, 5, 0, 0);
        fieldPanel.add(footballerSalaryField, c);

        return fieldPanel;
    }

    protected String[] footballerData() {
        footballerID = footballerIDField.getText();
        footballerName = footballerNameField.getText();
        footballerSalary = footballerSalaryField.getText();
        return new String[]{footballerID, footballerName, footballerSalary};
    }

    public AddFootballerGUI() {
        // Set up the JFrame
        setTitle("Player Manager");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        add(fieldsPanel(), BorderLayout.CENTER);
        add(buttonsPanel(), BorderLayout.SOUTH);

        // Pack and display the JFrame
        pack();
        setPreferredSize(new Dimension(350, 200)); // Set the window size
        setLocationRelativeTo(null); // Center the JFrame on the screen
        setResizable(false);
        setVisible(true);
    }
}
