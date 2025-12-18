package touhou;

import javax.swing.*;
import java.awt.*;

public class UI extends JPanel {
	
	private CardLayout cardLayout;
    private TaskManager manager;
    private DefaultListModel<String> taskListModel;
	private DefaultListModel<String> rewardListModel;
    private JList<String> taskList;
	private JList<String> rewardList;
    private Roulette roulette;

    public UI() {
        manager = new TaskManager();
        manager.loadTasks(); // Load tasks at startup
        roulette = new Roulette(manager);
        initComponents();
    }

    private void initComponents() {
		cardLayout = new CardLayout();
		setLayout(cardLayout);

		JPanel mainMenu = new ImagePanel("/touhou/Assets/MainMenuBG.png");
		mainMenu.setLayout(new BorderLayout());
		JPanel gambleMenu = new ImagePanel("/touhou/Assets/RouletteBG.png");
		gambleMenu.setLayout(new BorderLayout());
		JPanel office = new ImagePanel("/touhou/Assets/OfficeBG.png");
		office.setLayout(new GridBagLayout());
		
		JButton officeButton = new JButton("Office");
		JButton gambleButton = new JButton("Gamble");
		
		JPanel mainLeftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		mainLeftPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		mainLeftPanel.add(officeButton);

		JPanel mainRightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		mainRightPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		mainRightPanel.add(gambleButton);
		
		mainLeftPanel.setOpaque(false);
		mainRightPanel.setOpaque(false);
		mainMenu.add(mainLeftPanel, BorderLayout.WEST);
		mainMenu.add(mainRightPanel, BorderLayout.EAST);
		
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 5;
		gbc.weighty = 4;


		taskListModel = new DefaultListModel<>();
		rewardListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
		rewardList = new JList<>(rewardListModel);
		// List 1
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weighty = 2;
		gbc.weightx = 1;
		office.add(taskList, gbc);

		// List 2
		gbc.gridx = 1;
		office.add(rewardList, gbc);
		
		
		JButton historyButton = new JButton("Task History");
		// Task History Button
		gbc.weighty = 0;
		gbc.gridx = 2;
		office.add(historyButton, gbc);
		
		JButton returnButton = new JButton("Return");
		// Task History Button
		gbc.gridy = 1;
		office.add(returnButton, gbc);
		
		JButton addTaskButton = new JButton("Add Task");
        JButton renameTaskButton = new JButton("Rename Task");
		JButton removeTaskButton = new JButton("Remove Task");
		JButton doneButton = new JButton("Mark Done");
		
		JButton addRewardButton = new JButton("Add Reward");
        JButton renameRewardButton = new JButton("Rename Reward");
		JButton removeRewardButton = new JButton("Remove Reward");
		// add task button
		gbc.gridx = 0;
		gbc.gridy = 1;
		office.add(addTaskButton, gbc);
		
		// remove task button
		gbc.gridx = 1;
		office.add(removeTaskButton, gbc);

		// add reward button
		gbc.gridx = 2;
		office.add(addRewardButton, gbc);
		
		// remove reward button
		gbc.gridx = 3;
		office.add(removeRewardButton, gbc);
		
		// rename task button
		gbc.gridx = 0;
		gbc.gridy = 2;
		office.add(renameTaskButton, gbc);
        
		// mark done task button
		gbc.gridx = 1;
		office.add(doneButton, gbc);
		
		// rename reward button
		gbc.gridx = 2;
		office.add(renameRewardButton, gbc);
		
		
        JButton rouletteButton = new JButton("Roulette");
		JButton prayButton = new JButton("Pray");
		
		JPanel gambleLeftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		gambleLeftPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		gambleLeftPanel.add(rouletteButton);

		JPanel gambleRightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		gambleRightPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		gambleRightPanel.add(prayButton);
		
		mainLeftPanel.setOpaque(false);
		mainRightPanel.setOpaque(false);
		gambleMenu.add(gambleLeftPanel, BorderLayout.WEST);
		gambleMenu.add(gambleRightPanel, BorderLayout.EAST);
		

        // Button actions
        addTaskButton.addActionListener(e -> addTaskDialog());
        renameTaskButton.addActionListener(e -> renameTaskDialog());
        doneButton.addActionListener(e -> markDone());
        removeTaskButton.addActionListener(e -> removeTask());
        rouletteButton.addActionListener(e -> spinRoulette());
		returnButton.addActionListener(e ->
		cardLayout.show(this, "MAIN")
		);
		gambleButton.addActionListener(e ->
		cardLayout.show(this, "GAMBLE")
		);
		officeButton.addActionListener(e ->
			cardLayout.show(this, "OFFICE")
		);
		
        refreshTaskList();
		
		
		add(mainMenu, "MAIN");
		add(gambleMenu, "GAMBLE");
		add(office, "OFFICE");
		cardLayout.show(this, "MAIN");
    }

    private void refreshTaskList() {
        taskListModel.clear();
        for (Task t : manager.getTasks()) {
        String display = String.format("[%s] %s (%s)",
                t.getDone() ? "X" : " ",
                t.getName(),
                t.getCategory());
        taskListModel.addElement(display);
        }
    }

    private void addTaskDialog() {
        JTextField nameField = new JTextField();
        JComboBox<String> catField = new JComboBox<>();
		catField.addItem("Easy");
		catField.addItem("Medium");
		catField.addItem("Hard");

        Object[] message = {
                "Task name:", nameField,
                "Category:", catField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Add Task", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            manager.addTask(nameField.getText(), (String) catField.getSelectedItem());
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
                            t.getName() + " - " + " (" + t.getCategory() + ")");
        }
    }
}
