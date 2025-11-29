package touhou;

import java.io.IOException;
import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> tasks;

    public TaskManager() {
        tasks = new ArrayList<>();
    }

    public void loadTasks() {
        try {
            tasks = TaskStorage.readFile("tasks.txt");
        } catch (IOException e) {
            System.out.println("Failed to load tasks: " + e.getMessage());
        }
    }

    public void saveTasks() {
        try {
            TaskStorage.fileSave(tasks);
        } catch (IOException e) {
            System.out.println("Failed to save tasks: " + e.getMessage());
        }
    }

    public void addTask(String title, String description, String category) {
        tasks.add(new Task(title, description, category));
        saveTasks(); // auto-save after adding
    }

    public void removeTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
            saveTasks(); // auto-save after removing
        }
    }

    public void markTaskDone(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).markDone();
            saveTasks(); // auto-save after marking done
        }
    }

    public void setTaskName(int index, String newName) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).setName(newName);
            saveTasks(); // auto-save after renaming
        }
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
