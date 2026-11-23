import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private final List<Task> taskList = new ArrayList<>();
    private final TaskStorage storage;
    private int idTracker = 0;

    public TaskManager(TaskStorage storage){
        this.storage = storage;
        load();
    }

    private void load(){
        for (Task task : storage.load()) {
            taskList.add(task);
            // idTracker stays ahead of existing task IDs
            if (task.getId() >= idTracker) {
                idTracker = task.getId() + 1;
            }
        }
    }

    public boolean addTask(String input){
        if(input.isBlank()){
            return false;
        }
        String title = input.strip();
        Task task = new Task(idTracker, title, false);
        idTracker++;
        taskList.add(task);
        storage.save(taskList);
        return true;
    }

    public boolean completeTask(int taskId) {
        for (Task task : taskList) {
            if (task.getId() == taskId) {
                task.setStatusComplete();
                storage.save(taskList);
                return true;
            }
        }
        return false;
    }

    public boolean deleteTask(int taskId){
        if (taskList.removeIf(task -> task.getId() == taskId)) {
            storage.save(taskList);
            return true;
        }
        return false;
    }

    public List<Task> getTaskList(){
        return List.copyOf(taskList);
    }

    public boolean isTaskListEmpty(){
        return taskList.isEmpty();
    }

}

