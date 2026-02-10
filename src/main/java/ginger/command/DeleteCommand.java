package ginger.command;

import ginger.GingerException;
import ginger.Storage;
import ginger.Ui;
import ginger.task.Task;
import ginger.task.TaskList;

/**
 * Represents a command to remove a specific task from the application.
 * <p>
 * This command uses an index to identify the task, validates it via the
 * parent {@code IndexCommand}, and then handles the removal and
 * confirmation sequence.
 * </p>
 */
public class DeleteCommand extends IndexCommand {
    /**
     * Constructs a {@code DeleteCommand} with the specified target index.
     *
     * @param index The zero-based index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        super(index);
    }

    /**
     * Executes the task deletion process.
     * <p>
     * This method first validates the index. If valid, it retrieves the task,
     * removes it from the {@code TaskList}, and notifies the user.
     * <b>Note:</b> This operation permanently removes the task.
     * </p>
     *
     * @param tasks   The list from which the task will be deleted.
     * @param ui      The user interface to display the deletion confirmation.
     * @param storage The storage component (inherited logic handles saving).
     * @throws GingerException If the index is out of bounds or if the
     * deletion process encounters an error.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws GingerException {
        super.execute(tasks, ui, storage);
        Task t = tasks.getTask(index);
        tasks.deleteTask(t);
        ui.showDeletedTask(t, tasks.size());
    }
}
