package touhou;

import java.util.Random;
import java.util.List;

public class Shrine {
    private RewardManager rewardManager;
    private Random random;
    private static final int PULL_COST = 30;
    private static final double REWARD_CHANCE = 0.2;
    
    public Shrine(RewardManager rewardManager) {
        this.rewardManager = rewardManager;
        this.random = new Random();
    }
    
    public PullResult pull(int currentFaith) {
        if (currentFaith < PULL_COST) {
            return new PullResult(false, null, "Not enough faith!");
        }
        
        // 20% chance to get a random reward
        if (random.nextDouble() <= REWARD_CHANCE) {
            List<Reward> rewards = rewardManager.getRewards(); // Move this line here
            if (!rewards.isEmpty()) {
                Reward wonReward = rewards.get(random.nextInt(rewards.size()));
                return new PullResult(true, wonReward, "Congratulations! You won: " + wonReward.getName());
            } else {
                return new PullResult(false, null, "No rewards available to win!");
            }
        }
        else{
            List<Reward> rewards = rewardManager.getRewards(); // Move this line here
            if (!rewards.isEmpty()) {
            return new PullResult(false, null, "No rewards available to win!");
            }
        }
        return new PullResult(false, null, "Better luck next time...");
    }

    
    public int getPullCost() {
        return PULL_COST;
    }
    
    class PullResult {
        boolean success;
        Reward reward;
        String message;
        
        PullResult(boolean success, Reward reward, String message) {
            this.success = success;
            this.reward = reward;
            this.message = message;
        }
    }
}