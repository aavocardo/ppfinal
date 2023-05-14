package Project;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Scratch extends JFrame {
    public Scratch() {
        setTitle("Example JTable");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        String[] columnNames = {"Name", "Age", "Gender"};
        Object[][] data = {
                {"John Smith", 30, "Male"},
                {"Jane Doe", 25, "Female"},
                {"Bob Johnson", 40, "Male"},
                {"Sally Jones", 35, "Female"}
        };

        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Scratch();
    }
}