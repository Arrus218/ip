package ginger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import ginger.task.Task;
import ginger.task.TaskList;

public class Storage {
    private final Path path;

    public Storage(String filePath) {
        this.path = Paths.get(filePath);
    }

    public void save(TaskList tasks) throws GingerException {
        try {
            Files.createDirectories(path.getParent());
            Files.write(path, tasks.toSaveFormat());
        } catch (IOException e) {
            throw new GingerException("Failed to save to file: " + e.getMessage());
        }
    }

    public ArrayList<Task> load() throws GingerException {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                try {
                    Task task = Task.fromFileString(line);
                    tasks.add(task);
                } catch (GingerException e) {
                    throw new GingerException("Failed to load task: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            throw new GingerException("Failed to load task list from file.");
        }

        return tasks;
    }
}
