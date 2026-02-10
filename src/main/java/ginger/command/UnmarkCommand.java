package ginger.command;

import ginger.GingerException;
import ginger.Storage;
import ginger.Ui;
import ginger.task.Task;
import ginger.task.TaskList;

/**
 * Represents a command to unmark a specific task as completed.
 * <p>
 * This command identifies a task by its index, updates its status to "undone",
 * and ensures the change is saved to the persistent storage.
 * </p>
 */
public class UnmarkCommand extends IndexCommand {
    /**
     * Constructs an {@code UnmarkCommand} for the task at the specified index.
     *
     * @param index The zero-based index of the task to be marked as undone.
     */
    public UnmarkCommand(int index) {
        super(index);
    }

    /**
     * Executes the mark-as-undone process.
     * <p>
     * This method performs the following steps:
     * <ol>
     * <li>Validates the index via {@code super.execute}.</li>
     * <li>Retrieves and updates the task status.</li>
     * <li>Displays a success message to the user.</li>
     * <li>Triggers {@code onExecute} to save the updated list to storage.</li>
     * </ol>
     * </p>
     *
     * @param tasks   The list containing the task to be marked.
     * @param ui      The user interface to confirm the status change.
     * @param storage The storage component used to persist the update.
     * @throws GingerException If the index is invalid or if the storage
     * operation fails in {@code onExecute}.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws GingerException {
        super.execute(tasks, ui, storage);
        Task t = tasks.getTask(super.index);
        t.setUndone();
        ui.showUnmarkSuccess(t);
        super.onExecute(t, tasks, ui, storage);
    }
}
