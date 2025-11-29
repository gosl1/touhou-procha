package touhou;

import java.util.Random;

public class Roulette {

    private TaskManager manager;
    private Random random;

    public Roulette(TaskManager manager) {
        this.manager = manager;
        this.random = new Random();
    }

    // Returns a random task, or null if no tasks exist
    public Task spin() {
        if (manager.getTasks().isEmpty()) return null;
        int index = random.nextInt(manager.getTasks().size());
        return manager.getTasks().get(index);
    }
}
