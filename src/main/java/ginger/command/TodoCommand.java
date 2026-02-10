package ginger.command;

import ginger.GingerException;
import ginger.Storage;
import ginger.Ui;
import ginger.task.Task;
import ginger.task.TaskList;
import ginger.task.Todo;

/**
 * Represents a command to add a {@code Todo} task to the task list.
 * <p>
 * This is the simplest form of an addition command, requiring only a
 * description. It leverages the template logic in {@code AddCommand}
 * to handle the addition and persistence.
 * </p>
 */
public class TodoCommand extends AddCommand {
    /**
     * Constructs a {@code TodoCommand} with the specified task description.
     *
     * @param description The text description of the todo task.
     */
    public TodoCommand(String description) {
        super(description);
    }

    /**
     * Executes the todo addition process.
     * <p>
     * This method creates a new {@code Todo} instance and delegates the
     * remaining steps—adding to the list, saving to disk, and updating the UI—to
     * the {@code onExecute} hook in the parent class.
     * </p>
     *
     * @param tasks   The list where the todo will be added.
     * @param ui      The user interface for displaying feedback.
     * @param storage The storage handler for saving the updated list.
     * @throws GingerException If the task cannot be saved to the hard drive
     * during the {@code onExecute} phase.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws GingerException {
        Task t = new Todo(super.description);
        super.onExecute(t, tasks, ui, storage);
    }
}
