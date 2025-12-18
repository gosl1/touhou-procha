package touhou;
public class Reward {
    private String rewardName;
    // REMOVED: private String description; // Description field removed
    // REMOVED: private String category; // Category field removed

    // REMOVED: description and category parameters from constructor
    // BEFORE: public Reward(String rewardName, String description, String category){
    // AFTER: Only name
    public Reward(String rewardName){
        this.rewardName = rewardName;
        // REMOVED: this.description = description; // No more description
        // REMOVED: this.category = category; // No more category
    }

    //Getter
    public String getName(){
        return rewardName;
    }
    
    // REMOVED: getDescription() method
    // public String getDescription(){
    //     return description;
    // }
    
    // REMOVED: getCategory() method
    // public String getCategory(){
    //     return category;
    // }

    //Setter
    public void setName(String name){
        this.rewardName = name;
    }

    // REMOVED: setDescription() method
    // public void setDescription(String desc){
    //     this.description = desc;
    // }

    // REMOVED: setCategory() method
    // public void setCategory(String category){
    //     this.category = category;
    // }
}