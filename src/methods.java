import java.io.*;
import java.util.*;

class methods{

  static ArrayList<Tasks> tasks = new ArrayList<>();			// Static ArrayList for use in other aspects of the code
	
  
  public static void fileSave(StringBuilder task) throws IOException{
    FileWriter writer = new FileWriter("tasks.txt", true);		
    writer.write(task + "\n");
	writer.close();
    }


	public static void readFile(String fileName){
		Scanner fileScanner = new Scanner(new File(fileName));
		tasks.clear();
		while (fileScanner.hasNextLine()) {
			String line = fileScanner.nextLine();
			String[] taskData = line.split("\\|");
			if (taskData.length == 3) {
				tasks.add(new Tasks(taskData[0], taskData[1], taskData[2]));
			}
		}
		fileScanner.close();
	}

  public static void addTask() throws IOException{
	File file = new File("tasks.txt");							// 'file' points to a file named 'tasks.txt'
	if (!file.exists()) {										// Creates a new file with the same name as whatever 'file' is pointing to
		file.createNewFile();
	}
		
		Scanner taskScanner = new Scanner(System.in);
		StringBuilder task = new StringBuilder();
		addTask:
		while (true){
			String category;
			String name;
			String description;
			char decision = ' ';
			
			System.out.print("Enter task: ");
			name = taskScanner.nextLine();
			System.out.print("Enter description: ");
			description = taskScanner.nextLine();
			System.out.print("Enter Category: ");
			while (true) {
				System.out.print("Enter Category (easy/medium/hard): ");
				category = taskScanner.nextLine();
				if (category.equalsIgnoreCase("easy") ||
					category.equalsIgnoreCase("medium") ||
					category.equalsIgnoreCase("hard")) {
					break;
				} else {
					System.out.println("Invalid category. Try again.");
				}
			}
			task.append(name).append("|").append(description).append("|").append(category);
			
			fileSave(task);
			task.setLength(0);
			
			Tasks t = new Tasks(name, description, category);
			tasks.add(t);
			
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
					readFile("tasks.txt");
					for (int i = 0; i < tasks.size(); i ++){					// Iterates through the ArrayList
						Tasks t = tasks.get(i);
						System.out.println((i+1) + ". " + t.getName() + " - " + t.getCategory() + "\n" + t.getDescription());
					}
				}
				else if(decision == 'y'){
					task.setLength(0);
					break;
				}
			}
		}
	}
	readFile("tasks.txt");
}
