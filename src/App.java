import java.io.UncheckedIOException;

public class App {
    private boolean quit = false;
    private final UI ui;
    private final TaskManager taskManager;

    public App(UI ui, TaskManager taskManager){
        this.ui = ui;
        this.taskManager = taskManager;
    }

    void run() {

        while(!quit){

            ui.printMainMenu();
            int input = ui.readIntInput();

            try {
                switch(input){
                    case 1:
                        //ADD TASK
                        ui.printAddTaskMenu();
                        String title = ui.readStringInput();
                        boolean isTitleValid = taskManager.addTask(title);
                        if(isTitleValid){
                            ui.taskAddedMessage();
                        }else{
                            ui.invalidTitleMessage();
                        }
                        break;
                    case 2:
                        //SHOW TASK LIST
                        if(taskManager.isTaskListEmpty()){
                            ui.noTasksYetMessage();
                        }else{
                            ui.showTaskList(taskManager.getTaskList());
                        }
                        break;
                    case 3:
                        //COMPLETE TASK
                        if(taskManager.isTaskListEmpty()){
                            ui.noTasksYetMessage();
                            break;
                        }
                        ui.showTaskList(taskManager.getTaskList());
                        ui.changeTaskStatusMenu();
                        int taskCompletedId = ui.readIntInput();
                        if(taskManager.completeTask(taskCompletedId)){
                            ui.taskCompletedMessage();
                        }else{
                            ui.taskDoesNotExistMessage();
                        }
                        break;
                    case 4:
                        //DELETE TASK
                        if(taskManager.isTaskListEmpty()){
                            ui.noTasksYetMessage();
                            break;
                        }
                        ui.showTaskList(taskManager.getTaskList());
                        ui.deleteTaskMenu();
                        int deleteTaskId = ui.readIntInput();
                        if(taskManager.deleteTask(deleteTaskId)){
                            ui.taskDeletedMessage();
                        }else{
                            ui.taskDoesNotExistMessage();
                        }
                        break;
                    case 5:
                        //QUIT
                        ui.quitMessage();
                        quit = true;
                        break;
                    default:
                        ui.invalidInputMessage();
                        break;
                }
            } catch (UncheckedIOException e) {
                ui.errorMessage(e.getMessage());
            }

        }

    }

}