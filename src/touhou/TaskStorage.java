package touhou;

import java.io.*;
import java.util.*;

public class TaskStorage {

    // Save a list of tasks to file
    public static void fileSave(ArrayList<Task> tasks) throws IOException {
        FileWriter writer = new FileWriter("tasks.txt");
        for (Task t : tasks) {
            writer.write(t.getName() + "|" + t.getDescription() + "|" + t.getCategory() + "\n");
        }
        writer.close();
    }

    // Read tasks from file and return them as a list
    public static ArrayList<Task> readFile(String fileName) throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(fileName);
        if (!file.exists()) return tasks;

        Scanner fileScanner = new Scanner(file);
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            String[] taskData = line.split("\\|");
            if (taskData.length == 3) {
                tasks.add(new Task(taskData[0], taskData[1], taskData[2]));
            }
        }
        fileScanner.close();
        return tasks;
    }
}
