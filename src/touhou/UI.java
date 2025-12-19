package touhou;

import javax.swing.*;
import java.awt.*;

public class UI extends JPanel {
	
	// ========================================
	// FIELDS
	// ========================================
	
	private FaithManager faithManager;
	private TaskManager taskManager;
	private RewardManager rewardManager;
	private Roulette roulette;
	
	private CardLayout cardLayout;
	private DefaultListModel<String> taskListModel;
	private DefaultListModel<String> rewardListModel;
	private DefaultListModel<String> historyListModel;
	private JList<String> taskList;
	private JList<String> rewardList;
	private JList<String> historyList;
	private JLabel faithCounter;
	
	// ========================================
	// CONSTRUCTOR
	// ========================================

	public UI() {
		faithManager = new FaithManager();
		taskManager = new TaskManager();
		rewardManager = new RewardManager();
		taskManager.loadTasks();
		rewardManager.loadRewards();
		roulette = new Roulette(taskManager, rewardManager);
		initComponents();
	}

	// ========================================
	// UI INITIALIZATION
	// ========================================

	private void initComponents() {
		cardLayout = new CardLayout();
		setLayout(cardLayout);
		
		JPanel mainMenu = createMainMenu();
		JPanel roulettePanel = createRoulettePanel();
		JPanel shrinePanel = createShrinePanel();
		JPanel office = createOfficePanel();
		JPanel historyPanel = createHistoryPanel();
		
		add(mainMenu, "MAIN");
		add(roulettePanel, "ROULETTE");
		add(shrinePanel, "SHRINE");
		add(office, "OFFICE");
		add(historyPanel, "HISTORY");
		
		cardLayout.show(this, "MAIN");
	}

	// ========================================
	// PANEL CREATION METHODS
	// ========================================

