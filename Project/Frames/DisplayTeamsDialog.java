package Project.Frames;


import static Project.Logic.FileManager.parseTeamID;
import static Project.Logic.FileManager.parseTeamName;
import static Project.Logic.FileManager.parseLocation;
import static Project.Logic.FileManager.parsePlayerID;
import static Project.Logic.FileManager.parsePlayerName;
import static Project.Logic.FileManager.parseSalary;
import Project.Logic.FileManager;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;


public class DisplayTeamsDialog extends JFrame {
    JTable teamInformation, footballerInformation;
    JTableHeader teamHeader, footballerHeader;
    JPanel tablePanel;
    JScrollPane teamScrollPane, playerScrollPane;
    DefaultTableCellRenderer centerRenderer;
    Color headerColor = new Color(204, 217, 255);
    Color tableColor = new Color(153, 179, 255);
    Font headerFont = new Font("San Francisco", Font.BOLD, 12);
    Font tableFont = new Font("San Francisco", Font.PLAIN, 12);
    FileManager file = new FileManager("./Project/Data.xlsx");
    Object[] teamData, playerData;
    public DisplayTeamsDialog(String name) {
        tablePanel = new JPanel();
        tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));

        centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        teamData = file.getRow("team_name", name);
        String[] teamColumns = {"Team ID", "Team Name", "Location"};
        Object[][] teamTable = {{parseTeamID(teamData), parseTeamName(teamData), parseLocation(teamData)}};

        teamInformation = new JTable(teamTable, teamColumns);
        teamInformation.setBackground(tableColor);
        teamInformation.setFont(tableFont);
        teamInformation.setRowHeight(30);

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

        teamScrollPane = new JScrollPane(teamInformation);
        tablePanel.add(teamScrollPane);

        playerData = file.getAllRows("team_name", name);
        String[] footballerColumns = {"ID", "Name", "Salary"};
        Object[][] footballerTable = new Object[playerData.length][3];

        for (int i = 0; i < playerData.length; i++) {
            Object[] player = (Object[]) playerData[i];
            footballerTable[i][0] = parsePlayerID(player);
            footballerTable[i][1] = parsePlayerName(player);
            footballerTable[i][2] = parseSalary(player);
        }

        footballerInformation = new JTable(footballerTable, footballerColumns);
        footballerInformation.setBackground(tableColor);
        footballerInformation.setFont(tableFont);
        footballerInformation.setRowHeight(30);

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

        playerScrollPane = new JScrollPane(footballerInformation);
        tablePanel.add(playerScrollPane);

        add(tablePanel);
        setSize(500, 600);
        pack();
    }
}
