package Project.Scratch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Example extends JPanel {

    public Example() {
        setLayout(new GridLayout(5, 1)); // Set layout to display 5 elements vertically
        for (int i = 1; i <= 5; i++) {
            JLabel label = new JLabel("Element " + i);
            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    System.out.println(label.getText()); // Print the name of the clicked element
                }
            });
            add(label);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Clickable Elements");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new Example());
        frame.pack();
        frame.setVisible(true);
    }
}