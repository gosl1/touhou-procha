public class Tasks {
    private String taskName;
    private String description;
    private String category;

    public Tasks(String taskname, String description, String category){
        this.taskName = taskname;
        this.description = description;
        this.category = category;
    }

    public String getName(){
        return taskName;
    }

    public String getDescription(){
        return description;
    }
    
    public String category(){
        return category;
    }

    public void setName(String name){
        this.taskName = name;
    }

    public void setDescription(String desc){
        this.description = desc;
    }

    public void setCategory(String category){
        this.category = category;
    }

    //Plan to make a separate class that you can access the set methods

}
