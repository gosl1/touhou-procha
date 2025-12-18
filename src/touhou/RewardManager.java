package touhou;

import java.io.IOException;
import java.util.ArrayList;

public class RewardManager {
    private ArrayList<Reward> rewards;

    public RewardManager() {
        rewards = new ArrayList<>();
    }

    public void loadRewards() {
        try {
            rewards = TaskStorage.readReward("rewards.txt");
        } catch (IOException e) {
            System.out.println("Failed to load rewards: " + e.getMessage());
        }
    }

    public void saveRewards() {
        try {
            TaskStorage.saveReward(rewards);
        } catch (IOException e) {
            System.out.println("Failed to save rewards: " + e.getMessage());
        }
    }

    public void addReward(String title, String description, String category) {
        rewards.add(new Reward(title, description, category));
        saveRewards(); // auto-save after adding
    }

    public void removeReward(int index) {
        if (index >= 0 && index < rewards.size()) {
            rewards.remove(index);
            saveRewards(); // auto-save after removing
        }
    }

    public void setRewardName(int index, String newName) {
        if (index >= 0 && index < rewards.size()) {
            rewards.get(index).setName(newName);
            saveRewards(); // auto-save after renaming
        }
    }

    public ArrayList<Reward> getRewards() {
        return rewards;
    }
}
