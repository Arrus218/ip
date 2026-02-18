package ginger;

import ginger.command.Command;
import ginger.task.TaskList;

/**
 * The main class for the Ginger application.
 * <p>
 * Ginger acts as the coordinator between the user interface, storage system,
 * and task logic. It initializes the application state and runs the main
 * input-process-output loop.
 * </p>
 */
public class Ginger {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    public boolean isExit;

    /**
     * Initializes the Ginger application with a specific file path for data.
     * <p>
     * It attempts to load existing tasks from the provided path. If loading fails
     * due to a {@code GingerException}, it notifies the user and starts with
     * an empty task list.
     * </p>
     *
     * @param filePath The relative path to the text file where tasks are stored.
     */
    public Ginger(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new Ui();
        this.isExit = false;
        try {
            this.tasks = new TaskList(storage.load());
        } catch (GingerException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            this.isExit = c.isExit();
            return c.execute(tasks, ui, storage);
        } catch (GingerException e) {
            return ui.showError(e);
        }
    }
}