	private JPanel createMainMenu() {
		JPanel mainMenu = new ImagePanel("/touhou/Assets/MainMenuBG.png");
		mainMenu.setLayout(new BorderLayout());
		
		// Left panel with Reimu character
		JPanel mainLeftPanel = new JPanel(new BorderLayout());
		mainLeftPanel.setOpaque(false);
		
		ImageIcon reimu = new ImageIcon(getClass().getResource("Assets/reimuintro.png"));
		JLabel reimuicon = new JLabel(reimu);
		reimuicon.setHorizontalAlignment(SwingConstants.LEFT);
		reimuicon.setVerticalAlignment(SwingConstants.BOTTOM);
		mainLeftPanel.add(reimuicon, BorderLayout.SOUTH);
		
		// Right panel with buttons and faith counter
		JPanel mainRightPanel = new JPanel(new GridLayout(0, 1, 15, 15));
		mainRightPanel.setOpaque(false);
		mainRightPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
		
		// Faith counter in bubble
		faithCounter = new JLabel("Faith: 0", SwingConstants.CENTER);
		faithCounter.setFont(new Font("Lucida Sans", Font.PLAIN, 18));
		faithCounter.setForeground(Color.BLACK);
		faithCounter.setOpaque(false);
		faithCounter.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));
		
		BubblePanel faithBubble = new BubblePanel("Assets/DialogueBG.png");
		faithBubble.setPreferredSize(new Dimension(220, 80));
		faithBubble.add(faithCounter);
		
		mainRightPanel.add(faithBubble);
		
		// Menu buttons
		JButton officeButton = new JButton("Office");
		JButton rouletteButton = new JButton("Roulette");
		JButton shrineButton = new JButton("Shrine");
		
		mainRightPanel.add(officeButton);
		mainRightPanel.add(rouletteButton);
		mainRightPanel.add(shrineButton);
		
		// Add panels to main menu
		mainMenu.add(mainLeftPanel, BorderLayout.WEST);
		mainMenu.add(mainRightPanel, BorderLayout.EAST);
		
		// Button actions
		officeButton.addActionListener(e -> cardLayout.show(this, "OFFICE"));
		rouletteButton.addActionListener(e -> cardLayout.show(this, "ROULETTE"));
		shrineButton.addActionListener(e -> cardLayout.show(this, "SHRINE"));
		
		return mainMenu;
	}

	private JPanel createRoulettePanel() {
		JPanel roulettePanel = new ImagePanel("/touhou/Assets/RouletteBG.png");
		roulettePanel.setLayout(new BorderLayout());
		
		// Left panel with Marisa character
		JPanel leftPanel = new JPanel(new BorderLayout());
		leftPanel.setOpaque(false);
		
		ImageIcon marisa = new ImageIcon(getClass().getResource("Assets/marisa.png"));
		JLabel marisaIcon = new JLabel(marisa);
		marisaIcon.setHorizontalAlignment(SwingConstants.LEFT);
		marisaIcon.setVerticalAlignment(SwingConstants.BOTTOM);
		leftPanel.add(marisaIcon, BorderLayout.SOUTH);
		
		// Center panel with wheel image and button
		JPanel centerPanel = new JPanel(new GridBagLayout());
		centerPanel.setOpaque(false);
		GridBagConstraints gbc = new GridBagConstraints();
		
		// Wheel image (resized to be smaller)
		ImageIcon wheelIcon = new ImageIcon(getClass().getResource("Assets/wheel.png"));
		Image scaledWheel = wheelIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
		wheelIcon = new ImageIcon(scaledWheel);
		JLabel wheelLabel = new JLabel(wheelIcon);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(20, 0, 20, 0);
		centerPanel.add(wheelLabel, gbc);
		
		// Spin button below wheel
		JButton spinButton = new BubbleButton("Spin");
		spinButton.addActionListener(e -> spinRoulette());
		gbc.gridy = 1;
		gbc.insets = new Insets(10, 0, 20, 0);
		centerPanel.add(spinButton, gbc);
		
		// Return button panel at top
		JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 15));
		topPanel.setOpaque(false);
		JButton returnButton = new BubbleButton("Return");
		returnButton.addActionListener(e -> cardLayout.show(this, "MAIN"));
		topPanel.add(returnButton);
		
		roulettePanel.add(topPanel, BorderLayout.NORTH);
		roulettePanel.add(leftPanel, BorderLayout.WEST);
		roulettePanel.add(centerPanel, BorderLayout.CENTER);
		
		return roulettePanel;
	}

	private JPanel createShrinePanel() {
		JPanel shrinePanel = new ImagePanel("/touhou/Assets/ShrineBG.png");
		shrinePanel.setLayout(new BorderLayout());
		
		// Center panel with pray button
		JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
		centerPanel.setOpaque(false);
		
		JButton prayButton = new BubbleButton("Pray");
		prayButton.addActionListener(e -> handlePray());
		centerPanel.add(prayButton);
		
		// Return button panel at top
		JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 15));
		topPanel.setOpaque(false);
		JButton returnButton = new BubbleButton("Return");
		returnButton.addActionListener(e -> cardLayout.show(this, "MAIN"));
		topPanel.add(returnButton);
		
		shrinePanel.add(topPanel, BorderLayout.NORTH);
		shrinePanel.add(centerPanel, BorderLayout.CENTER);
		
		return shrinePanel;
	}

	private JPanel createOfficePanel() {
		JPanel office = new ImagePanel("/touhou/Assets/OfficeBG.png");
		office.setLayout(new GridLayout(1, 2, 20, 0));
		
		JPanel leftPanel = createOfficeLeftPanel();
		JPanel rightPanel = createOfficeRightPanel();
		
		office.add(leftPanel);
		office.add(rightPanel);
		
		return office;
	}

	private JPanel createOfficeLeftPanel() {
		JPanel leftPanel = new JPanel(new GridBagLayout());
		leftPanel.setOpaque(false);
		leftPanel.setPreferredSize(new Dimension(600, 0));
		leftPanel.setMinimumSize(new Dimension(600, 0));
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.BOTH;
		
		// Return button
		JButton returnButton = new BubbleButton("Return");
		returnButton.addActionListener(e -> cardLayout.show(this, "MAIN"));
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.WEST;
		leftPanel.add(returnButton, gbc);
		
		// Task list
		taskListModel = new DefaultListModel<>();
		taskList = new JList<>(taskListModel);
		JScrollPane taskScroll = new JScrollPane(taskList);
		taskScroll.setPreferredSize(new Dimension(250, 400));
		
		JPanel taskListPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
		taskListPanel.setOpaque(false);
		taskListPanel.add(taskScroll);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.weightx = 1;
		gbc.weighty = 1;
		leftPanel.add(taskListPanel, gbc);
		
		// Reward list
		rewardListModel = new DefaultListModel<>();
		rewardList = new JList<>(rewardListModel);
		JScrollPane rewardScroll = new JScrollPane(rewardList);
		rewardScroll.setPreferredSize(new Dimension(250, 400));
		
		JPanel rewardListPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
		rewardListPanel.setOpaque(false);
		rewardListPanel.add(rewardScroll);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		gbc.weightx = 1;
		gbc.weighty = 1;
		leftPanel.add(rewardListPanel, gbc);
		
		refreshTaskList();
		refreshRewardList();
		
		return leftPanel;
	}

	private JPanel createOfficeRightPanel() {
		JPanel rightPanel = new JPanel(new GridLayout(0, 2, 10, 10));
		rightPanel.setOpaque(false);
		rightPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
		// Create all buttons
		JButton addTaskButton = new BubbleButton("Add Task");
		JButton removeTaskButton = new BubbleButton("Remove Task");
		JButton renameTaskButton = new BubbleButton("Rename Task");
		JButton doneButton = new BubbleButton("Mark Done");
		JButton addRewardButton = new BubbleButton("Add Reward");
		JButton removeRewardButton = new BubbleButton("Remove Reward");
		JButton renameRewardButton = new BubbleButton("Rename Reward");
		JButton historyButton = new BubbleButton("Task History");
		
		// Add action listeners
		addTaskButton.addActionListener(e -> addTaskDialog());
		removeTaskButton.addActionListener(e -> removeTask());
		renameTaskButton.addActionListener(e -> renameTaskDialog());
		doneButton.addActionListener(e -> markDone());
		addRewardButton.addActionListener(e -> addRewardDialog());
		removeRewardButton.addActionListener(e -> removeReward());
		renameRewardButton.addActionListener(e -> renameRewardDialog());
		historyButton.addActionListener(e -> {
			refreshHistoryList();
			cardLayout.show(this, "HISTORY");
		});
		
		// Add buttons to panel
		rightPanel.add(addTaskButton);
		rightPanel.add(removeTaskButton);
		rightPanel.add(renameTaskButton);
		rightPanel.add(doneButton);
		rightPanel.add(addRewardButton);
		rightPanel.add(removeRewardButton);
		rightPanel.add(renameRewardButton);
		rightPanel.add(historyButton);
		
		return rightPanel;
	}

	private JPanel createHistoryPanel() {
		JPanel historyPanel = new ImagePanel("/touhou/Assets/OfficeBG.png");
		historyPanel.setLayout(new BorderLayout());
		
		// Top panel with return button
		JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 15));
		topPanel.setOpaque(false);
		JButton returnButton = new BubbleButton("Return");
		returnButton.addActionListener(e -> cardLayout.show(this, "OFFICE"));
		topPanel.add(returnButton);
		
		// Center panel with history list
		historyListModel = new DefaultListModel<>();
		historyList = new JList<>(historyListModel);
		JScrollPane historyScroll = new JScrollPane(historyList);
		historyScroll.setPreferredSize(new Dimension(600, 500));
		
		JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
		centerPanel.setOpaque(false);
		centerPanel.add(historyScroll);
		
		historyPanel.add(topPanel, BorderLayout.NORTH);
		historyPanel.add(centerPanel, BorderLayout.CENTER);
		
		return historyPanel;
	}

	// ========================================
	// REFRESH METHODS
	// ========================================

	private void refreshTaskList() {
		taskListModel.clear();
		for (Task t : taskManager.getTasks()) {
			int baseFaith = getBaseFaith(t.getCategory());
			int faithValue = t.isRouletteBoosted() ? baseFaith * 3 : baseFaith;
			String boostIndicator = t.isRouletteBoosted() ? "★ " : "";
			
			String display = String.format("%s[%s] %s (%s) - %d faith",
				boostIndicator,
				t.getDone() ? "X" : " ",
				t.getName(),
				t.getCategory(),
				faithValue);
			taskListModel.addElement(display);
		}
	}

	private void refreshRewardList() {
		rewardListModel.clear();
		for (Reward r : rewardManager.getRewards()) {
			rewardListModel.addElement(r.getName());
		}
	}
	
	private void refreshFaithCounter() {
		int faithNum = faithManager.getFaith();
		faithCounter.setText("Faith: " + faithNum);
	}

	private void refreshHistoryList() {
		historyListModel.clear();
		try {
			java.util.ArrayList<String> history = TaskStorage.loadHistory();
			for (String entry : history) {
				historyListModel.addElement(entry);
			}
		} catch (java.io.IOException e) {
			System.out.println("Failed to load history: " + e.getMessage());
		}
	}

	// ========================================
	// DIALOG METHODS
	// ========================================

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
		Object[] message = {"Reward name:", nameField};

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

	// ========================================
	// TASK ACTIONS
	// ========================================

	private void markDone() {
		int index = taskList.getSelectedIndex();
		if (index == -1) {
			JOptionPane.showMessageDialog(this, "Select a task to mark done");
			return;
		}

		Task task = taskManager.getTasks().get(index);
		
		if (task.getDone()) {
			JOptionPane.showMessageDialog(this, "Task is already completed!");
			return;
		}
		
		// Calculate faith earned
		int faithEarned = getBaseFaith(task.getCategory());
		boolean wasBoosted = task.isRouletteBoosted();
		if (wasBoosted) {
			faithEarned *= 3;
		}
		
		// Add faith to faith manager
		faithManager.addFaith(faithEarned);
		
		// Create history entry
		String historyEntry = String.format("[%s] %s (%s) - %d faith earned%s",
			new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()),
			task.getName(),
			task.getCategory(),
			faithEarned,
			wasBoosted ? " ★ BOOSTED" : "");
		
		// Save to history
		try {
			TaskStorage.saveHistory(historyEntry);
		} catch (java.io.IOException e) {
			System.out.println("Failed to save history: " + e.getMessage());
		}
		
		// Remove task from list
		taskManager.removeTask(index);
		
		// Update displays
		refreshTaskList();
		refreshFaithCounter();
		
		// Show message with faith earned
		String boostMessage = wasBoosted ? "\n(300% Roulette Boost Applied!)" : "";
		
		JOptionPane.showMessageDialog(this, 
			"Task completed!\n" +
			"Task: " + task.getName() + " (" + task.getCategory() + ")\n" +
			"Faith earned: " + faithEarned + boostMessage + "\n" +
			"Total faith: " + faithManager.getFaith());
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

	// ========================================
	// ROULETTE
	// ========================================

	private void spinRoulette() {
		Object result = roulette.spin();
		
		if (result == null) {
			JOptionPane.showMessageDialog(this, "No tasks available!");
			return;
		}
		
		if (result instanceof Reward) {
			showRewardWon((Reward) result);
		} else if (result instanceof Task) {
			showTaskSelected((Task) result);
		}
	}

	private void showRewardWon(Reward reward) {
		ImageIcon giftIcon = new ImageIcon(getClass().getResource("/touhou/Assets/gift.png"));
		Image scaledImage = giftIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		giftIcon = new ImageIcon(scaledImage);
		
		JPanel panel = new JPanel(new BorderLayout());
		JLabel imageLabel = new JLabel(giftIcon, SwingConstants.CENTER);
		JLabel textLabel = new JLabel(
			"<html><center>🎉 LUCKY SPIN! 🎉<br><br>" +
			"Roulette landed on a <b>REWARD</b>!<br><br>" +
			"You won: <font color='green'>" + reward.getName() + "</font><br><br>" +
			"(20% chance to land on rewards)</center></html>",
			SwingConstants.CENTER
		);
		
		panel.add(imageLabel, BorderLayout.CENTER);
		panel.add(textLabel, BorderLayout.SOUTH);
		
		JOptionPane.showMessageDialog(this, panel, "Reward Won!", JOptionPane.INFORMATION_MESSAGE);
	}

	private void showTaskSelected(Task task) {
		int baseFaith = getBaseFaith(task.getCategory());
		int boostedFaith = baseFaith * 3;
		
		String message = "Roulette picked a TASK:\n\n" +
						task.getName() + " (" + task.getCategory() + ")\n\n" +
						"Normal faith: " + baseFaith + "\n" +
						"★ Boosted faith: " + boostedFaith + " ★\n\n";
		
		JOptionPane.showMessageDialog(this, message);
		refreshTaskList();
	}

	// ========================================
	// PRAY FUNCTIONALITY
	// ========================================

	private void handlePray() {
		Shrine shrine = new Shrine(rewardManager);
		int currentFaith = faithManager.getFaith();
		int pullCost = shrine.getPullCost();
		
		// Show pray dialog
		int option = JOptionPane.showConfirmDialog(this,
			"Pray at the shrine?\n" +
			"Cost: " + pullCost + " faith\n" +
			"Your faith: " + currentFaith + "\n\n" +
			"20% chance to win a reward!",
			"Pray",
			JOptionPane.YES_NO_OPTION);
		
		if (option == JOptionPane.YES_OPTION) {
			if (currentFaith < pullCost) {
				JOptionPane.showMessageDialog(this, "Not enough faith!");
				return;
			}
			
			// Deduct faith
			faithManager.spendFaith(pullCost);
			
			// Perform the pull
			Shrine.PullResult result = shrine.pull(currentFaith);
			
			// Update faith counter
			refreshFaithCounter();
			
			// Show result
			if (result.success && result.reward != null) {
				JOptionPane.showMessageDialog(this,
					"🎊 Blessing received! 🎊\n\n" +
					"You received: " + result.reward.getName() + "\n\n" +
					result.message,
					"Prayer Answered!",
					JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(this, result.message);
			}
		}
	}
	// ========================================
	// HELPER METHODS
	// ========================================

	private int getBaseFaith(String category) {
		switch (category) {
			case "Medium": return 25;
			case "Hard": return 50;
			default: return 10;
		}
	}
}