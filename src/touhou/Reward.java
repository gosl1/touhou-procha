package touhou;
public class Reward {
    private String rewardName;
    private String description;
    private String category;

    public Reward(String rewardName, String description, String category){
        this.rewardName = rewardName;
        this.description = description;
        this.category = category;
    }

    //Getters
    public String getName(){
        return rewardName;
    }
    public String getDescription(){
        return description;
    }
    public String getCategory(){
        return category;
    }

    //Setters
    public void setName(String name){
        this.rewardName = name;
    }

    public void setDescription(String desc){
        this.description = desc;
    }

    public void setCategory(String category){
        this.category = category;
    }
}