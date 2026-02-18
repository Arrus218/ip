package ginger.command;

import ginger.GingerException;
import ginger.Storage;
import ginger.Ui;
import ginger.task.Task;
import ginger.task.TaskList;

/**
 * Represents a command to mark a specific task as completed.
 * <p>
 * This command identifies a task by its index, updates its status to "done",
 * and ensures the change is saved to the persistent storage.
 * </p>
 */
public class MarkCommand extends IndexCommand {
    /**
     * Constructs a {@code MarkCommand} for the task at the specified index.
     *
     * @param index The zero-based index of the task to be marked as done.
     */
    public MarkCommand(int index) {
        super(index);
    }

    /**
     * Executes the mark-as-done process.
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
    public String execute(TaskList tasks, Ui ui, Storage storage) throws GingerException {
        super.execute(tasks, ui, storage);
        Task t = tasks.getTask(super.index);
        t.setDone();
        super.onExecute(t, tasks, ui, storage);
        return ui.showMarkSuccess(t);
    }
}
