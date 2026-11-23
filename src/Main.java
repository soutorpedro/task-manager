import java.io.UncheckedIOException;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        UI ui = new UI();
        TaskStorage storage = new TaskStorage(Path.of("tasks.txt"));
        TaskManager taskManager;
        try {
            taskManager = new TaskManager(storage);
        } catch (UncheckedIOException e) {
            ui.errorMessage(e.getMessage());
            return;
        }
        App app = new App(ui, taskManager);
        app.run();
    }
}