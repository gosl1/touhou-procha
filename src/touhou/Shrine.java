package touhou;

// Create Shrine.java
import java.util.Random;
import java.util.List;

public class Shrine {
    private RewardManager rewardManager;
    private Random random;
    private static final int PULL_COST = 100; // Faith cost per pull
    private static final double REWARD_CHANCE = 0.2; // 20% chance
    
    public Shrine(RewardManager rewardManager) {
        this.rewardManager = rewardManager;
        this.random = new Random();
    }
    
    public PullResult pull(int faithAmount) {
        if (faithAmount < PULL_COST) {
            return new PullResult(false, null, "Not enough faith!");
        }
        
        // Spend faith
        faithAmount -= PULL_COST;
        
        // 20% chance to get a random reward
        if (random.nextDouble() <= REWARD_CHANCE) {
            List<Reward> rewards = rewardManager.getRewards();
            if (!rewards.isEmpty()) {
                Reward wonReward = rewards.get(random.nextInt(rewards.size()));
                return new PullResult(true, wonReward, "Congratulations! You won: " + wonReward.getName());
            } else {
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
