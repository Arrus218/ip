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
        try {
            this.tasks = new TaskList(storage.load());
        } catch (GingerException e) {
            ui.showError(e);
            tasks = new TaskList();
        }
    }

    /**
     * Starts the main application loop.
     * <p>
     * This method displays the welcome message and continuously processes user
     * input until an exit command is issued. It handles exceptions globally
     * to ensure the application doesn't crash on invalid input.
     * </p>
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showSeparator(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (GingerException e) {
                ui.showError(e);
            } finally {
                ui.showSeparator();
            }
        }
    }

    /**
     * Main entry point for the Ginger application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        new Ginger("./data/Ginger.txt").run();
    }
}
