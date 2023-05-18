package Project.Frames;

import Project.Logic.FileManager;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import static Project.Logic.FileManager.*;


public class DisplayTeamsDialog extends JFrame {
    PlayerManager playerManager;
    TeamManager teamManager;
    String teamName;
    Object[] teamData, playerData;
    JTable teamInformation, playerInformation;
    JTableHeader teamHeader, footballerHeader;
    JPanel mainPanel, teamPanel, playerPanel, buttonPanel;
    JLabel teamLabel, footballerLabel;
    JScrollPane teamScrollPane, playerScrollPane;
    JButton editTeam, addFootballer;
    DefaultTableCellRenderer centerRenderer;
    String[] teamSection, footballerSection;
    FileManager file = new FileManager("./Project/Data.xlsx");
    FileManager teamFile = new FileManager("./Project/Teams.xlsx");
    Color headerColor = new Color(204, 217, 255);
    Color tableColor = new Color(153, 179, 255);
    Font headerFont = new Font("San Francisco", Font.BOLD, 12);
    Font tableFont = new Font("San Francisco", Font.PLAIN, 12);
    Font labelFont = new Font("San Francisco", Font.BOLD, 16);
    String footballerID, footballerName, footballerSalary;

    private JPanel teamInformation() {
        teamPanel = new JPanel();
        teamPanel.setLayout(new BoxLayout(teamPanel, BoxLayout.PAGE_AXIS));

        teamData = file.getRow("team_name", this.teamName);
        Object[][] teamTable = {{parseTeamID(teamData), parseTeamName(teamData), parseLocation(teamData)}};
        String[] teamColumns = {"Team ID", "Team Name", "Location"};

        teamInformation = new JTable(teamTable, teamColumns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public boolean isCellSelected(int row, int column) {
                return false;
            }
        };
        teamInformation.setBackground(tableColor);
        teamInformation.setFont(tableFont);
        teamInformation.setRowHeight(35);

        teamHeader = teamInformation.getTableHeader();
        teamHeader.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public void setHorizontalAlignment(int alignment) {
                super.setHorizontalAlignment(JLabel.CENTER);
            }

            @Override
            public void setBackground(Color c) {
                super.setBackground(headerColor);
            }

            @Override
            public void setFont(Font font) {
                super.setFont(headerFont);
            }
        });

        for (int i = 0; i < teamInformation.getColumnCount(); i++) {
            teamInformation.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        teamLabel = new JLabel("Team Information");
        teamLabel.setFont(labelFont);
        teamLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        teamScrollPane = new JScrollPane(teamInformation);
        teamScrollPane.setPreferredSize(new Dimension(300, 56));
        teamScrollPane.setAlignmentX(CENTER_ALIGNMENT);

        teamPanel.add(teamLabel, BorderLayout.PAGE_START);
        teamPanel.add(teamScrollPane, BorderLayout.PAGE_END);

        return teamPanel;
    }

    private JPanel playerInformation() {
        playerPanel = new JPanel();
        playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.PAGE_AXIS));

        playerData = file.getAllRows("team_name", this.teamName);
        Object[][] footballerTable = new Object[playerData.length][3];
        String[] footballerColumns = {"ID", "Name", "Salary"};

        for (int i = 0; i < playerData.length; i++) {
            Object[] player = (Object[]) playerData[i];
            footballerTable[i][0] = parsePlayerID(player);
            footballerTable[i][1] = parsePlayerName(player);
            footballerTable[i][2] = parseSalary(player);
        }

        playerInformation = new JTable(footballerTable, footballerColumns);
        playerInformation.setBackground(tableColor);
        playerInformation.setFont(tableFont);
        playerInformation.setRowHeight(25);

        footballerHeader = playerInformation.getTableHeader();
        footballerHeader.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public void setHorizontalAlignment(int alignment) {
                super.setHorizontalAlignment(JLabel.CENTER);
            }

            @Override
            public void setBackground(Color c) {
                super.setBackground(headerColor);
            }

            @Override
            public void setFont(Font font) {
                super.setFont(headerFont);
            }
        });

        for (int i = 0; i < playerInformation.getColumnCount(); i++) {
            playerInformation.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        footballerLabel = new JLabel("Player Information");
        footballerLabel.setFont(labelFont);
        footballerLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        playerScrollPane = new JScrollPane(playerInformation);
        playerScrollPane.setPreferredSize(new Dimension(350, 200));
        playerScrollPane.setAlignmentX(CENTER_ALIGNMENT);

        playerPanel.add(footballerLabel, BorderLayout.PAGE_START);
        playerPanel.add(playerScrollPane, BorderLayout.PAGE_END);

        return playerPanel;
    }

    private JPanel buttonPanel() {
        buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        editTeam = new JButton("Edit Team");
        editTeam.addActionListener(e -> {
            teamManager = new TeamManager();
            teamManager.setVisible(true);
        });

        addFootballer = new JButton("Add Footballer");
        addFootballer.addActionListener(e -> {
            playerManager = new PlayerManager();
            playerManager.setVisible(true);

            Object[] td = teamFile.getRow("team_name", this.teamName);
            teamSection = file.teamData(parseTeamID(td), parseTeamName(td), parseLocation(td));
            playerManager.saveFootballerButton.addActionListener(l -> {
                footballerID = playerManager.footballerIDField.getText();
                footballerName = playerManager.footballerNameField.getText();
                footballerSalary = playerManager.footballerSalaryField.getText();
                System.out.println(footballerSalary);

                footballerSection = file.footballerData(footballerID, footballerName, footballerSalary);
                file.addDataRow(teamSection, footballerSection);
                playerManager.dispose();
            });
        });

        buttonPanel.add(editTeam);
        buttonPanel.add(addFootballer);

        return buttonPanel;
    }

    protected DisplayTeamsDialog(String name) {
        this.teamName = name;

        centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        mainPanel.add(teamInformation());
        mainPanel.add(playerInformation());
        mainPanel.add(buttonPanel());

        setTitle(name + " Information");
        add(mainPanel);
        setResizable(false);
        setSize(400, 450);
    }
}
