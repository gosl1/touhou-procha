package touhou;

import java.util.Random;

public class Roulette {

    private TaskManager manager;
    private RewardManager rewardManager;
    private Random random;
    private Task lastSelectedTask;

    public Roulette(TaskManager manager, RewardManager rewardManager) {
        this.manager = manager;
        this.rewardManager = rewardManager;
        this.random = new Random();
    }

        public Object spin() {
        // 20% chance to get a reward
        if (rewardManager != null && !rewardManager.getRewards().isEmpty()) {
            if (random.nextDouble() < 0.20) {  // 20% chance
                // Pick a random reward
                int rewardIndex = random.nextInt(rewardManager.getRewards().size());
                return rewardManager.getRewards().get(rewardIndex);
            }
        }
        
        // Otherwise (80% chance), pick a task
        if (manager.getTasks().isEmpty()) return null;

        if (lastSelectedTask != null) {
            lastSelectedTask.setRouletteBoosted(false);
        }

        int index = random.nextInt(manager.getTasks().size());
        lastSelectedTask = manager.getTasks().get(index);

        // Mark this task as boosted
        lastSelectedTask.setRouletteBoosted(true);

        return lastSelectedTask;
    }


    public Task getLastSelected() {
            return lastSelectedTask;
        }

}
