package touhou;

import java.io.*;
import java.util.*;

public class TaskStorage {

    public static void saveTask(ArrayList<Task> tasks) throws IOException {
        FileWriter writer = new FileWriter("tasks.txt");
        for (Task t : tasks) {
            // REMOVED: description field from save
            // BEFORE: writer.write(t.getName() + "|" + t.getDescription() + "|" + t.getCategory() + "\n");
            // AFTER: Only name and category
            writer.write(t.getName() + "|" + t.getCategory() + "\n");
        }
        writer.close();
    }

    public static void saveReward(ArrayList<Reward> rewards) throws IOException {
        FileWriter writer = new FileWriter("rewards.txt");
        for (Reward r : rewards) {
            // REMOVED: description and category fields from save
            // BEFORE: writer.write(r.getName() + "|" + r.getDescription() + "|" + r.getCategory() + "\n");
            // AFTER: Only name
            writer.write(r.getName() + "\n");
        }
        writer.close();
    }

    // Read tasks from file and return them as a list
    public static ArrayList<Task> readTask(String fileName) throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(fileName);
        if (!file.exists()) return tasks;

        Scanner fileScanner = new Scanner(file);
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            String[] taskData = line.split("\\|");
            // CHANGED: from 3 fields to 2 fields (removed description)
            // BEFORE: if (taskData.length == 3) { tasks.add(new Task(taskData[0], taskData[1], taskData[2])); }
            // AFTER: Only name and category
            if (taskData.length == 2) {
                tasks.add(new Task(taskData[0], taskData[1]));
            }
        }
        fileScanner.close();
        return tasks;
    }
    
    public static ArrayList<Reward> readReward(String fileName) throws IOException {
        ArrayList<Reward> rewards = new ArrayList<>();
        File file = new File(fileName);
        if (!file.exists()) return rewards;

        Scanner fileScanner = new Scanner(file);
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine().trim();
            if (!line.isEmpty()) {
                // REMOVED: description and category parsing
                // BEFORE: String[] rewardData = line.split("\\|"); if (rewardData.length == 3) { rewards.add(new Reward(rewardData[0], rewardData[1], rewardData[2])); }
                // AFTER: Only name
                rewards.add(new Reward(line));
            }
        }
        fileScanner.close();
        return rewards;
    }
}