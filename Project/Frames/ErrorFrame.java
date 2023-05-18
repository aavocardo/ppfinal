package Project.Frames;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ErrorFrame extends JFrame {
    JFrame frame;
    public ErrorFrame() {
        frame = new JFrame();

        JOptionPane.showMessageDialog(frame, "No players in team, cannot open.", "Error", JOptionPane.ERROR_MESSAGE);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
    }
}