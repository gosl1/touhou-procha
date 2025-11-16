import java.io.*;
import java.util.*;

class methods{

  static ArrayList<String> tasks = new ArrayList<>();			// Static ArrayList for use in other aspects of the code
	
	/*
	For saving the elements of the ArrayList into a file
	Will also be used later in task removal
	*/
  
  public static void fileSave() throws IOException{
    FileWriter writer = new FileWriter("tasks.txt");		
    for (String task : tasks) {								// Iterates through the ArrayList for every element
      writer.write(task + "\n");							// Writes that element into the file
    }
    writer.close();
	}

  public static void addTask() throws IOException{
		File file = new File("tasks.txt");					// 'file' points to a file named 'tasks.txt'

        if (!file.exists()) {								// Creates a new file with the same name as whatever 'file' is pointing to
            file.createNewFile();
        }
		
		Scanner fileScanner = new Scanner(file);			// For data persistence
    tasks.clear();
    while (fileScanner.hasNextLine()) {					// While the file still has lines left
        String task = fileScanner.nextLine();			// Saves that line to a variable
        tasks.add(task);								// Saves that variable to the ArrayList
    }
    fileScanner.close();
		
		Scanner taskScanner = new Scanner(System.in);
		String task = "";
		
		addTask:
		while (true){										// Basic [user input] -> [save to ArrayList]
			char decision = ' ';
			
			System.out.print("Enter task: ");
			task = taskScanner.nextLine();
			tasks.add(task);
			
			fileSave();
			
			while(true){
				System.out.println("y - yes\nn - no\ns - show tasks");
				System.out.print("Add more tasks?: ");
				decision = taskScanner.next().charAt(0);
				taskScanner.nextLine();
				decision = Character.toLowerCase(decision);						// So the user input is not case sensitive
				if (decision == 'n'){
					break addTask;												// Breaks outer loop
				}
				else if(decision == 's'){
					for (int i = 0; i < tasks.size(); i ++){					// Iterates through the ArrayList
						System.out.println((i+1) + ". " + tasks.get(i));		// Prints all saved tasks in a list
					}
				}
				else if(decision == 'y'){
					break;
				}
			}
		}
	}
}
