package touhou;

import javax.swing.*;
import java.awt.*;

public class UI extends JPanel {
	
	private FaithManager faithManager;
	private CardLayout cardLayout;
    private TaskManager taskManager;
	private RewardManager rewardManager;
    private DefaultListModel<String> taskListModel;
	private DefaultListModel<String> rewardListModel;
    private JList<String> taskList;
	private JList<String> rewardList;
    private Roulette roulette;
	private JLabel faithCounter;
	

    public UI() {
		faithManager = new FaithManager();
        taskManager = new TaskManager();
		rewardManager = new RewardManager();
        taskManager.loadTasks(); // Load tasks at startup
		rewardManager.loadRewards();
        roulette = new Roulette(taskManager, rewardManager);
        initComponents();
    }

    private void initComponents() {
		cardLayout = new CardLayout();
		setLayout(cardLayout);
		JPanel mainMenu = new ImagePanel("/touhou/Assets/MainMenuBG.png");
		mainMenu.setLayout(new BorderLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.insets = new Insets(10, 10, 10, 10); 
		gbc.fill = GridBagConstraints.BOTH;
		
		JPanel gambleMenu = new ImagePanel("/touhou/Assets/RouletteBG.png");
		gambleMenu.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));
		
		
		JPanel office = new ImagePanel("/touhou/Assets/OfficeBG.png");
		office.setLayout(new GridLayout(1, 2, 20, 0));
		
		JButton officeButton = new JButton("Office");
		JButton gambleButton = new JButton("Gamble");

		JPanel leftPanel = new JPanel(new GridBagLayout());
		leftPanel.setOpaque(false);
		leftPanel.setPreferredSize(new Dimension(600, 0));
		leftPanel.setMinimumSize(new Dimension(600, 0));

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
		gambleMenu.add(rouletteButton);
		JButton prayButton = new JButton("Pray");
		
		JPanel mainLeftPanel = new JPanel(new BorderLayout());
		mainLeftPanel.setOpaque(false);
		
		ImageIcon reimu = new ImageIcon(getClass().getResource("Assets/reimuintro.png"));
		JLabel reimuicon = new JLabel(reimu);
		reimuicon.setHorizontalAlignment(SwingConstants.LEFT);
		reimuicon.setVerticalAlignment(SwingConstants.BOTTOM);
		
		mainLeftPanel.add(reimuicon, BorderLayout.SOUTH);
		
		JPanel mainRightPanel = new JPanel(new GridLayout(0, 1, 15, 15));
		mainRightPanel.setOpaque(false);
		mainRightPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
		
		faithCounter = new JLabel("Faith: 0", SwingConstants.CENTER);
		faithCounter.setFont(new Font("Impact", Font.PLAIN, 18));
		faithCounter.setForeground(Color.BLACK);
		faithCounter.setOpaque(false);
		
		BubblePanel faithBubble = new BubblePanel("Assets/DialogueBG.png");
		faithBubble.setPreferredSize(new Dimension(220, 80)); // tweak to taste

		// Padding so text doesn't touch edges
		faithCounter.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));

		faithBubble.add(faithCounter);
		
		mainRightPanel.add(faithBubble);
		
		mainRightPanel.add(officeButton);
		mainRightPanel.add(gambleButton);
		mainRightPanel.add(prayButton);

		mainMenu.add(mainLeftPanel, BorderLayout.WEST);
		mainMenu.add(mainRightPanel, BorderLayout.EAST);
		
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
			// Calculate faith value for display
			int baseFaith = 10;
			if (t.getCategory().equals("Medium")) baseFaith = 25;
			else if (t.getCategory().equals("Hard")) baseFaith = 50;
			
			int faithValue = t.isRouletteBoosted() ? baseFaith * 3 : baseFaith;
			
			// Add star for boosted tasks
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
        String display = String.format("%s",
                r.getName());
        rewardListModel.addElement(display);
        }
    }
	
	private void refreshFaithCounter(){
		int faithNum = faithManager.getFaith();
		faithCounter.setText("Faith: " + String.valueOf(faithNum));
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

		// Get the task
		Task task = taskManager.getTasks().get(index);
		
		// Check if already done
		if (task.getDone()) {
			JOptionPane.showMessageDialog(this, "Task is already completed!");
			return;
		}
		
		// Calculate faith earned
		int faithEarned = 0;
		String category = task.getCategory();
		
		// Base faith values
		if (category.equals("Easy")) {
			faithEarned = 10;
		} else if (category.equals("Medium")) {
			faithEarned = 25;
		} else if (category.equals("Hard")) {
			faithEarned = 50;
		}
		
		// Apply 300% boost if roulette boosted
		if (task.isRouletteBoosted()) {
			faithEarned *= 3; // 300% increase
		}
		
		// Mark task as done
		taskManager.markTaskDone(index);
		
		// Add faith to faith manager
		faithManager.addFaith(faithEarned);
		
		// Reset roulette boost
		task.setRouletteBoosted(false);
		
		// Update displays
		refreshTaskList();
		refreshFaithCounter();
		
		// Show message with faith earned
		String boostMessage = task.isRouletteBoosted() ? 
			"\n(300% Roulette Boost Applied!)" : "";
		
		JOptionPane.showMessageDialog(this, 
			"Task completed!\n" +
			"Task: " + task.getName() + " (" + category + ")\n" +
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

    private void spinRoulette() {
		Object result = roulette.spin();
		
		if (result == null) {
			JOptionPane.showMessageDialog(this, "No tasks available!");
			return;
		}
		
		if (result instanceof Reward) {
			// Landed on a reward (20% chance)
			Reward reward = (Reward) result;
			
			// Load the gift icon image
			ImageIcon giftIcon = new ImageIcon(getClass().getResource("/touhou/Assets/gift.png"));
			
			// Resize if needed (optional)
			Image scaledImage = giftIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
			giftIcon = new ImageIcon(scaledImage);
			
			// Create a panel with the image and text
			JPanel panel = new JPanel();
			panel.setLayout(new BorderLayout());
			
			JLabel imageLabel = new JLabel(giftIcon);
			imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
			
			JLabel textLabel = new JLabel(
				"<html><center>🎉 LUCKY SPIN! 🎉<br><br>" +
				"Roulette landed on a <b>REWARD</b>!<br><br>" +
				"You won: <font color='green'>" + reward.getName() + "</font><br><br>" +
				"(20% chance to land on rewards)</center></html>"
			);
			textLabel.setHorizontalAlignment(SwingConstants.CENTER);
			
			panel.add(imageLabel, BorderLayout.CENTER);
			panel.add(textLabel, BorderLayout.SOUTH);
			
			JOptionPane.showMessageDialog(this, panel, "Reward Won!", JOptionPane.INFORMATION_MESSAGE);
		} 
		else if (result instanceof Task) {
			// Landed on a task (80% chance)
			Task t = (Task) result;
			
			// Calculate faith value for display
			int baseFaith = 10;
			if (t.getCategory().equals("Medium")) baseFaith = 25;
			else if (t.getCategory().equals("Hard")) baseFaith = 50;
			
			int boostedFaith = baseFaith * 3;  // 300% boost
			
			String message = "Roulette picked a TASK:\n\n" +
							t.getName() + " (" + t.getCategory() + ")\n\n" +
							"Normal faith: " + baseFaith + "\n" +
							"★ Boosted faith: " + boostedFaith + " ★\n\n";
			
			JOptionPane.showMessageDialog(this, message);
			refreshTaskList();  // Update to show ★
		}
	}
}

