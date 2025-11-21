import java.io.*;
import java.util.*;

class methods{

	static ArrayList<Tasks> tasks = new ArrayList<>();			// Static ArrayList for use in other aspects of the code


	public static void fileSave(StringBuilder task) throws IOException{
	FileWriter writer = new FileWriter("tasks.txt", true);		
	writer.write(task + "\n");
	writer.close();
	}


	public static void readFile(String fileName) throws IOException{
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

	public static void addTask(String name, String description, String category) throws IOException{
		File file = new File("tasks.txt");							// 'file' points to a file named 'tasks.txt'
		if (!file.exists()) {										// Creates a new file with the same name as whatever 'file' is pointing to
			file.createNewFile();
		}
			
		StringBuilder task = new StringBuilder();
		task.append(name).append("|").append(description).append("|").append(category);
				
		fileSave(task);
		task.setLength(0);
				
		Tasks t = new Tasks(name, description, category);
		tasks.add(t);
		readFile("tasks.txt");
	}
}
