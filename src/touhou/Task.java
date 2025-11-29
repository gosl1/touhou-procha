package touhou;
public class Task {
    private String taskName;
    private String description;
    private String category;
    private boolean done;

    public Task(String taskname, String description, String category){
        this.taskName = taskname;
        this.description = description;
        this.category = category;
        this.done = false;
    }

    //Getters
    public String getName(){
        return taskName;
    }
    public String getDescription(){
        return description;
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

    public void setDescription(String desc){
        this.description = desc;
    }

    public void setCategory(String category){
        this.category = category;
    }
    public void markDone(){
        this.done = true;
    }

    //Plan to make a separate class that you can access the set methods

}
