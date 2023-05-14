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
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import static Project.Logic.FileManager.*;


public class DisplayTeamsDialog extends JFrame {
    JTable teamInformation, footballerInformation;
    JTableHeader teamHeader, footballerHeader;
    JPanel tablePanel, buttonPanel;
    JLabel teamLabel, footballerLabel;
    JScrollPane teamScrollPane, playerScrollPane;
    JButton editTeam, addFootballer;
    DefaultTableCellRenderer centerRenderer;
    Color headerColor = new Color(204, 217, 255);
    Color tableColor = new Color(153, 179, 255);
    Font headerFont = new Font("San Francisco", Font.BOLD, 12);
    Font tableFont = new Font("San Francisco", Font.PLAIN, 12);
    Font labelFont = new Font("San Francisco", Font.BOLD, 16);
    FileManager file = new FileManager("./Project/Data.xlsx");
    Object[] teamData, playerData;

    protected JPanel buttonPanel() {
        buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));


        editTeam = new JButton("Edit Team");

        addFootballer = new JButton("Add Footballer");

        buttonPanel.add(editTeam);
        buttonPanel.add(addFootballer);

        return buttonPanel;
    }

    protected DisplayTeamsDialog(String name) {
        tablePanel = new JPanel();
        tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));

        centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        teamData = file.getRow("team_name", name);
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
        teamInformation.setRowHeight(45);
        teamInformation.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

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
        teamScrollPane.setPreferredSize(new Dimension(500, 77));
        teamScrollPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        tablePanel.add(teamLabel);
        tablePanel.add(teamScrollPane);

        playerData = file.getAllRows("team_name", name);
        Object[][] footballerTable = new Object[playerData.length][3];
        String[] footballerColumns = {"ID", "Name", "Salary"};

        for (int i = 0; i < playerData.length; i++) {
            Object[] player = (Object[]) playerData[i];
            footballerTable[i][0] = parsePlayerID(player);
            footballerTable[i][1] = parsePlayerName(player);
            footballerTable[i][2] = parseSalary(player);
        }

        footballerInformation = new JTable(footballerTable, footballerColumns);
        footballerInformation.setBackground(tableColor);
        footballerInformation.setFont(tableFont);
        footballerInformation.setRowHeight(25);

        footballerHeader = footballerInformation.getTableHeader();
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

        for (int i = 0; i < footballerInformation.getColumnCount(); i++) {
            footballerInformation.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        footballerLabel = new JLabel("Player Information");
        footballerLabel.setFont(labelFont);
        footballerLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        playerScrollPane = new JScrollPane(footballerInformation);
        playerScrollPane.setPreferredSize(new Dimension(500, footballerInformation.getPreferredSize().height));
        playerScrollPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        tablePanel.add(footballerLabel);
        tablePanel.add(playerScrollPane);
        tablePanel.add(buttonPanel());
        tablePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        add(tablePanel);

        setSize(550, 500);
    }
}
