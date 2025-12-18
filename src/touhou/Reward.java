package touhou;
public class Reward {
    private String rewardName;

    public Reward(String rewardName){
        this.rewardName = rewardName;
    }

    //Getter
    public String getName(){
        return rewardName;
    }
    

    //Setter
    public void setName(String name){
        this.rewardName = name;
    }


}