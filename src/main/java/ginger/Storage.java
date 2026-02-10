package ginger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import ginger.task.Task;
import ginger.task.TaskList;

/**
 * Handles the loading and saving of task data to a local file.
 * <p>
 * This class manages all file I/O operations, ensuring that the task list
 * persists across different sessions of the application. It handles directory
 * creation and the translation of file strings into {@code Task} objects.
 * </p>
 *
 */
public class Storage {
    private final Path path;

    public Storage(String filePath) {
        this.path = Paths.get(filePath);
    }

    /**
     * Saves the current list of tasks to the local file system.
     * <p>
     * If the parent directories do not exist, they will be created automatically.
     * <b>Warning:</b> This will overwrite the existing file at the specified path.
     * </p>
     *
     * @param tasks The {@code TaskList} containing tasks to be archived.
     * @throws GingerException If an I/O error occurs during the saving process.
     */
    public void save(TaskList tasks) throws GingerException {
        try {
            Files.createDirectories(path.getParent());
            Files.write(path, tasks.toSaveFormat());
        } catch (IOException e) {
            throw new GingerException("Failed to save to file: " + e.getMessage());
        }
    }

    /**
     * Loads the task list from the local storage file.
     * <p>
     * Each line in the file is expected to represent a single task. The method
     * uses {@code Task.fromFileString} to reconstruct the task objects.
     * </p>
     *
     * @return An {@code ArrayList} of {@code Task} objects recovered from storage.
     * @throws GingerException If the file cannot be accessed or if a line in the
     * file does not match the expected task format.
     */
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
