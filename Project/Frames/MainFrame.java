package Project.Frames;

import Project.Logic.FileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public class MainFrame extends JFrame {
    FileManager data = new FileManager("./Project/Data.xlsx");
    FileManager teamData = new FileManager("./Project/Teams.xlsx");
    JPanel dataPanel, buttonBox, buttonPanel, teamPanel;
    JButton newTeam, editTeam, deleteTeam, titleButton;
    Font tableFont = new Font("San Francisco", Font.PLAIN, 20);
    Font buttonFont =  new Font("San Fransisco", Font.BOLD, 12);

    private JPanel data() {
        dataPanel = new JPanel();
        dataPanel.setLayout(new BoxLayout(dataPanel, BoxLayout.Y_AXIS));
        dataPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        ArrayList<String> teams = (ArrayList<String>) teamData.getUniqueValues("team_name");
        teams.remove(0);    // Remove column name (location)

        titleButton = new JButton("Teams");
        titleButton.setFont(new Font("San Francisco", Font.BOLD, 22));
        titleButton.setPreferredSize(new Dimension(200, 50));
        titleButton.setFocusable(false);
        titleButton.setForeground(new Color(102, 153, 255));

        teamPanel = new JPanel(new GridLayout(teams.size() + 1, 1));
        teamPanel.add(titleButton);

        for (String team : teams) {
            JButton teamName = new JButton(team);
            teamName.setPreferredSize(new Dimension(200, 20));
            teamName.setFocusable(true);
            teamName.setFont(tableFont);
            teamName.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 1) {
                        String teamChoice = teamName.getText();
                        teamName.requestFocusInWindow();
                        System.out.println(teamChoice + " selected");
                    } else if (e.getClickCount() == 2) {
                        String teamChoice = teamName.getText();
                        DisplayTeamsDialog displayTeamsDialog = new DisplayTeamsDialog(teamChoice);
                        displayTeamsDialog.setVisible(true);
                        System.out.println(teamChoice + " dialog opened");
                    }
                }
            });
            teamPanel.add(teamName);
            dataPanel.add(teamPanel);
        }
        return dataPanel;
    }

    private JPanel buttons() {
        buttonBox = new JPanel(new GridLayout(3, 1));
        buttonBox.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));

        newTeam = new JButton("New Team");
        newTeam.setFont(buttonFont);
        newTeam.addActionListener(e -> {
            System.out.println("New Team clicked");
            AddTeamGUI addTeamGUI = new AddTeamGUI();
            addTeamGUI.setVisible(true);
        });
        buttonBox.add(newTeam);

        editTeam = new JButton("Edit Team");
        editTeam.setFont(buttonFont);
        editTeam.addActionListener(e -> {
            System.out.println("Edit Team clicked");
            System.out.println("Just avoiding");
        });
        buttonBox.add(editTeam);

        deleteTeam = new JButton("Delete Team");
        deleteTeam.setFont(buttonFont);
        deleteTeam.addActionListener(e -> {
            System.out.println("Delete Team clicked");
            System.out.println("Just avoiding");
        });
        buttonBox.add(deleteTeam);

        buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.add(buttonBox);

        return buttonPanel;
    }

    public MainFrame() {
        setTitle("Football Team Manager");
        setSize(500, 450);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(new Color(194, 214, 214));

        getContentPane().add(data(), BorderLayout.CENTER);
        getContentPane().add(buttons(), BorderLayout.EAST);
    }
}
