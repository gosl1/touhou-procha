package touhou;

import java.io.IOException;

public class FaithManager {
    private int faithAmount;
    
    public FaithManager() {
        try {
            faithAmount = TaskStorage.loadFaith();
        } catch (IOException e) {
            System.out.println("Failed to load faith: " + e.getMessage());
            faithAmount = 0;
        }
    }
    
    public void addFaith(int amount) {
        faithAmount += amount;
        saveFaith();
    }
    
    public boolean spendFaith(int amount) {
        if (faithAmount >= amount) {
            faithAmount -= amount;
            saveFaith();
            return true;
        }
        return false;
    }
    
    public int getFaith() {
        return faithAmount;
    }
    
    private void saveFaith() {
        try {
            TaskStorage.saveFaith(faithAmount);
        } catch (IOException e) {
            System.out.println("Failed to save faith: " + e.getMessage());
        }
    }
}