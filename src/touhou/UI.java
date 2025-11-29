package touhou;

import javax.swing.*;
import java.awt.*;

public class UI extends JPanel {

    private TaskManager manager;
    private DefaultListModel<String> taskListModel;
    private JList<String> taskList;
    private Roulette roulette;

    public UI() {
        manager = new TaskManager();
        manager.loadTasks(); // Load tasks at startup
        roulette = new Roulette(manager);
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        // Task list
        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        add(new JScrollPane(taskList), BorderLayout.CENTER);

        // Buttons panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 5, 5, 5)); // Add one more column for roulette

        JButton addButton = new JButton("Add");
        JButton renameButton = new JButton("Rename");
        JButton doneButton = new JButton("Mark Done");
        JButton removeButton = new JButton("Remove");
        JButton rouletteButton = new JButton("Roulette"); // New button

        buttonPanel.add(addButton);
        buttonPanel.add(renameButton);
        buttonPanel.add(doneButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(rouletteButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Button actions
        addButton.addActionListener(e -> addTaskDialog());
        renameButton.addActionListener(e -> renameTaskDialog());
        doneButton.addActionListener(e -> markDone());
        removeButton.addActionListener(e -> removeTask());
        rouletteButton.addActionListener(e -> spinRoulette());

        refreshTaskList();
    }

    private void refreshTaskList() {
        taskListModel.clear();
        for (Task t : manager.getTasks()) {
            String display = String.format("[%s] %s - %s (%s)",
                    t.getDone() ? "X" : " ",
                    t.getName(),
                    t.getDescription(),
                    t.getCategory());
            taskListModel.addElement(display);
        }
    }

    private void addTaskDialog() {
        JTextField nameField = new JTextField();
        JTextField descField = new JTextField();
        JTextField catField = new JTextField();

        Object[] message = {
                "Task name:", nameField,
                "Description:", descField,
                "Category:", catField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Add Task", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            manager.addTask(nameField.getText(), descField.getText(), catField.getText());
            refreshTaskList();
        }
    }

    private void renameTaskDialog() {
        int index = taskList.getSelectedIndex();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Select a task to rename");
            return;
        }

        String newName = JOptionPane.showInputDialog(this, "New name:");
        if (newName != null && !newName.isEmpty()) {
            manager.setTaskName(index, newName);
            refreshTaskList();
        }
    }

    private void markDone() {
        int index = taskList.getSelectedIndex();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Select a task to mark done");
            return;
        }

        manager.markTaskDone(index);
        refreshTaskList();
    }

    private void removeTask() {
        int index = taskList.getSelectedIndex();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Select a task to remove");
            return;
        }

        manager.removeTask(index);
        refreshTaskList();
    }

    private void spinRoulette() {
        Task t = roulette.spin();
        if (t == null) {
            JOptionPane.showMessageDialog(this, "No tasks available!");
        } else {
            JOptionPane.showMessageDialog(this,
                    "Roulette picked:\n" +
                            t.getName() + " - " + t.getDescription() + " (" + t.getCategory() + ")");
        }
    }
}
