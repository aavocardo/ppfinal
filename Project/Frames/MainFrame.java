package Project.Frames;

import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTable;

public class MainFrame extends JFrame {
    JPanel dataPanel, buttonBox, buttonPanel;
    JButton newTeam, editTeam, deleteTeam;
    JTable teamData;
    private JPanel buttons() {
        buttonBox = new JPanel(new GridLayout(3, 1));

        newTeam = new JButton("New Team");
        newTeam.addActionListener(e -> {
            System.out.println("New Team clicked");
            TeamDialog teamDialog = new TeamDialog();
            teamDialog.setVisible(true);
        });
        buttonBox.add(newTeam);

        editTeam = new JButton("Edit Team");
        newTeam.addActionListener(e -> {
            System.out.println("Edit Team clicked");
            System.out.println("Just avoiding");
        });
        buttonBox.add(editTeam);

        deleteTeam = new JButton("Delete Team");
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
        setSize(700, 600);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        dataPanel = new JPanel();

        JButton testButton = new JButton("Test Button");
        dataPanel.add(testButton);



        getContentPane().add(dataPanel, BorderLayout.CENTER);
        getContentPane().add(buttons(), BorderLayout.EAST);

        pack();
    }
}
