import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

class Task {
   String name;
   boolean isCompleted;
   
   public Task(String name, boolean isCompleted) {
      this.name = name;
      this.isCompleted = isCompleted;
      }
      
      public Task(String name) {
         this(name, false);
         }
      
      public void markComplete() {
         isCompleted = true;
         }
         
         public String toString() {
            if (isCompleted) {
               return "[X] " + name; 
               }
                  else {
                  return "[ ] " + name;
                  }
                  }
                  }

public class ToDoApp {

   static String FILE_NAME ="tasks.txt";
   
   public static void saveTasks(ArrayList<Task> tasks) {
      try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
         for (Task t : tasks) {
            writer.println(t.name + "|" + t.isCompleted);
            }
            }
               catch (IOException e) {
                  System.out.println("Error saving tasks.");
                  }
                  }
                  
                  public static ArrayList<Task> loadTasks() {
                     ArrayList<Task> tasks = new ArrayList<>();
                     
                     try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
                        String line;
                        
                           while ((line = reader.readLine()) != null) {
                              String[] parts = line.split("\\|");
                              String name = parts[0];
                              boolean completed = Boolean.parseBoolean(parts[1]);
                              
                              tasks.add(new Task(name, completed));
                              }
                              }
                                 catch (IOException e) {
                                 }
                                 
                                 return tasks;
                                 }
                                 

   public static void main(String[] args) {

    ArrayList<Task> tasks = loadTasks();
    Scanner scanner = new Scanner(System.in);

    while (true) {

        System.out.println("\n=== TO DO LIST ===");
        System.out.println("1. Add task");
        System.out.println("2. View tasks");
        System.out.println("3. Delete Task");
        System.out.println("4. Mark task complete");
        System.out.println("5. Exit");
        System.out.print("Choose an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {

            System.out.print("Enter task: ");
            String taskName = scanner.nextLine();
            tasks.add(new Task(taskName));
            saveTasks(tasks);
            System.out.println("Task added.");

        } 
        else if (choice == 2) {

            System.out.println("\nYour tasks:");

            if (tasks.isEmpty()) {
                System.out.println("No tasks yet.");
            } else {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i));
                }
            }

        } 
        else if (choice == 3) {

            System.out.print("Enter task number to delete: ");
            int index = scanner.nextInt();
            scanner.nextLine();

            if (index > 0 && index <= tasks.size()) {
                tasks.remove(index - 1);
                saveTasks(tasks);
                System.out.println("Task deleted.");
            } else {
                System.out.println("Invalid task number.");
            }

        } 
        else if (choice == 4) {

            System.out.print("Enter task number to mark complete: ");
            int index = scanner.nextInt();
            scanner.nextLine();

            if (index > 0 && index <= tasks.size()) {
                tasks.get(index - 1).markComplete();
                saveTasks(tasks);
                System.out.println("Task marked complete.");
            } else {
                System.out.println("Invalid task number.");
            }

        } 
        else if (choice == 5) {
            System.out.println("Goodbye!");
            break;
        } 
        else {
            System.out.println("Invalid option.");
        }
    }

    scanner.close();
}
}