import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class UI {
    private final Scanner scanner = new Scanner(System.in);

    public UI(){

    }

    // PRINT METHODS

    public void printMainMenu(){
        System.out.println("\n=== TASK MANAGER ===\n" + "1. Add task.\n" + "2. Show task list.\n" + "3. Complete task.\n" + "4. Delete task.\n" + "5. Quit.\n" + "\nChoose an option: ");
    }

    public void printAddTaskMenu(){
        System.out.println("Task title: ");
    }

    public void showTaskList(List<Task> taskList){
        System.out.println("\nTask List:");
        for(Task task: taskList){
            System.out.println(task);
        }
    }

    public void noTasksYetMessage(){
        System.out.println("\nNo tasks yet.");
    }

    public void taskAddedMessage(){
        System.out.println("Task added.");
    }

    public void taskDeletedMessage(){
        System.out.println("Task Deleted.");
    }

    public void changeTaskStatusMenu(){
        System.out.println("Which task have you completed? ");
    }

    public void deleteTaskMenu(){
        System.out.println("Which task do you want to delete? ");
    }

    public void taskCompletedMessage(){
        System.out.println("Task Completed.");
    }

    public void taskDoesNotExistMessage(){
        System.out.println("That ID does not exist.");
    }

    public void quitMessage(){
        System.out.println("See you next time!");
    }

    public void invalidInputMessage(){
        System.out.println("That's not an option. Try again.");
    }

    public void invalidTitleMessage(){
        System.out.println("That title is not valid.");
    }

    public void errorMessage(String error){
        System.out.println(error);
    }


    // READ METHODS

    public int readIntInput(){
        while (true) {
            String line = scanner.nextLine();
            try {
                return Integer.parseInt(line.trim());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a valid number: ");
            }
        }
    }

    public String readStringInput(){
        return scanner.nextLine();
    }
}