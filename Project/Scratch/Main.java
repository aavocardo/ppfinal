package Project.Scratch;

import Project.Logic.ButtonListener;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JInternalFrame;
import javax.swing.JDesktopPane;

public class Main extends JFrame {
    public Main() {
        super();
        setSize(600, 500);

        JDesktopPane mainFrame = new JDesktopPane();
        getContentPane().add(mainFrame);

        JInternalFrame teamFrame = new JInternalFrame("Add Team");
        teamFrame.setSize(300, 300);
        teamFrame.setLocation(50, 50);
        teamFrame.setVisible(true);
        mainFrame.add(teamFrame);

        String startButtonText = "End it";
        JButton startButton = new JButton(startButtonText);
        startButton.addActionListener(new ButtonListener(() ->
                System.out.println("Starter button pressed")
        ));
        mainFrame.add(startButton);

        JTextField teamID = new JTextField(20);


    }
    public static void main(String[] args) {
        Main t = new Main();
        t.setVisible(true);
    }
}
