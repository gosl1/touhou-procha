package touhou;

import java.io.*;
import java.util.*;

public class Storage {

    public static void saveTask(ArrayList<Task> tasks) throws IOException {
        FileWriter writer = new FileWriter("tasks.txt");
        for (Task t : tasks) {
            writer.write(t.getName() + "|" + t.getCategory() + "\n");
        }
        writer.close();
    }

    public static void saveReward(ArrayList<Reward> rewards) throws IOException {
        FileWriter writer = new FileWriter("rewards.txt");
        for (Reward r : rewards) {
            writer.write(r.getName() + "\n");
        }
        writer.close();
    }

    public static ArrayList<Task> readTask(String fileName) throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(fileName);
        if (!file.exists()) return tasks;

        Scanner fileScanner = new Scanner(file);
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            String[] taskData = line.split("\\|");
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
                rewards.add(new Reward(line));
            }
        }
        fileScanner.close();
        return rewards;
    }

    public static void saveFaith(int faith) throws IOException {
        FileWriter writer = new FileWriter("faith.txt");
        writer.write(String.valueOf(faith));
        writer.close();
    }

    public static int loadFaith() throws IOException {
        File file = new File("faith.txt");
        if (!file.exists()) return 0;
        
        Scanner scanner = new Scanner(file);
        if (scanner.hasNextInt()) {
            int faith = scanner.nextInt();
            scanner.close();
            return faith;
        }
        scanner.close();
        return 0;
    }

    // Task history methods
    public static void saveHistory(String historyEntry) throws IOException {
        FileWriter writer = new FileWriter("history.txt", true); // append mode
        writer.write(historyEntry + "\n");
        writer.close();
    }

    public static ArrayList<String> loadHistory() throws IOException {
        ArrayList<String> history = new ArrayList<>();
        File file = new File("history.txt");
        if (!file.exists()) return history;

        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (!line.isEmpty()) {
                history.add(line);
            }
        }
        scanner.close();
        return history;
    }
}