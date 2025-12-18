package touhou;

import java.util.Random;

public class Roulette {

    private TaskManager manager;
    private Random random;
    private Task lastSelectedTask;

    public Roulette(TaskManager manager) {
        this.manager = manager;
        this.random = new Random();
    }

    // Returns a random task, or null if no tasks exist
    public Task spin() {
        if (manager.getTasks().isEmpty()) return null;

        if (lastSelectedTask != null){
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
