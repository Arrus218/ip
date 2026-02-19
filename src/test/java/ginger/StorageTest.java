package ginger;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import ginger.task.Task;
import ginger.task.TaskList;
import ginger.task.Todo;

public class StorageTest {
    @TempDir
    Path tempDir;

    @Test
    void save_validTaskList_savesToFile() throws GingerException {
        Path tempFile = tempDir.resolve("Ginger.txt");
        Storage storage = new Storage();

        TaskList tasks = new TaskList();
        tasks.addTask(new Todo("read book"));

        // Test saving
        assertDoesNotThrow(() -> storage.save(tasks));
        assertTrue(tempFile.toFile().exists(), "File should be created on disk");
    }

    @Test
    void load_validFile_returnsCorrectTasks() throws GingerException {
        Path tempFile = tempDir.resolve("load_test.txt");
        Storage storage = new Storage();

        // 1. Manually create a "save file" structure to test loading
        // Assuming your format is: T | 0 | read book
        TaskList tasksToSave = new TaskList();
        tasksToSave.addTask(new Todo("read book"));
        storage.save(tasksToSave);

        // 2. Load it back
        ArrayList<Task> loadedTasks = storage.load();

        assertEquals(1, loadedTasks.size());
        assertEquals("read book", loadedTasks.get(0).getDescription());
    }
}
