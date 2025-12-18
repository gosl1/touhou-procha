package touhou;

public class FaithManager {
    private int faithAmount;
    
    public FaithManager() {
        faithAmount = 0;
    }
    
    public void addFaith(int amount) {
        faithAmount += amount;
    }
    
    public boolean spendFaith(int amount) {
        if (faithAmount >= amount) {
            faithAmount -= amount;
            return true;
        }
        return false;
    }
    
    public int getFaith() {
        return faithAmount;
    }
}