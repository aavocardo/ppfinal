package Project.Frames;

import static Project.Logic.FileManager.parseTeamID;
import static Project.Logic.FileManager.parseTeamName;
import static Project.Logic.FileManager.parseLocation;
import static Project.Logic.FileManager.parsePlayerID;
import static Project.Logic.FileManager.parsePlayerName;
import static Project.Logic.FileManager.parseSalary;
import Project.Logic.FileManager;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.Arrays;


public class DisplayTeamsDialog extends JFrame {
    JTable teamInformation, footballers;
    JDialog displayTeams;
    JScrollPane teamScrollPane, playerScrollPane;
    FileManager file = new FileManager("./Project/Data.xlsx");
    Object[] teamData, playerData;
    public DisplayTeamsDialog(String name) {
        teamData = file.getRow("team_name", name);
        String[] teamColumns = {"Team ID", "Team Name", "Location"};
        Object[][] teamTable = {{parseTeamID(teamData), parseTeamName(teamData), parseLocation(teamData)}};

        teamInformation = new JTable(teamTable, teamColumns);
        teamScrollPane = new JScrollPane(teamInformation);
        add(teamScrollPane);

        playerData = file.getAllRows("team_name", name);
        String[] footballerColumns = {"ID", "Name", "Salary"};
        Object[][] footballerTable = new Object[playerData.length][3];

        for (int i = 0; i < playerData.length; i++) {
            Object[] player = (Object[]) playerData[i];
            footballerTable[i][0] = parsePlayerID(player);
            footballerTable[i][1] = parsePlayerName(player);
            footballerTable[i][2] = parseSalary(player);
        }

        footballers = new JTable(footballerTable, footballerColumns);
        playerScrollPane = new JScrollPane(footballers);
        add(playerScrollPane);

        setSize(500, 600);
        pack();
    }
}
