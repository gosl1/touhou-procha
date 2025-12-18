package touhou;

import javax.swing.*;
import java.awt.*;

public class UI extends JPanel {
	
	private CardLayout cardLayout;
    private TaskManager taskManager;
	private RewardManager rewardManager;
    private DefaultListModel<String> taskListModel;
	private DefaultListModel<String> rewardListModel;
    private JList<String> taskList;
	private JList<String> rewardList;
    private Roulette roulette;

    public UI() {
        taskManager = new TaskManager();
		rewardManager = new RewardManager();
        taskManager.loadTasks(); // Load tasks at startup
		rewardManager.loadRewards();
        roulette = new Roulette(taskManager);
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
		office.setLayout(new GridLayout(1, 2, 20, 0));
		
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
		
		
		
		JPanel leftPanel = new JPanel(new GridBagLayout());
		leftPanel.setOpaque(false);
		leftPanel.setPreferredSize(new Dimension(600, 0));
		leftPanel.setMinimumSize(new Dimension(600, 0));

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.BOTH;


		taskListModel = new DefaultListModel<>();
		rewardListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
		rewardList = new JList<>(rewardListModel);
		JScrollPane taskScroll = new JScrollPane(taskList);
		JScrollPane rewardScroll = new JScrollPane(rewardList);
		
		JButton returnButton = new JButton("Return");
		// return to main menu Button
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.WEST;

		leftPanel.add(returnButton, gbc);
		
		JPanel taskListPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
		taskListPanel.setOpaque(false);

		JPanel rewardListPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
		rewardListPanel.setOpaque(false);
		
		taskScroll.setPreferredSize(new Dimension(250, 400));
		rewardScroll.setPreferredSize(new Dimension(250, 400));

		taskListPanel.add(taskScroll);
		rewardListPanel.add(rewardScroll);
		
		
		// Row 1: lists
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.weightx = 1;
		gbc.weighty = 1;
		leftPanel.add(taskListPanel, gbc);

		// Reward list
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		gbc.weightx = 1;
		gbc.weighty = 1;
		leftPanel.add(rewardListPanel, gbc);

		// Reward list column
		gbc.gridx = 1;
		leftPanel.add(rewardListPanel, gbc);
		
		JPanel rightPanel = new JPanel(new GridLayout(0, 2, 10, 10));
		rightPanel.setOpaque(false);
		rightPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
		JButton addTaskButton = new JButton("Add Task");
		JButton removeTaskButton = new JButton("Remove Task");
		JButton renameTaskButton = new JButton("Rename Task");
		JButton doneButton = new JButton("Mark Done");

		JButton addRewardButton = new JButton("Add Reward");
		JButton removeRewardButton = new JButton("Remove Reward");
		JButton renameRewardButton = new JButton("Rename Reward");

		JButton historyButton = new JButton("Task History");

		rightPanel.add(addTaskButton);
		rightPanel.add(removeTaskButton);
		rightPanel.add(renameTaskButton);
		rightPanel.add(doneButton);
		rightPanel.add(addRewardButton);
		rightPanel.add(removeRewardButton);
		rightPanel.add(renameRewardButton);
		rightPanel.add(historyButton);
		
		office.add(leftPanel);
		office.add(rightPanel);
		
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
		addRewardButton.addActionListener(e -> addRewardDialog());
		removeRewardButton.addActionListener(e -> removeReward());
		renameRewardButton.addActionListener(e -> renameRewardDialog());
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
		refreshRewardList();
		
		add(mainMenu, "MAIN");
		add(gambleMenu, "GAMBLE");
		add(office, "OFFICE");
		cardLayout.show(this, "MAIN");
    }

    private void refreshTaskList() {
        taskListModel.clear();
        for (Task t : taskManager.getTasks()) {
        String display = String.format("[%s] %s (%s)",
                t.getDone() ? "X" : " ",
                t.getName(),
                t.getCategory());
        taskListModel.addElement(display);
        }
    }

	private void refreshRewardList() {
        rewardListModel.clear();
        for (Reward r : rewardManager.getRewards()) {
        String display = String.format("%s",
                r.getName());
        rewardListModel.addElement(display);
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
            taskManager.addTask(nameField.getText(), (String) catField.getSelectedItem());
            refreshTaskList();
        }
    }
	
	private void addRewardDialog() {
        JTextField nameField = new JTextField();

        Object[] message = {
                "Reward name:", nameField,
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Add Reward", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            rewardManager.addReward(nameField.getText());
            refreshRewardList();
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
            taskManager.setTaskName(index, newName);
            refreshTaskList();
        }
    }
	
	private void renameRewardDialog() {
        int index = rewardList.getSelectedIndex();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Select a reward to rename");
            return;
        }

        String newName = JOptionPane.showInputDialog(this, "New name:");
        if (newName != null && !newName.isEmpty()) {
            rewardManager.setRewardName(index, newName);
            refreshRewardList();
        }
    }

    private void markDone() {
        int index = taskList.getSelectedIndex();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Select a task to mark done");
            return;
        }

        taskManager.markTaskDone(index);
        refreshTaskList();
    }

    private void removeTask() {
        int index = taskList.getSelectedIndex();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Select a task to remove");
            return;
        }

        taskManager.removeTask(index);
        refreshTaskList();
    }
	
	private void removeReward() {
        int index = rewardList.getSelectedIndex();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Select a reward to remove");
            return;
        }

        rewardManager.removeReward(index);
        refreshRewardList();
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
