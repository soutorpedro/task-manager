import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class TaskStorage {
    private final Path file;

    public TaskStorage(Path file) {
        this.file = file;
    }

    public List<Task> load() {
        List<Task> tasks = new ArrayList<>();
        if (!Files.exists(file)) {
            return tasks;
        }
        try {
            for (String line : Files.readAllLines(file)) {
                if (!line.isBlank()) {
                    tasks.add(fromFileString(line));
                }
            }
        } catch (IOException e) {
            throw new UncheckedIOException("Could not load tasks", e);
        }
        return tasks;
    }

    public void save(List<Task> tasks) {
        List<String> lines = new ArrayList<>();
        for (Task task : tasks) {
            lines.add(toFileString(task));
        }
        try {
            Files.write(file, lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new UncheckedIOException("Could not save tasks", e);
        }
    }

    private static String toFileString(Task task) {
        return task.getId() + "|" + task.getTitle() + "|" + task.getStatus();
    }

    private static Task fromFileString(String line) {
        String[] parts = line.split("[|]", 3);
        int id = Integer.parseInt(parts[0]);
        String title = parts[1];
        boolean isComplete = Boolean.parseBoolean(parts[2]);
        return new Task(id, title, isComplete);
    }
}