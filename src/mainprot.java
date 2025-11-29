package touhou;
import java.io.*;
import java.util.*;
public class Main {
    
    public static void main( String[] args) throws IOException{
		TaskStorage.readFile("tasks.txt");
		Scanner taskScanner = new Scanner(System.in);
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
			TaskStorage.addTask(name, description, category);
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
					for (int i = 0; i < TaskStorage.tasks.size(); i ++){					// Iterates through the ArrayList
						Tasks t = TaskStorage.tasks.get(i);
						System.out.println((i+1) + ". " + t.getName() + " - " + t.getCategory() + "\n" + t.getDescription());
					}
				}
				else if(decision == 'y'){
					break;
				}
			}
		}
    }

}
