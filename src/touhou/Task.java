package touhou;
public class Task {
    private String taskName;
    // REMOVED: private String description; // Description field removed
    private String category;
    private boolean done;

    // REMOVED: description parameter from constructor
    // BEFORE: public Task(String taskname, String description, String category){
    // AFTER: Only name and category
    public Task(String taskname, String category){
        this.taskName = taskname;
        // REMOVED: this.description = description; // No more description
        this.category = category;
        this.done = false;
    }

    //Getters
    public String getName(){
        return taskName;
    }
    
    // REMOVED: getDescription() method
    // public String getDescription(){
    //     return description;
    // }
    
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

    // REMOVED: setDescription() method
    // public void setDescription(String desc){
    //     this.description = desc;
    // }

    public void setCategory(String category){
        this.category = category;
    }
    public void markDone(){
        this.done = true;
    }
}