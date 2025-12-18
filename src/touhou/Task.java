package touhou;
public class Task {
    private String taskName;

    private String category;
    private boolean done;
    private boolean rouletteBoosted;  // NEW FIELD

    public Task(String taskname, String category){
        this.taskName = taskname;
        this.category = category;
        this.done = false;
        this.rouletteBoosted = false;
    }

    //Getters
    public String getName(){
        return taskName;
    }
    

    
    public String getCategory(){
        return category;
    }
    public boolean getDone(){
        return done;
    }

    //Setters
    public void setName(String name){
        this.taskName = name;
    }



    public void setCategory(String category){
        this.category = category;
    }
    public void markDone(){
        this.done = true;
    }

        // Add these methods to Task.java:
    public boolean isRouletteBoosted() {
        return rouletteBoosted;
    }
    
    public void setRouletteBoosted(boolean boosted) {
        this.rouletteBoosted = boosted;
    }
}
