package Project.Scratch;

import javax.swing.*;

public class MyApplication {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Football Team Manager");
        frame.setSize(700, 600);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton newTeam = new JButton("New Team");
        JButton editTeam = new JButton("Edit Team");
        JButton deleteTeam = new JButton("Delete Team");

        JPanel panel = new JPanel();
        panel.add(newTeam);
        panel.add(editTeam);
        panel.add(deleteTeam);
        frame.add(panel);

        // Create a hovering window (modal dialog)
        JDialog dialog1 = new JDialog(frame, "Dialog 1", true);
        dialog1.setSize(200, 150);
        dialog1.setLocationRelativeTo(frame);

        // Add some components to the dialog
        JPanel dialogPanel1 = new JPanel();
        JButton dialogButton1 = new JButton("OK");
        dialogPanel1.add(new JLabel("Dialog 1 content"));
        dialogPanel1.add(dialogButton1);
        dialog1.add(dialogPanel1);

        // Create another hovering window (non-modal dialog)
        JDialog dialog2 = new JDialog(frame, "Dialog 2", false);
        dialog2.setSize(200, 150);
        dialog2.setLocationRelativeTo(frame);

        // Add some components to the dialog
        JPanel dialogPanel2 = new JPanel();
        JButton dialogButton2 = new JButton("Cancel");
        dialogPanel2.add(new JLabel("Dialog 2 content"));
        dialogPanel2.add(dialogButton2);
        dialog2.add(dialogPanel2);

        // Add action listeners to the buttons in the main window
        newTeam.addActionListener(e -> dialog1.setVisible(true));
        editTeam.addActionListener(e -> dialog2.setVisible(true));
        deleteTeam.addActionListener(e -> System.out.println("Button 3 clicked"));

        // Show the main window
        frame.setVisible(true);
    }
}
