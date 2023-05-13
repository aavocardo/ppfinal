package Project.Logic;

import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class FileManager {
    String path;

    public FileManager(String path) {
        this.path = path;
    }

    public void createFile() {
        try {
            File file = new File(this.path);
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            }   else {
                System.out.println("File exists.");
            }

        }   catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void writeToFile(String text) {
        try {
            FileWriter writer = new FileWriter(this.path, true);
            writer.write(text);
            writer.close();

            System.out.println("Successfully written");

        }   catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void readFile() {
        try {
            File file = new File(this.path);
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                System.out.println(data);
            }

            reader.close();

        }   catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }
    }

    public String readFileExperiment() {
        StringBuilder stringbuilder = new StringBuilder();
        try {
            File file = new File(this.path);
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                stringbuilder.append(data);
                stringbuilder.append("\n");
            }

            reader.close();
            String data = stringbuilder.toString();
            System.out.println(data);
            return data;

        }   catch (FileNotFoundException exception) {
            exception.printStackTrace();
            return "";
        }
    }

}
